import {
    AppRegistry,
    Text,
    Modal,
    StyleSheet,
    TouchableOpacity,
    Button,
    DeviceEventEmitter,
    YellowBox,
    View,
} from 'react-native';
import AsyncStorage from '@react-native-community/async-storage';
import React, { Component } from 'react';
import { name as appName } from './../../../app.json';
import Autocomplete from 'react-native-autocomplete-input';
import { apiServer as server } from '../../../app.json';
import commonStyles from '../../../commonStyles.js';
import HeaderCustom from '../HeaderCustom.js';

const initialState = {
    clienteSessao: null,
    isLoading: true,
    isVisible: false,
    text: '',
    query: {
        telefone: '',
        cpf: ''
    },
    clientes: [

    ]
}

export default class ProcurarUsuario extends Component {

    state = {
        ...initialState
    }
    constructor(props) {
        super(props);
    }

    _storeData = async (value) => {
        console.log("_storeData:: ", JSON.stringify(value));
        try {
          await AsyncStorage.setItem('@cliente:', value);
        } catch (error) {
          // Error saving data
        }
      };

    toggleAddCliente = () => this.setState(prevState => ({ clienteSessao: !prevState.clienteSessao }))

    load = async () => {
        console.log("`${server}/api/cliente/listarTodos`", `${server}/api/cliente/listarTodos`);
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
        console.log("componentDidMount:: ", JSON.stringify(this.state));
        this.setState({
            "isVisible": this.props.isVisible
        })
        DeviceEventEmitter.addListener('hardwareBackPress', () => onBackButtonPress());
    }

    onBackButtonPress() {
        this.setState({ isVisible: null });
        console.log("this.state:: ", JSON.stringify(this.state), ", props", JSON.stringify(this.props));
    }


    procurar = () => {
        const data = { ...this.state.clientes };
        this.props.onSave(data);
        this.setState({ ...initialState });
    }

    _filterData = async (query) => {
        console.log("_filterData:: ", JSON.stringify(query));
        this.setState({ loading: true});
        try {
            const response = await api.post(`/api/cliente/obterPorTelCpf`,
            {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    telefone: query,
                    cpf: query
                })
            });
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


    render() {
        const { query } = this.state.query;
        if (this.state.loading) {
            return <View style={styles.loading}><ActivityIndicator style={{ color: '#000' }} /></View>;

        } else {
            return (
                <Modal onRequestClose={this.props.onCancel}
                    visible={this.state.isVisible}
                    animationType='slide' transparent={true}>
                    <HeaderCustom title="Pesquisar cliente: CPF, Telefone" />

                    <View style={styles.autocompleteContainer}>
                        <Text>Tesst: {this.props.isVisible}</Text>
                        <Autocomplete
                            data={this.state.clientes}
                            autoCapitalize="none"
                            autoCorrect={false}
                            defaultValue={query}
                            onChangeText={text => this._filterData(text)}
                            keyExtractor={(item, index) => index.toString()}
                            renderItem={({ item, release_date }) => (
                                //you can change the view you want to show in suggestion from here
                                <TouchableOpacity onPress={() => this._storeData(item)}>
                                    <Text style={styles.itemText}>
                                        {item.nome} - {item.telefone}
                                    </Text>
                                </TouchableOpacity>
                            )}
                        />


                    </View>
                </Modal>
            )
        }
    }

}

var styles = StyleSheet.create({
    loading: {
        width: '100%',
        height: '100%',
        flex: 1,
        marginTop: 40,
        
    },
    autocompleteContainer: {
        flex: 1,
        left: 0,
        position: 'absolute',
        right: 0,
        top: 0,
        zIndex: 1
    },
    container: {
        backgroundColor: 'white',
        justifyContent: 'space-between'
    },
    offSet: {
        flex: 1,
        backgroundColor: '#e3e3e3',
    },
    button: {
        margin: 20,
        marginRight: 30,
    },
    input: {
        width: '90%',
        height: 30,
        marginLeft: 10,
        backgroundColor: 'white',
        borderWidth: 1,
        borderColor: '#e3e3e3',
        borderRadius: 6
    },
    itemText: {
        height: 40,
        borderRadius: 25,
        fontSize: 16,
        fontWeight: '600',
        marginTop: 4,
        left: 4,
        color: commonStyles.colors.default
    }
})

AppRegistry.registerComponent(appName, ProcurarUsuario);