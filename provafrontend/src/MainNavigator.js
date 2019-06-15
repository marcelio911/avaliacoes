import {createStackNavigator, createAppContainer} from 'react-navigation';
import HomeScreen from './screens/HomeScreen';
import VitrineScreen from './screens/VitrineScreen';
import ClienteScreen from './screens/ClienteScreen';
import CarrinhoScreen from './screens/CarrinhoScreen';

const MainNavigator = createStackNavigator({  
  Vitrine: {screen: VitrineScreen},
  Home: {screen: HomeScreen},
  Cliente: {screen: ClienteScreen},
  Carrinho: {screen: CarrinhoScreen},
});

const App = createAppContainer(MainNavigator);

export default createDrawerNavigator({
  Vitrine: {screen: ()=> <VitrineScreen />},
  Home: {screen: ()=> <HomeScreen/>},
  Cliente: {screen: ()=> <ClienteScreen/>},
  Carrinho: {screen: ()=> <arrinhoScreen/>},
},{ drawerWidth: 300});