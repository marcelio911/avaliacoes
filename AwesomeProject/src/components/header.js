import {AppRegistry} from 'react-native';
import React, {  Component } from 'react';
import {
    StyleSheet,
    Text,
    View,
    Platform,
    Image
} from 'react-native';
import {name as appName} from '../../app.json';

export default class HeaderCustom extends Component { 
     
    render(){
        return (
            <View style={styles.container}>
                <View style={styles.rowContainer}>
                    <Image source={this.props.icon} style={styles.image}></Image>
                    <Text style={styles.title}>{this.props.title}</Text>
                </View>
            </View>
        )
    }
   
}
    


const styles = StyleSheet.create({
    container: {
        marginTop: Platform.OS === 'ios' ? 20 : 0,
        padding: 10,
        borderBottomWidth: 1,
        borderColor: '#BBB',
    },
    rowContainer: {
        flexDirection: 'row',
    },
    image: {
        width: 30,
        height: 30,
        resizeMode: 'contain'
    },
    title: {
        color: '#000',
        fontFamily: 'shelter',
        padding: 10,
        fontSize: 20
    }
});

AppRegistry.registerComponent(appName, ()=> HeaderCustom);