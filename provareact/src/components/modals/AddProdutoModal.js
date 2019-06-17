import {
    AppRegistry,
    View,
    Text,
    TextInput,
    Modal,
    StyleSheet,
    StatusBar,
    TouchableOpacity,
    TouchableWithoutFeedback
} from 'react-native';
import React, { Component } from 'react';
import { name as appName } from './../../../app.json';
import { whileStatement } from '@babel/types';

const initialState = { descricao: '', valor: 0 };
export default class AddProdutoModal extends Component {
    state = { ...initialState }

    salvar = () => {
        const data = { ...this.state };
        this.props.onSave(data);
        this.setState({ ...initialState });
    }

    render() {
        return (
            <Modal onRequestClose={this.props.onCancel}
                visible={this.props.isVisible}
                animationType='slide' transparent={true}>
                <TouchableWithoutFeedback onPress={this.props.onCancel} >
                    <View style={styles.offSet}></View>
                </TouchableWithoutFeedback>
                <View style={styles.container}>
                    <Text style={styles.header}>Novo produto</Text>
                    <TextInput placeholder="Descrição" style={styles.input} 
                        onChangeText={descricao => this.setState({ descricao })}
                        value={this.state.descricao}
                        />
                    <TextInput placeholder="Valor" style={styles.input} 
                        onChangeText={valor => this.setState({ valor })}
                        value={this.state.valor}
                        />
                </View>
                <View style={{
                    flexDirection: 'row',
                    justifyContent: 'left'
                }}>
                    <TouchableOpacity onPress={this.salvar}>
                        <Text style={styles.buttonS}>Salvar</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={this.props.onCancel}>
                        <Text style={styles.buttonC}>Cancelar</Text>
                    </TouchableOpacity>
                </View>
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

AppRegistry.registerComponent(appName, AddProdutoModal);