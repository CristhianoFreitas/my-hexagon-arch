package br.org.pessoal.domain.exception;

import lombok.Getter;

/**
 * Tipo de exceção base a ser lançada pelo domínio da aplicação.
 *
 */
@SuppressWarnings("serial")
@Getter
public class GeralException extends RuntimeException {

    private final TipoErroEnum tipoErro;
    private final CodigoErroEnum codigoErro;
    private final String mensagemSimples;

    public GeralException(
            final String mensagemTecnica,
            final String mensagemSimples,
            final TipoErroEnum tipoErro,
            final CodigoErroEnum codigoErro,
            final Throwable throwable) {

        super(mensagemTecnica, throwable);
        this.tipoErro = tipoErro;
        this.codigoErro = codigoErro;
        this.mensagemSimples = mensagemSimples;
    }
}
