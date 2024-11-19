package br.org.pessoal.domain.exception;

/**
 * Representa os possíveis tipos de erro vindos através da porta de entrada da
 * aplicação.
 */
public enum TipoErroEnum {

    /**
     * Se refere aos tipos de erro na qual a entrada seja inválida.
     */
    VALIDACAO,
    /**
     * Se refere aos tipos de erro na qual a entrada não foi encontrada.
     */
    NAO_ENCONTRADO,
    /**
     * Se refere aos tipos de erro na qual a entrada já foi ou esteja sendo
     * processada.
     */
    CONFLITO,
    /**
     * Se refere aos tipos de erro gerados por qualquer outra fonte que não tenha
     * vindo através de uma porta de entrada.
     */
    OUTRO
}
