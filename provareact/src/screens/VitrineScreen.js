import React, { Component } from 'react';
import {
    TouchableOpacity,
    FlatList,
    Button,
    StyleSheet,
    View,
    AppRegistry,
    ActivityIndicator,
    Image,
    Alert
} from 'react-native';
import { YellowBox } from 'react-native';
import Produto from '../components/Produto';
import { name as appName } from '../../app.json';
import { apiServer as server } from '../../app.json';
import ProcurarUsuario from '../components/modals/ProcurarUsuario';
import CarrinhoScreen from './CarrinhoScreen';
import images from '../../assets/static';
import commonStyles from '../../commonStyles';

export default class VitrineScreen extends React.PureComponent {


    screens = 1;
    state = {
        loading: false,
        data: [],
        dados: [
            {
                "id": 10001,
                "descricao": "Calça",
                "valor": 50.2,
                "miniatura": images.produtoIcon
            },
            {
                "id": 10002,
                "descricao": "Tenis",
                "valor": 145.65,
                "miniatura": images.produtoIcon
            },
            {
                "id": 10003,
                "descricao": "Camisa",
                "valor": 29.99,
                "miniatura": images.produtoIcon
            }
        ],
        error: '',
        visibleProdutos: [],
        showProdutos: true,
        addProdutoModal: false,
        addVitrineModal: false,
    }

    constructor(props) {
        super(props);
    }

    loadProdutos = async () => {
        try {
            const response = await fetch(`${server}/api/produto/listarTodos`,
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

            this.setState({ loading: false, data: responseJson.listaGenerica });
        } catch (err) {
            // showError(err);            
            YellowBox.ignoreWarnings([`Informação: ...ainda não existem dados registrados ${err}`]);
        }
    }

    // exemplo:: https://medium.com/@ecavalcanti/react-native-consumindo-a-api-da-marvel-c444e0bc1c8a 
    async componentDidMount() {
        await this.loadProdutos();
    }

    _renderItem = ({ item }) => {
        return (
            <TouchableOpacity onPress={() => this._onItemPress(item)} style={{ flexDirection: 'row', padding: 10, alignItems: 'center' }}>
                {/*<Image style={{ height: 50, width: 50, borderRadius: 25 }} source={ item.miniatura } />*/}
                <Image style={{ height: 50, width: 50, borderRadius: 25 }} source={ images.produtoIcon } />
                <Produto produto={item}></Produto>
                <Button style={styles.btn} title="+" onPress={() => this._onItemPress(item)} />
            </TouchableOpacity> 
        )
    }

    _onItemPress = (item) => {
        if (this.state.clienteSessao) {
            this.setState({showClienteModal: false, showCarrinhoComprasModal: true});
            this._irPara();
        } else {
            this.setState({ showClienteModal: true, showCarrinhoComprasModal: false });
        }
        this.state.produtoSelecionado = item;
        console.log("_onItemPress:: ", JSON.stringify(this.state));

    }

    handleLoadMore = () => {
        if (!this.state.loading) {
            this.screens = this.screens + 1; // increase screens by 1
            this.loadProdutos(); // method for API call 
        }
    };

    _irPara = () => {
        console.log("_irPara");
        if (this.state.clienteSessao) {
            const { navigate } = this.props.navigation;
            navigate('CarrinhoScreen', { clienteSessao: this.state.clienteSessao, isVisible: true })
        } else {
            this.setState({ showClienteModal: true, showCarrinhoComprasModal: false })
        }
    }

    render() {
        if (this.state.isLoading) {
            return <View style={{
                width: '100%',
                height: '100%'
            }}><ActivityIndicator style={{ color: '#000' }} /></View>;

        } else if (this.state.showClienteModal) {
            return (
                <View>
                    <ProcurarUsuario isVisible={true} />
                </View>
            );
        } else if (this.state.showCarrinhoComprasModal) {
            return (
                <View>
                    <CarrinhoScreen isVisible={true} />
                </View>
            );
        } else {
            return (
                <View>
                    <FlatList
                        data={this.state.data}
                        renderItem={this._renderItem}
                        keyExtractor={(item, index) => index.toString()}

                    />

                    <Button style={styles.botoesMm} title="Ver Carrinho" onPress={() => this._irPara()} />
                </View>

            );
        }
    }
}

const styles = StyleSheet.create({
    btn: {
        height: 40,
        width: 20,
        borderRadius: 25,
        fontSize: 36,
        fontWeight: '600',
        marginTop: 20,
        color: commonStyles.colors.default
    }
});

AppRegistry.registerComponent(appName, () => VitrineScreen);