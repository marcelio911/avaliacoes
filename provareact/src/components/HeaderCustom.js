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
import commonStyles from '../../commonStyles';
import { Colors } from 'react-native/Libraries/NewAppScreen';

export default class HeaderCustom extends Component { 
     
    render(){
        return (
            <View style={styles.container}>
                <View style={styles.rowContainer}>
                    <Image source={this.props.icon} style={styles.image}/>
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
        borderColor: commonStyles.colors.default,
        backgroundColor: commonStyles.colors.default,

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
        color: Colors.white,
        fontFamily: 'shelter',
        padding: 10,
        fontSize: 20
    },
    icon: {
        color: Colors.white,
        padding: 10,
        fontSize: 20
    }
});

AppRegistry.registerComponent(appName, ()=> HeaderCustom);