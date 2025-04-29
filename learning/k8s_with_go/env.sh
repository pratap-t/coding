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
