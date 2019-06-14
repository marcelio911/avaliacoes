# Setup inicial
```
 mvn clean install
```
```
 mvn spring-boot:run
 ```
#### Dados de acesso
* [Console H2: http://localhost:2222/h2-console]
* [Usuário: user / 0fd02cd7-2ae1-497b-9a4c-7a5a15452a5e]



### Testar endpoint:: produtos::
```
 curl -v -X POST localhost:2222/api/produtos/salvar -H 'Content-Type:application/json' -d '{"descricao": "gardener", "valor": 92.99, "min
iatura": "../"}'
```