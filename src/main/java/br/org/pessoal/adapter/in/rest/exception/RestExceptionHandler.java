package br.org.pessoal.adapter.in.rest.exception;

import org.modelmapper.MappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.org.pessoal.domain.exception.GeralException;
import br.org.pessoal.domain.exception.TipoErroEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Resposável por manipular as exceções/erros que possam ocorrer na aplicação
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String CODIGO_ERRO_DESCONHECIDO = "ERRO_DESCONHECIDO";
    public static final String CODIGO_ERRO_SEM_TRATAMENTO = "ERRO_SEM_TRATAMENTO";

    @ExceptionHandler(GeralException.class)
    public ResponseEntity<RestExceptionResponse> handlePlataformaSeSDescidaMainframeException(
            final GeralException exception) {
        log.error("Exceção da aplicação encontrada.", exception);

        return construirResponseEntity(exception);
    }

    @ExceptionHandler({ MappingException.class })
    public ResponseEntity<RestExceptionResponse> handleExceptionSemTratamento(final Exception exception) {
        log.error("Exceção sem tratamento. Utilizando tratamento padrão");

        return construirResponseEntity(CODIGO_ERRO_SEM_TRATAMENTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestExceptionResponse> handleExceptionDesconhecida(final Exception exception) {
        log.error("Exceção desconhecida encontrada. Utilizando tratamento padrão", exception);

        return construirResponseEntity(CODIGO_ERRO_DESCONHECIDO);
    }

    private ResponseEntity<RestExceptionResponse> construirResponseEntity(final String codigoErro) {

        final var restExceptionResponse = new RestExceptionResponse(codigoErro, null);

        final var tipoErroResponse = TipoErroResponseEnum.OUTRO;

        return ResponseEntity.status(tipoErroResponse.getErrohttp()).body(restExceptionResponse);
    }

    private ResponseEntity<RestExceptionResponse> construirResponseEntity(
            final GeralException plataformaSeSDescidaMainframeException) {

        final var restExceptionResponse = new RestExceptionResponse(
                plataformaSeSDescidaMainframeException.getCodigoErro().name(),
                plataformaSeSDescidaMainframeException.getMensagemSimples());

        final var httpStatus = TipoErroResponseEnum.valueOf(plataformaSeSDescidaMainframeException.getTipoErro().name());

        return ResponseEntity.status(httpStatus.getErrohttp()).body(restExceptionResponse);
    }

    /**
     * Representação equivalente na camada de domínio do modelo
     * {@link TipoErroEnum}, atribuindo um {@code HttpStatus} equivalente ao tipo do
     * erro.
     */
    @Getter
    enum TipoErroResponseEnum {

        /**
         * Se refere aos tipos de erro na qual a entrada seja inválida.
         */
        VALIDACAO(HttpStatus.UNPROCESSABLE_ENTITY),
        /**
         * Se refere aos tipos de erro na qual a entrada não foi encontrada.
         */
        NAO_ENCONTRADO(HttpStatus.NOT_FOUND),
        /**
         * Se refere aos tipos de erro na qual a entrada já foi ou esteja sendo
         * processada.
         */
        CONFLITO(HttpStatus.CONFLICT),
        /**
         * Se refere aos tipos de erro gerados por qualquer outra fonte que não tenha
         * vindo através de uma porta de entrada.
         */
        OUTRO(HttpStatus.INTERNAL_SERVER_ERROR);

        private HttpStatus errohttp;

        TipoErroResponseEnum(final HttpStatus errohttp) {
            this.errohttp = errohttp;
        }
    }
}