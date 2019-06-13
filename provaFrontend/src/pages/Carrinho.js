import { Card, Button } from 'react-native-material-design';
import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View} from 'react-native';

export default class Carrinho extends Component{
    
    render() {
        return (
                <View>
                    <Card>
                        {/* <Card.Media
                            image={<Image source={require('assets/img/welcome.jpg')} />}
                            overlay
                        /> */}
                        <Card.Body>
                            <Text>Some text to go in the body.</Text>
                        </Card.Body>
                        <Card.Actions position="right">
                            <Button value="ACTION" />
                        </Card.Actions>
                    </Card>
                </View>
        );
    }
}