import React, { Component } from 'react';
import { 
    TouchableOpacity, 
    FlatList, 
    View, 
    AppRegistry,
    ActivityIndicator
} from 'react-native';
import { YellowBox } from 'react-native';
import Produto from '../components/Produto';
import { name as appName } from '../../app.json';
import { apiServer as server } from '../../app.json';
import axios from 'axios';

export default class ClienteScreen extends React.Component {

    screens = 1;
    state = {
        loading: false, // user list loading
        isRefreshing: false, //for pull to refresh
        data: [], //user list
        error: '',
    }

    constructor(props) {
        super(props);
    }    

    loadClientes = async () => {
        try {
            this.setState({ loading: true })
            const response = await axios.get(`${server}/clientes/listarTodos`);
            const responseJson = await response.json();
            console.log("responseJson:: ", JSON.stringify(response));
            this.setState({ loading: false, data: responseJson.listaGenerica })
        } catch (err) {
            // showError(err);            
            YellowBox.ignoreWarnings([`Informação: ...ainda não existem dados registrados ${err}`]);
        }
    }

    // exemplo:: https://medium.com/@ecavalcanti/react-native-consumindo-a-api-da-marvel-c444e0bc1c8a 
    async componentDidMount() {
        const response = await fetch(`${server}/api/clientes/listarTodos`, 
        {   method: 'GET',
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
        
        this.setState({ loading: false, data: responseJson.listaGenerica });
        // this.loadClientes();
    }

    _renderItem = ({ item }) => {
        return (
            <TouchableOpacity onPress={() => this._onItemPress(item)} style={{ flexDirection: 'row', padding: 10, alignItems: 'center' }}>
                <Produto cliente={item}></Produto>
            </TouchableOpacity>
        )
    }

    _onItemPress = (item) => {
        this.state.addVitrineModal = true;
        const {navigate} = this.props.navigation;
        navigate('AddProdutoModal', { cliente: item , isVisible: true})
    }

    handleLoadMore = () => {
        if (!this.state.loading) {
            this.screens = this.screens + 1; // increase screens by 1
            this.loadClientes(); // method for API call 
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
                keyExtractor={(item, index) => index.toString()}
                ItemSeparatorComponent={() =>

                    <View />

                }
            />
        );
    }
}

AppRegistry.registerComponent(appName, () => ClienteScreen);