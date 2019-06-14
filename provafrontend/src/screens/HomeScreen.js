import {
    AppRegistry,
    View,
    Text,
    TextInput,
    Modal,
    StyleSheet,
    StatusBar,
    TouchableOpacity,
    Button,
    TouchableWithoutFeedback
} from 'react-native';
import React, { Component } from 'react';
import { name as appName } from '../../app.json';

class HomeScreen extends React.Component {
    static navigationOptions = {
      title: 'Welcome',
    };
    render() {
      const {navigate} = this.props.navigation;
      return (
        <Button
          title="Go to Jane's profile"
          onPress={() => navigate('Profile', {name: 'Jane'})}
        />
      );
    }
  }

  AppRegistry.registerComponent(appName, HomeScreen);