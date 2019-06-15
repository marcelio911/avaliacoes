import {
    AppRegistry,
    Button,
} from 'react-native';
import React, { Component } from 'react';
import { name as appName } from '../../app.json';

class HomeScreen extends React.Component {
    static navigationOptions = {
      title: 'Bem-vindo...',
    };
    render() {
      const {navigate} = this.props.navigation;
      return (
        <Button
          title="Ir Para lista de produtos"
          onPress={() => navigate('Vitrine')}
        />
      );
    }
  }
  AppRegistry.registerComponent(appName, HomeScreen);