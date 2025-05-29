// 1. Build a Pod object using a Go structure.
// 2. Serialize the Pod object in JSON using a serializer.
// 3. Build an HTTP POST request with the body that contains the Pod \
// to create, serialized in JSON.
// 4. Call the kubernetes API with the request built.
// 5. Get the body from the response.
// 6. Depending on the response status code:
// - If the request returns a 2XX status code:
// 7. Deserialize the response body as a Pod Go structure.
// 8. Display the created Pod object as JSON for information;
// otherwise:
// 9. Deserialize the response body as a status Go structure.
// 10. Display the Status object as JSON for information:

package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"

	corev1 "k8s.io/api/core/v1"
	metav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/apimachinery/pkg/runtime"
	"k8s.io/apimachinery/pkg/runtime/schema"
	jsonk "k8s.io/apimachinery/pkg/runtime/serializer/json"
)

func createPodObject() *corev1.Pod {
	pod := corev1.Pod{
		Spec: corev1.PodSpec{
			Containers: []corev1.Container{
				{
					Name:  "runtime",
					Image: "nginx",
				},
			},
		},
	}
	pod.SetName("my-pod")
	pod.SetLabels(map[string]string{
		"app.kubernetes.io/component": "my-component",
		"app.kubernetes.io/name":      "a-name",
	})
	return &pod
}

func serializePodObject(
	serializer runtime.Serializer,
	pod *corev1.Pod,
) (
	io.Reader,
	error,
) {
	var buf bytes.Buffer
	err := serializer.Encode(pod, &buf)
	if err != nil {
		return nil, err
	}
	return &buf, nil
}

func buildPostRequest(
	body io.Reader,
) (
	*http.Request,
	error,
) {
	reqCreate, err := http.NewRequest(
		"POST",
		"http://127.0.0.1:8001/api/v1/namespaces/default/pods",
		body,
	)
	if err != nil {
		return nil, err
	}
	reqCreate.Header.Add(
		"Accept",
		"application/json",
	)
	reqCreate.Header.Add(
		"Content-Type",
		"application/json",
	)
	return reqCreate, nil
}

func deserializePodBody(
	serializer runtime.Serializer,
	body []byte,
) (
	*corev1.Pod,
	error,
) {
	var result corev1.Pod
	_, _, err := serializer.Decode(body, nil, &result)
	if err != nil {
		return nil, err
	}
	return &result, nil
}

func deserializeStatusBody(
	serializer runtime.Serializer,
	body []byte,
) (
	*metav1.Status,
	error,
) {
	var status metav1.Status
	_, _, err := serializer.Decode(body, nil, &status)
	if err != nil {
		return nil, err
	}
	return &status, nil
}

func getJSONSerializer() runtime.Serializer {
	scheme := runtime.NewScheme()
	scheme.AddKnownTypes(
		schema.GroupVersion{
			Group:   "",
			Version: "v1",
		},
		&corev1.Pod{},
		&metav1.Status{},
	)
	return jsonk.NewSerializerWithOptions(
		jsonk.SimpleMetaFactory{},
		nil,
		scheme,
		jsonk.SerializerOptions{},
	)
}

func createPod() error {
	pod := createPodObject()
	serializer := getJSONSerializer()
	postBody, err := serializePodObject(serializer, pod)
	if err != nil {
		return err
	}
	reqCreate, err := buildPostRequest(postBody)
	if err != nil {
		return err
	}
	client := &http.Client{}
	resp, err := client.Do(reqCreate)
	if err != nil {
		return err
	}
	defer resp.Body.Close()
	body, err := io.ReadAll(resp.Body)
	if err != nil {
		return err
	}
	if resp.StatusCode < 300 {
		createdPod, err := deserializePodBody(serializer, body)
		if err != nil {
			return err
		}
		json, err := json.MarshalIndent(createdPod, "", " ")
		if err != nil {
			return err
		}
		fmt.Printf("%s\n", json)
	} else {
		status, err := deserializeStatusBody(serializer, body)
		if err != nil {
			return err
		}
		json, err := json.MarshalIndent(status, "", " ")
		if err != nil {
			return err
		}
		fmt.Printf("%s\n", json)
	}
	return nil
}

func main() {
	err := createPod()
	if err != nil {
		fmt.Printf("Error creating pod: %v\n", err)
	}
}
