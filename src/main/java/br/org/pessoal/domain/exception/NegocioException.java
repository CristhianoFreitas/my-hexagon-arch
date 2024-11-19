package br.org.pessoal.domain.exception;

public class NegocioException extends GeralException {

    private static final long serialVersionUID = 1L;

    private NegocioException(
        String mensagemTecnica,
        String mensagemSimples,
        TipoErroEnum tipoErro,
        CodigoErroEnum codigoErro,
        Throwable throwable) {

        super(mensagemTecnica, mensagemSimples, tipoErro, codigoErro, throwable);
    }

    public NegocioException(String mensagemSimples, CodigoErroEnum codigoErro) {

        this(mensagemSimples, mensagemSimples, TipoErroEnum.NEGOCIO, codigoErro, null);
    }

}
