package com.prova.enums;

/**
 *
 * @author marcelio
 */
public enum HttpEnum {
    /*
     * mensagens de sucesso
     */
    MSG_SUCESSO_OPERACAO_GENERICA("MS0001", "Esta operação foi efetuada com sucesso."),
    MSG_SUCESSO_ATUALIZACAO("MS0002", "A versão do aplicativo está atualizada."),
    MSG_SUCESSO_DELETE("MS0003", "Este item foi removido com Sucesso."),
    /*
     * mensagens de erro
     */
    MSG_ERRO_DADOS_INVALIDOS_MR0000("MR0000", "Dados inválidos."),
    MSG_ERRO_CARRINHO_VAZIO_MR0500("MR0500", "O carrinho está vazio."),
    MSG_ERRO_PRODUTO_ADD_CARRINHO_MR0500("MR0500", "Este produto já foi adicionado ao carrinho."),
    MSG_ERRO_NENHUM_RESULTADO_MR0404("MR0404", "Nenhum resultado encontrado para esta consulta."),
    MSG_ERRO_GENERICO_MR0500("MR0500", "Não foi possível continuar a executar esta operação... ");
    

    private final String codigo;
    private final String msg;

    private HttpEnum(String codigo, String msg) {
        this.codigo = codigo;
        this.msg = msg;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMsg() {
        return msg;
    }

    public static HttpEnum getEnum(String codigo) {
        if (codigo != null) {
            for (HttpEnum respostas : HttpEnum.values()) {
                if (respostas.getCodigo().equals(codigo)) {
                    return respostas;
                }
            }
        }
        return null;
    }

    public static HttpEnum getEnumMessage(String mensagem) {
        if (mensagem != null) {
            for (HttpEnum respostas : HttpEnum.values()) {
                if (respostas.getMsg().equals(mensagem)) {
                    return respostas;
                }
            }
        }
        return null;
    }

    public static HttpEnum getEnumName(String name) {
        if (name != null) {
            for (HttpEnum respostas : HttpEnum.values()) {
                if (respostas.name().equals(name)) {
                    return respostas;
                }
            }
        }
        return null;
    }

}
