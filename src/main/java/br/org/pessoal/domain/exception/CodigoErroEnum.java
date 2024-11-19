package br.org.pessoal.domain.exception;

/**
 * Tipo de erro no domínio da aplicação.
 *
 * (validação de negócio?)
 *
 */
public enum CodigoErroEnum {

    /**
     * Tipo de erro desconhecido
     */
    ERRO_DESCONHECIDO,
    /**
     * Erro ao consultar dado
     */
    ERRO_CONSULTA,
    /**
     * Erro de persistência
     */
    ERRO_PERSISTENCIA;
}
