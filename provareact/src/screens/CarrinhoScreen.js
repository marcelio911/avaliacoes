import React from 'react';
import {
    TouchableOpacity,
    FlatList,
    View,
    AppRegistry,
    ActivityIndicator,
} from 'react-native';
import { YellowBox } from 'react-native';
import { name as appName } from '../../app.json';
import { apiServer as server } from '../../app.json';
import api from '../services/api';
import ItemCarrinho from '../components/ItemCarrinho';

export default class CarrinhoScreen extends React.PureComponent {


    screens = 1;
    state = {
        isLoading: false, // user list isLoading
        isRefreshing: false, //for pull to refresh
        clienteSessao: null, //user list
        itensCarrinho: [],
        error: '',
        addCarrinho: false,
    }

    constructor(props) {
        super(props);
    }

    validarProduto = async (idProduto) => {
        const response = await api.get(`/api/carrinho/validarProduto/`,
            {
                method: 'GET',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    idCliente: this.props.clienteSessao,
                    idProduto: this.props.produtoSelecionado
                })
            });
        this.state.addCarrinho = await response.json().adicionado;
        this.state.itensCarrinho = await response.json().listaGenerica;
    }

    add = async () => {
        const response = await fetch(`${server}/api/carrinho/add/`,
            {
                method: 'GET',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    idCliente: this.props.clienteSessao,
                    idProduto: this.props.produtoSelecionado
                })
            })
        const responseJson = await response.json();
        console.log("responseJsresponseJson.listaGenericaon:: ", JSON.stringify(responseJson.listaGenerica));
        if (!responseJson.listaGenerica) {
            YellowBox.ignoreWarnings(['Informação: ...ainda não existem dados registrados']);
        }

        this.setState({ isLoading: false, data: responseJson.listaGenerica });
    }

    load = async () => {
        const response = await fetch(`${server}/api/carrinho/idUsuario/${this.state.clienteSessao}`,
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

        this.setState({ isLoading: false, data: responseJson.listaGenerica });
    }

    // exemplo:: https://medium.com/@ecavalcanti/react-native-consumindo-a-api-da-marvel-c444e0bc1c8a 
    async componentDidMount() {

        this.load();
    }

    _renderItem = ({ item }) => {
        return (
            <TouchableOpacity onPress={() => this._onItemPress(item)} style={{ flexDirection: 'row', padding: 10, alignItems: 'center' }}>
                <ItemCarrinho produto={item} />
            </TouchableOpacity>
        )
    }

    _onItemPress = (item) => {

    }

    handleLoadMore = () => {

    };

    render() {
        if (this.state.isLoading && this.screens === 1) {
            return <View style={{
                width: '100%',
                height: '100%'
            }}><ActivityIndicator style={{ color: '#000' }} /></View>;
        }
        return (
            <FlatList
                data={this.state.itensCarrinho}
                renderItem={this._renderItem}
                keyExtractor={(item, index) => index.toString()}
                ItemSeparatorComponent={() =>

                    <View />

                }
            />
        );
    }
}

AppRegistry.registerComponent(appName, () => CarrinhoScreen);