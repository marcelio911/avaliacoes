import { AppRegistry } from 'react-native';
import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    Image,
} from 'react-native';
import {
    Colors,
  } from 'react-native/Libraries/NewAppScreen';
import { name as appName } from '../../app.json';

export default props => {

    props.produto = {
        id: null,
        descricao: '',
        valor: '0'
    }

    return (
        <View style={styles.sectionContainer}>
            <Text style={styles.sectionTitle}>
                {props.produto.descricao}
            </Text> 
            <Text style={styles.sectionDescription}>
                Preço: <Text style={styles.highlight}>{props.produto.valor}</Text>
            </Text>
        </View>
    )

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
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

