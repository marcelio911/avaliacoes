# Setup inicial 
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
## Executar API do projeto
```
 mvn spring-boot:run
 ```

## Dados de acesso
* [Acessar: http://localhost:2222/]
    * [method (list):  http://localhost:2222/api/{endpoint}/listarTodos]
    * [method (findById):  http://localhost:2222/api/{endpoint}/obterPorId/{id}]
    * [method (deleteById):  http://localhost:2222/api/{endpoint}/deletePorId/{id}]
    * [method (save):  http://localhost:2222/api/{endpoint}/salvar]


## Estrutura do projeto FRONT-END
```
avaliacoes
├── provafrontend
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

## Execute Android App
```
emulator list-avds
```
```
emulator -avd "Seu_Device" --gpu system-libs --hardware
```

```
react-native start
```

```
react-native run-android
```