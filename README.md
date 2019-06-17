# Setup inicial ambos os projetos estão neste repositório...
```
git clone https://github.com/marcelio911/avaliacoes.git
```
## Estrutura do projeto API
```
avaliacoes
├── prova-api
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── com
│   │   │   │   │   ├── prova
│   │   │   │   │   │   ├── config
│   │   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── dao
│   │   │   │   │   │   ├── dto
│   │   │   │   │   │   ├── entity
│   │   │   │   │   │   ├── enums
│   │   │   │   │   │   ├── exception
│   │   │   │   │   │   ├── interface
│   │   │   │   │   │   ├── repository
│   │   │   │   │   │   ├── response
│   │   │   │   │   │   ├── ProvaApplication.java
│   │   │   ├── resources
│   │   │   │   ├── application.properties
│   │   │   │   ├── data.sql
│   │   ├── test
```
```
 cd avaliacoes/prova-api
```
```
 mvn clean install
```
### Executar API do projeto
```
 mvn spring-boot:run
 ```
### Dados de acesso
* [Acessar: http://localhost:2222/]
    * [method (list):  http://localhost:2222/api/{endpoint}/listarTodos]
    * [method (findById):  http://localhost:2222/api/{endpoint}/obterPorId/{id}]
    * [method (deleteById):  http://localhost:2222/api/{endpoint}/deletePorId/{id}]
    * [method (save):  http://localhost:2222/api/{endpoint}/salvar]

## Estrutura do projeto FRONT-END
```
avaliacoes
├── provareact
│   ├── android
│   ├── assets
│   ├── ios
│   ├── node_modules
│   ├── src
│   │   ├── components
│   │   ├── screens
│   ├── __tests__
│   ├── index.js
│   ├── App.js
│   ├── package.json
│   ├── app.json
```
### Execute Android App
```
npm install
```
### Resolvendo problemas caso SO Linux Ubuntu
```
chmod 755 android/gradlew
```
```
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
```
### Alterar o IP do Servidor {apiServer} no arquivo app.json
```
    ├── app.json
    "apiServer": "http://SEU_IP_API/api"
```
```
react-native start
```
```
react-native run-android
```
