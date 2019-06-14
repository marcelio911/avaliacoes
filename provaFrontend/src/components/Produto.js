// import { Card, Button } from 'react-native-material-design';

export default props => {

    props.produto = {
        id: null,
        descricao: '',
        valor: 0
    }

    return (
        <View>
            <View style={{ height: 1, backgroundColor: '#f7f7f7' }}>
                <View style={{flex: 1, backgroundColor: 'powderblue'}} >
                    <Text>{props.produto.descricao}</Text>
                    <Text style={styles.PriceElement}>{props.produto.valor}</Text>
                </View>
            </View>
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
    PriceElement: {
        fontSize: 5,
        textAlign: 'center',
        margin: 5,
    },
});
