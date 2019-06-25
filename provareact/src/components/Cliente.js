import { AppRegistry } from 'react-native';
import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    Image,
} from 'react-native';
import {
    Header,
    LearnMoreLinks,
    Colors,
    DebugInstructions,
    ReloadInstructions,
  } from 'react-native/Libraries/NewAppScreen';
import { name as appName } from '../../app.json';

export default props => {

    props.cliente = {
        id: null,
        nome: '',
        email: '',
        endereco: '',
        telefone: '',
        cpf: ''
    }

    return (
        <View style={styles.sectionContainer}>
            <Image style={{ height: 120, width: 90, borderRadius: 25 }} source={props.produto.miniatura} />
            <Text style={styles.sectionTitle}>
                CPF
            </Text>
            <TextInput placeholder="Descrição" style={styles.input} 
                onChangeText={descricao => this.setState({ descricao })}
                value={this.state.descricao}
                />
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

