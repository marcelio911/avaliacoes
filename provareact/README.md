###README
```
npm install
```
## configurar os components material design e o pacote de native-vector-icons
```
sudo npm install -g react-devtools
```
```
npm install react-native-vector-icons react-native-action-button axios react-native-material-design react-native-gesture-handler@^1.0.12 --save
```
```
mkdir -p ./android/app/src/main/assets/fonts
```
```
cp ./node_modules/react-native-vector-icons/Fonts/MaterialIcons.ttf ./android/app/src/main/assets/fonts
```
```
react-native link
react-native link react-native-vector-icons
```
```
yarn add @react-native-community/async-storage
react-native link @react-native-community/async-storage
```
### Resolvendo problemas se for Linux Ubuntu
```
chmod 755 android/gradlew
```
```
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
```
## Alterar o IP do Servidor {apiServer} no arquivo app.json
```
    "apiServer": "http://SEU_IP_API/api"
```
```
 adb reverse tcp:8097 tcp:8097
 react-devtools
```
```
react-native start || npm start -- --reset-cache
```
```
react-native run-android
```


