import {createStackNavigator, createAppContainer} from 'react-navigation';
import HomeScreen from './screens/HomeScreen';
import Carrinho from './screens/Carrinho';

const MainNavigator = createStackNavigator({
  Home: {screen: HomeScreen},
  Carrinho: {screen: Carrinho},
});

const App = createAppContainer(MainNavigator);

export default App;