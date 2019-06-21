import { 
  createStackNavigator 
} from 'react-navigation';
import VitrineScreen from './screens/VitrineScreen';
import ClienteScreen from './screens/ClienteScreen';
import CarrinhoScreen from './screens/CarrinhoScreen';
import InicialScreen from './screens/InicialScreen';

const Routes = createStackNavigator({
  InicialScreen,
  VitrineScreen,
  ClienteScreen,
  CarrinhoScreen
})

export default Routes;
