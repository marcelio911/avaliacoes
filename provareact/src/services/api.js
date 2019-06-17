import axios from 'axios';
import { apiServer as server } from '../../app.json';

/* Endereços para cada emulador/simulador:
** Emulador Android Studio: http://192.168.1.106/
** Simulador IOS:           http://localhost/
*/
const api = axios.create({
    baseURL: '${server}',
});

export default api;