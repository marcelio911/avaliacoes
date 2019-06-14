import {createStackNavigator, createAppContainer} from 'react-navigation';

const MainNavigator = createStackNavigator({
  Home: {screen: HomeScreen},
  Carrinho: {screen: Carrinho},
});

const App = createAppContainer(MainNavigator);

export default App;