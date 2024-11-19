package br.org.pessoal.domain.exception;

public class NaoEncontradoException extends GeralException {

    private static final long serialVersionUID = 1L;

    private NaoEncontradoException(
        String mensagemTecnica,
        String mensagemSimples,
        TipoErroEnum tipoErro,
        CodigoErroEnum codigoErro,
        Throwable throwable) {

        super(mensagemTecnica, mensagemSimples, tipoErro, codigoErro, throwable);
    }

    public NaoEncontradoException(String mensagemSimples, CodigoErroEnum codigoErro) {

        this(mensagemSimples, mensagemSimples, TipoErroEnum.NAO_ENCONTRADO, codigoErro, null);
    }

}
