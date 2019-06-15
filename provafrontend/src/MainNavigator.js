import { createStackNavigator, createAppContainer, createDrawerNavigator } from 'react-navigation';
import HomeScreen from './screens/HomeScreen';
import VitrineScreen from './screens/VitrineScreen';
import ClienteScreen from './screens/ClienteScreen';
import CarrinhoScreen from './screens/CarrinhoScreen';

const MainNavigator = createStackNavigator({
  Home: { screen: HomeScreen },
  Vitrine: { screen: VitrineScreen },
  Cliente: { screen: ClienteScreen },
  Carrinho: { screen: CarrinhoScreen },
},
  {
    initialRouteName: "Home"
  });

const App = createAppContainer(MainNavigator);

export default App;
