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