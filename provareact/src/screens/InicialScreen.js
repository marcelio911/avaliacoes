
import React, { Fragment } from 'react';
import { AppRegistry } from 'react-native';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  StatusBar,
} from 'react-native';

import {
  Colors,
} from 'react-native/Libraries/NewAppScreen';
import HeaderCustom from '../components/HeaderCustom';
import VitrineScreen from './VitrineScreen';
import { name as appName } from '../../app.json';

export default class HomeScreen extends React.PureComponent {

  render() {
    return (
      
      <Fragment>
        <HeaderCustom style={styles.header} title="Vitrine de Produtos" />

        <SafeAreaView>
          <ScrollView
            contentInsetAdjustmentBehavior="automatic"
            style={styles.scrollView}>
            <VitrineScreen />
          </ScrollView>
        </SafeAreaView>
      </Fragment>
    );
  };
}

const styles = StyleSheet.create({
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  body: {
    backgroundColor: Colors.white,
  },
  header: {
    marginTop: 0,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  highlight: {
    fontWeight: '700',
  },
});

AppRegistry.registerComponent(appName, () => HomeScreen);