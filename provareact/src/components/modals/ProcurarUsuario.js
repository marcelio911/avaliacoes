import {
    AppRegistry,
    Text,
    Modal,
    StyleSheet,
    TouchableOpacity,
} from 'react-native';
import React, { Component } from 'react';
import { name as appName } from './../../../app.json';
import Autocomplete from 'react-native-autocomplete-input';
import HeaderCustom from '../HeaderCustom.js';

const initialState = {
    usuario: null,
    isVisible: false,
    text: '',
    query: {
        telefone: '',
        cpf: ''
    },
    clientes: []
}

export default class ProcurarUsuario extends Component {
    state = { 
        ...initialState
    }

    load = async () => {
        try {
            const response = await fetch(`${server}/api/cliente/listarTodos`,
                {
                    method: 'GET',
                    headers: {
                        Accept: 'application/json',
                        'Content-Type': 'application/json',
                    },
                })
            const responseJson = await response.json();
            console.log("responseJsresponseJson.listaGenericaon:: ", JSON.stringify(responseJson.listaGenerica));
            if (!responseJson.listaGenerica) {
                YellowBox.ignoreWarnings(['Informação: ...ainda não existem dados registrados']);
            }

            this.setState({ loading: false, clientes: responseJson.listaGenerica });
        } catch (err) {
            // showError(err);            
            YellowBox.ignoreWarnings([`Informação: ...ainda não existem dados registrados ${err}`]);
        }
    }

    async componentDidMount() {
        await this.load();
    }


    procurar = () => {
        const data = { ...this.state.clientes };
        this.props.onSave(data);
        this.setState({ ...initialState }); 
    }

    _filterData = async (query) => {

    }

    render() {
        const { query } = this.state.query;
        const data = this._filterData(query);

        return (
            <Modal onRequestClose={this.props.onCancel}
                visible={this.props.isVisible}
                animationType='slide' transparent={true}>
                <HeaderCustom title="Pesquisar cliente: CPF, Telefone" /> 
                <Autocomplete
                    data={this.state.clientes}
                    defaultValue={query}
                    onChangeText={text => this.setState({ query: text })}
                    renderItem={({ item, i }) => (
                        <TouchableOpacity onPress={() => this.setState({ query: item })}>
                        <Text>{item}</Text>
                        </TouchableOpacity>
                    )}
                />
            </Modal>
        )
    }

}

var styles = StyleSheet.create({
    container: {
        backgroundColor: 'white',
        justifyContent: 'space-between'
    },
    offSet:{
        flex: 1,
        backgroundColor: '#e3e3e3',
    },
    button:{
        margin: 20,
        marginRight: 30,
    },
    input:{
        width: '90%',
        height: 30,
        marginLeft: 10,
        backgroundColor: 'white',
        borderWidth: 1,
        borderColor: '#e3e3e3',
        borderRadius: 6
    }
})

AppRegistry.registerComponent(appName, ProcurarUsuario);