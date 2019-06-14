import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View } from 'react-native';
import { YellowBox } from 'react-native';
import Produto from '../components/Produto';

export default class Carrinho extends React.PureComponent {

    static navigationOptions = {
        title: 'Carrinho de compras'
    }

    constructor(props) {
        super(props)
        this.state = this.getInitialState();
    }

    getInitialState() {
        return (
            data = {}
        )
    }

    save = () => {

    }

    // exemplo:: https://medium.com/@ecavalcanti/react-native-consumindo-a-api-da-marvel-c444e0bc1c8a 
    async componentDidMount() {
        const timestamp = Number(new Date())
        const hash = md5.create()
        hash.update(timestamp + PRIVATE_KEY + PUBLIC_KEY)

        const response = await fetch(`http://localhost/api/produtos/listarTodos`)
        const responseJson = await response.json()
        if (!responseJson.data) {
            YellowBox.ignoreWarnings(['Informação: ...ainda não existem dados registrados']);
        }
        this.setState({ data: responseJson.data.results })
    }

    _renderItem = ({ item }) => {
        return (
            <TouchableOpacity onPress={() => this._onItemPress(item)} style={{ flexDirection: 'row', padding: 10, alignItems: 'center' }}>
                <Image style={{ height: 50, width: 50, borderRadius: 25 }} source={{ uri: `${item.thumbnail.path}.${item.thumbnail.extension}` }} />
                <Text style={{ marginLeft: 10 }}>{item.descricao}</Text>
                <Produto produto={item}></Produto>
            </TouchableOpacity>
        )
    }

    _onItemPress = (item) => {
        this.props.navigation.navigate('Descrição', { produto: item })
    }

    render() {
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