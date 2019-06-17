import { AppRegistry } from 'react-native';
import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    Image,
    Button
} from 'react-native';
import { Icon } from 'react-native-vector-icons/FontAwesome';
import {
    Colors,
} from 'react-native/Libraries/NewAppScreen';
import { name as appName } from '../../app.json';
import commonStyles from '../../commonStyles.js';

export default props => {

    props.itemCarrinho = {
        produto: null,
        idUsuario: null,
        quantidade: 0
    }

    return (
        <View style={styles.sectionContainer}>

            <Image style={{ height: 120, width: 90, borderRadius: 25 }} source={props.produto.miniatura} />
            <Text style={styles.sectionTitle}>
                {props.produto.descricao}
            </Text>
            <Text style={styles.removerItem}>
                <Icon name="menu" size={20}/>
            </Text>
            <Text style={styles.sectionDescription}>

                Preço: <Text style={styles.highlight}>{props.produto.valor}</Text>

                <Button style={styles.botoesMm} value="-" />

                <TextInput style={styles.inputQtd}
                    onChangeText={quantidade => this.setState({ quantidade })}
                    value={this.state.quantidade}
                />

                <Button style={styles.botoesMm} value="+" />
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
    removerItem: {
        fontSize: 24,
        fontWeight: '600',
        justifyContent: 'center',
        alignItems: 'center',
        color: Colors.gray,
        backgroundColor: commonStyles.colors.default,
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
    inputQtd: {
        height: 30,
        width: 30,
        borderRadius: 25
    },
    botoesMm: {
        height: 30,
        width: 20,
        borderRadius: 25,
        fontSize: 36,
        fontWeight: '600',
        color: Colors.white
    }
});

AppRegistry.registerComponent(appName, () => itemCarrinho);