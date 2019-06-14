import React, { Component } from 'react';
import { 
    TouchableOpacity, 
    FlatList, 
    Text, 
    View, 
    AppRegistry,
    Image
} from 'react-native';
import { YellowBox } from 'react-native';
import Produto from '../components/Produto';
import { name as appName } from '../../app.json';
import { server as apiServer } from '../../app.json';
import axios from 'axios';
import images from '../../assets/static';

//import ActionButton from 'react-native-action-button';
//import AddProdutoModal from '../components/modals/AddProdutoModal';

export default class Carrinho extends React.PureComponent {

    
    screens = 1;
    state = {
        loading: false, // user list loading
        isRefreshing: false, //for pull to refresh
        data: [], //user list
        dados: [ {
            "id": 10001,
            "descricao": "Calça",
            "valor": 50.2,
            "miniatura": images
            },
              {
            "id": 10002,
            "descricao": "Tênis",
            "valor": 145.65,
            "miniatura": images
            },
              {
            "id": 10003,
            "descricao": "Camisa",
            "valor": 29.99,
            "miniatura": images
            }],
        error: '',
        visibleProdutos: [],
        showProdutos: true,
        addProdutoModal: false,
        addCarrinhoModal: false,
    }

    constructor(props) {
        super(props);
    }    

    loadProdutos = async () => {
        try {
            this.setState({ loading: true })
            const response = await axios.get(`${server}/produtos/listarTodos`);
            const responseJson = await response.json();
            console.log("responseJson:: ", JSON.stringify(response));
            this.setState({ loading: false, data: responseJson.listaGenerica })
        } catch (err) {
            // showError(err);            
            YellowBox.ignoreWarnings([`Informação: ...ainda não existem dados registrados ${err}`]);
        }
    }

    componentWillMount() {
        //this.loadProdutos() //Method for API call
    }

    // exemplo:: https://medium.com/@ecavalcanti/react-native-consumindo-a-api-da-marvel-c444e0bc1c8a 
    async componentDidMount() {
        const response = await fetch(`${server}/api/produtos/listarTodos`, 
        {   method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            }, 
        })
        const responseJson = await response.json();
        if (!responseJson.data) {
            YellowBox.ignoreWarnings(['Informação: ...ainda não existem dados registrados']);
        }
        this.setState({ data: responseJson.listaGenerica })
    }

    _renderItem = ({ item }) => {
        return (
            <TouchableOpacity onPress={() => this._onItemPress(item)} style={{ flexDirection: 'row', padding: 10, alignItems: 'center' }}>
                <Image style={{ height: 50, width: 50, borderRadius: 25 }} source={item.miniatura} />
                <Text style={{ marginLeft: 10 }}>{item.descricao}</Text>
                <Produto produto={item}></Produto>
            </TouchableOpacity>
        )
    }

    _onItemPress = (item) => {
        this.state.addCarrinhoModal = true;
        this.props.navigation.navigate('AddProdutoModal', { produto: item , isVisible: true})
    }

    handleLoadMore = () => {
        if (!this.state.loading) {
            this.screens = this.screens + 1; // increase screens by 1
            this.loadProdutos(); // method for API call 
        }
    };

    render() {
        if (this.state.loading && this.screens === 1) {
            return <View style={{
                width: '100%',
                height: '100%'
            }}><ActivityIndicator style={{ color: '#000' }} /></View>;
        }
        return (
            <FlatList
                data={this.state.data}
                renderItem={this._renderItem}
                keyExtractor={(item) => item.id}
                ItemSeparatorComponent={() =>

                    <View />

                }
            />
        );
    }
}

AppRegistry.registerComponent(appName, () => Carrinho);