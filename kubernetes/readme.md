# Kubernetes

Criando cluster `kind create cluster`

Conectar ao cluster que acabamos de criar `kubectl cluster-info --context kind-kind`

OBS: como o kind utiliza container para rodar o cluster que acabamos de criar, podemos `docker ps`, veremos o nosso **container kind-control-plane** rodando, ou `kubectl get nodes` para lista os nodes criados.

mostrar todos os cluster criados no kind `kind get clusters` 

deleta o cluster `kind delete clusters name_cluster`

## Criando cluster com multiplos nodes
criar arquivo de configuração no dir. `k8s/kind.yaml`
execute `kind create cluster --config=k8s/kind.yaml --name=clusterName` 

# clusters configurados
No arquivo `/.kube/config` temos as configurações dos cluster configurados em nossa máquina. Para ver quais são os clusters `kubectl config get-clusters`

para alterar entre os contextos (clusters) `kubectl use-context name-cluster`

## criando imagem docker
Neste exeplo temos uma simples webserver desenvolvido em GO. `/kubernetes/server.go`

vamos criar a imagem deste server. `docker build -t giovanitrevisol/hello-go .` No arquivo Dockerfile temos as informações de build.

Executar a imagem `docker run --rm -p 80:80 giovanitrevisol/hello-go`
Ponto, basta acessar no navegador: `localhost:80` que deverá funcionar.

## Pods
na pasta `/k8s/` vamos criar um arquivo `pod.yaml`. Especifique as configurações do Pod.

para criar o pod no kubernetes `kubectl apply -f k8s/pod.yaml`

para ver o pod rodando `kubectl get pods`

para deletar o pod `kubectl delte pod namepod`

Para ver informações de cada pod `kubectl describe pod podName`

## Replicaset
criar arquivo `/k8s/replicaset.yaml` adicionar parametros desejados.
**Do parametro `template:` em diante temos as configurações de cada pod**

Para executar `kubectl apply -f k8s/replicaset.yaml`

Para deletar replicaset `kubectl delete replicaset nameReplicaset`

## Deployment
criar arquivo `/k8s/deploymment.yaml` adicionar parametros desejados.
**Do parametro `template:` em diante temos as configurações de cada pod**

**Neste exemplo o deployment e o replicaset são iguais, mudando apenas o kind**

Para executar `kubectl apply -f k8s/deployment.yaml`

ver quantos arquivos de deployment temos em nosso cluster `kubectl get deployment`

## Rollout e Revisões

Ver informações de deploy `kubectl rollout history deployment nameDeployment` mostra quantos deploy aconteceram..

podemos voltar um deployment `kubectl rollout undo deployment goserver` ou
podemos voltar um deployment específico `kubectl rollout undo deployment  goserver --to-revision=2`

# Services
É a porta de entrada para a aplicação.. precisa mos de um service para direcionar para o devido pod. O proprio kuberntes atua como um loaderBalance.

criar arquivo `/k8s/service.yaml` adicionar parametros desejados.

Para executar `kubectl apply -f k8s/service.yaml`

# Utilizando Proxy para acessar API do Kubernetes

`kubectl proxy --port=8080`

# ConfigMap
criar arquivo `/k8s/configmap-env.yaml` adicionar parametros desejados.

Para executar `kubectl apply -f k8s/config-map.yaml` 

Agora precisamos rodar o deploy novamente `kubectl apply -f k8s/deployment.yaml`

# Secrets
criar arquivo `/k8s/secret.yaml` adicionar parametros desejados.


Adicinar chamada no arquivo deploy... Agora precisamos rodar o deploy novamente `kubectl apply -f k8s/deployment.yaml`