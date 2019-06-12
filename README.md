# Setup inicial
$ git clone https://github.com/marcelio911/avaliacoes.git
$ cd avaliacoes/
$ mvn clean install
$ mvn spring-boot:run

#### Dados de acesso
* [Acessar: http://localhost:2222/]
* [Usuário: user / 0fd02cd7-2ae1-497b-9a4c-7a5a15452a5e]


#### Execute Android
emulator list-avds
emulator -avd "Seu_Device" --gpu system-libs --hardware
react-native run-android