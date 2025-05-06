kubectl get pods --namespace default -v6
kubectl get pods --namespace default -v10
kubectl proxy
export HOST='http://127.0.0.1:8001'
###
cat >pod.yaml <<EOF
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  containers:
    - image: nginx
      name: nginx
EOF
####
curl $HOST/api/v1/namespaces/default/pods -H "Content-Type: application/yaml" --data-binary @pod.yaml
curl -X GET $HOST/api/v1/namespaces/default/pods/nginx
curl -X GET $HOST/api/v1/pods | less
kubectl get pods nginx -o yaml
curl $HOST/api/v1/namespaces/default/pods?labelSelector=mylabel==foo
curl $HOST/api/v1/namespaces/default/pods?labelSelector=mylabel\!=foo
curl $HOST/api/v1/namespaces/default/pods?labelSelector=mylabel+in+(foo,baz)
    Note the plus characters (+) that encodes spaces in the URL. The original selector being: mylabel in (foo,baz).
curl $HOST/api/v1/namespaces/default/pods?fieldSelector=status.phase==Running
####
cat > deploy.yaml <<EOF
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx
spec:
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - image: nginx
        name: nginx
EOF
####
curl $HOST/apis/apps/v1/namespaces/default/deployments -H "Content-Type: application/yaml" --data-binary @deploy.yaml
cat deploy.yaml | sed 's/image: nginx/image: nginx:latest/' >deploy2.yaml
curl -X PUT $HOST/apis/apps/v1/namespaces/default/deployments/nginx -H "Content-type: application/yaml" --data-binary @deploy2.yaml
This is equivalent to running the kubectl command:$ kubectl replace --namespace project1 -f deploy2.yaml -o json
curl -X GET $HOST/apis/apps/v1/namespaces/default/deployments/nginx

curl -X DELETE $HOST/api/v1/namespaces/default/pods/nginx

"resourceVersion": "130745"
####
cat >deploy3.yaml <<EOF
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx
ResourceVersion: "130745"
spec:
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - image: nginx
        name: nginx
EOF
####
curl -X PUT $HOST/apis/apps/v1/namespaces/default/deployments/nginx -H "Content-type: application/yaml" --data-binary @deploy3.yaml
####
cat >deploy-patch.yaml <<EOF
spec:
  template:
    spec:
      containers:
      - image: nginx:alpine
        name: nginx
EOF
####
curl -X PATCH $HOST/apis/apps/v1/namespaces/default/deployments/nginx -H "Content-type: application/strategic-merge-patch+yaml" --data-binary @deploy-patch.yaml

kubectl proxy
curl -X PATCH $HOST/apis/apps/v1/namespaces/default/deployments/nginx -H "Content-Type: application/strategic-merge-patch+yaml" --data-binary @deploy-patch.yaml

curl -X PATCH "$HOST/apis/apps/v1/namespaces/default/deployments/nginx?fieldManager=manager2&force=true" -H "Content-Type: application/apply-patch+yaml" --data-binary @patch.yaml

curl -X PATCH "$HOST/apis/apps/v1/namespaces/default/deployments/nginx?fieldManager=manager1" -H "Content-type: application/apply-patch+yaml" --data-binary @patch2.yaml

curl "$HOST/api/v1/namespaces/default/pods/?watch=true"

above is equivalet to command
kubectl get pods --watch -o json
##### To illustrate, here is a little experiment. 
First create two (2) pods and watch pods with a selector that will only match the first one. 
You will get an ADDED event for the matching pod, and, after a while, you should get a BOOKMARK event 
(but it is not guaranteed). If, in the meantime, there was no activity on the pods of the namespace, 
the resourceVersion should be the same.
$ kubectl run nginx1 --image nginx --labels mylabel=foo
$ kubectl run nginx2 --image nginx --labels mylabel=bar
$ curl "$HOST/api/v1/namespaces/default/pods?labelSelector=mylabel==foo&watch=true&allowWatchBookmarks=true"
$ kubectl delete pods nginx2
$ kubectl run nginx2 --image nginx --labels mylabel=bar
curl "$HOST/api/v1/namespaces/default/pods?labelSelector=mylabel==foo&watch=true&allowWatchBookmarks=true&resourceVersion=156887"

curl "$HOST/api/v1/namespaces/default/pods?limit=1"
"continue": "eyJ2IjoibWV0YS5rOHMuaW8vdjEiLCJydiI6MTU3NTI0LCJzdGFydCI6Ii9teS1yZWxlYXNlLW1hcmlhZGItMFx1MDAwMCJ9",
curl "$HOST/api/v1/pods?limit=1&continue=eyJ2IjoibWV0YS5rOHMuaW8vdjEiLCJydiI6MTU3NTI0LCJzdGFydCI6Ii9teS1ycmVsZWFzZS1tYXJpYWItMFx1MDAwMCJ9"
#####
Getting Results in Various Formats
Getting Results as a Table
$ curl $HOST/api/v1/pods-H 'Accept: application/json;as=Table;g=meta.k8s.io;v=v1'

Using the YAML Format
$ curl $HOST/api/v1/pods -H 'Accept: application/yaml'

Using the Protobuf Format
########## Chapter 2
How to access the Kubernetes API using the Go language. The two important go libraries needed to work with \
the Kubernetes API are: apimachinery and api.
The apimachinary library is a generic library that takes care of serializing data between go structures and \
the objects written in the JSON(or YAML or Protobuf) format. 
The API Library, for its part, is a collection of Go structures that are needed to work in Go with \
the resources defined by the Kubernetes API.
1. Writing K8s resources in Go, either using the client-go library or using an HTTP request.
2. For example, to create a deployment, you will need to create a Deployment kind; \ 
and for this, initiate a Deployment structure, which is defined in the apps/v1 package of the API Library.
3. import packages
     import ( corev1 "k8s.io/api/core/v1"
              appsv1 "k8s.io/api/apps/v1"
            )
4. You can now instantiate a Deployment structure:
     myDep := appsv1.Deployment{}
5. To compile your program, you will need to fetch the library
     go get k8s.io/api@latest
            or
     go get k8s.io/api@v0.23
6. All structures related to Kinds first embed two generic structures: TypeMeta and ObjectMeta.
Both are declared in the /pkg/apis/meta/v1 package of the API machinery library.
The TypeMeta FieldsThe TypeMeta structure is defined as follows:
type TypeMeta struct { 
    Kind string 
    APIVersion string
}

