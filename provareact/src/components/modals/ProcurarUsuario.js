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

const initialState = {
    usuario: null,
    isVisible: false,
    text: '',
    clientes: []
}

export default class ProcurarUsuario extends Component {
    state = { 
        ...initialState
    }


    procurar = () => {
        const data = { ...this.state };
        this.props.onSave(data);
        this.setState({ ...initialState }); //usar redux para devolver o usuario para sessao
    }

    _filterData = async (query) => {

    }

    render() {
        const { query } = this.state;
        const data = this._filterData(query);

        return (
            <Modal onRequestClose={this.props.onCancel}
                visible={this.props.isVisible}
                animationType='slide' transparent={true}>
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