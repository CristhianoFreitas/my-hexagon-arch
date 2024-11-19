package br.org.pessoal.adapter.in.rest.exception;

import static java.util.stream.Collectors.toCollection;

import java.util.List;
import java.util.TreeSet;

import org.modelmapper.MappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.org.pessoal.adapter.in.rest.exception.RestExceptionResponse.ErroResponse;
import br.org.pessoal.domain.exception.GeralException;
import br.org.pessoal.domain.exception.NegocioException;
import br.org.pessoal.domain.exception.TipoErroEnum;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import jakarta.validation.Path.Node;
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
    public static final String CODIGO_ERRO_JSON_MALFORMADO = "ERRO_JSON_MALFORMADO";

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
        final HttpMessageNotReadableException ex,
        final HttpHeaders headers,
        final HttpStatusCode status,
        final WebRequest request) {

        log.error("Exceção de JSON mal formado.", ex);

        final var errosValidacao = List.of(new ErroResponse(null, CODIGO_ERRO_JSON_MALFORMADO, ex.getLocalizedMessage()));

        return ResponseEntity.status(TipoErroResponseEnum.ESTRUTURAL.getErrohttp()).body(new RestExceptionResponse(errosValidacao));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

        log.error(ex.getMessage(), ex);

        final var errosValidacao = ex.getFieldErrors().stream().map(this::construirErroResponseValidacao).toList();
        return ResponseEntity.status(TipoErroResponseEnum.VALIDACAO.getErrohttp()).body(new RestExceptionResponse(errosValidacao));
    }

    private ErroResponse construirErroResponseValidacao(FieldError fe) {

        final var codigoErro = fe.isBindingFailure() ? CODIGO_ERRO_DESCONHECIDO : fe.getDefaultMessage();
        return new ErroResponse(fe.getField(), codigoErro, fe.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestExceptionResponse> handleConstraintViolationException(
        final ConstraintViolationException ex) {

        log.error(ex.getMessage(), ex);

        final var errosValidacao = ex.getConstraintViolations()
            .stream()
            .map(this::construirErroResponseValidacao)
            .collect(toCollection(() -> new TreeSet<>(ErroResponse.ORDEM_CRESCENTE_INDICE)));
        return ResponseEntity.status(TipoErroResponseEnum.VALIDACAO.getErrohttp()).body(new RestExceptionResponse(errosValidacao));
    }

    private ErroResponse construirErroResponseValidacao(
        ConstraintViolation<?> cv) {

        Node pai = null;
        Node filho = null;

        for (final Node node : cv.getPropertyPath()) {
            if (ElementKind.CONTAINER_ELEMENT.equals(node.getKind())) {
                filho = node;
                break;
            } else {
                pai = node;
            }
        }
        return new ErroResponse(pai != null ? pai.toString() : null, cv.getMessage(), null, filho != null ? filho.getIndex() : null);
    }

    @ExceptionHandler(GeralException.class)
    public ResponseEntity<RestExceptionResponse> handleEnquadramentoException(
        GeralException ex) {

        log.error("Exceção da aplicação encontrada.", ex);

        return construirResponseEntity(ex);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<RestExceptionResponse> handleNegocioException(
        NegocioException ex) {

        log.warn("Exceção de negócio encontrada.", ex);

        return construirResponseEntityParaErrosDeNegocio(ex);
    }

    @ExceptionHandler({MappingException.class})
    public ResponseEntity<RestExceptionResponse> handleExceptionSemTratamento(
        Exception ex) {

        log.error("Exceção sem tratamento. Utilizando tratamento padrão.", ex);

        return construirResponseEntity(CODIGO_ERRO_SEM_TRATAMENTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestExceptionResponse> handleExceptionDesconhecida(
        Exception ex) {

        log.error("Exceção desconhecida encontrada. Utilizando tratamento padrão.", ex);

        return construirResponseEntity(CODIGO_ERRO_DESCONHECIDO);
    }

    private ResponseEntity<RestExceptionResponse> construirResponseEntityParaErrosDeNegocio(
        final NegocioException ex) {

        final var restExceptionResponse = new RestExceptionResponse(new ErroResponse(ex.getCodigoErro().name()));

        final var httpStatus = TipoErroResponseEnum.valueOf(ex.getTipoErro().name());

        return ResponseEntity.status(httpStatus.getErrohttp()).body(restExceptionResponse);
    }

    private ResponseEntity<RestExceptionResponse> construirResponseEntity(
        String codigoErro) {

        final var restExceptionResponse = new RestExceptionResponse(codigoErro, null);

        final var tipoErroResponse = TipoErroResponseEnum.OUTRO;

        return ResponseEntity.status(tipoErroResponse.getErrohttp()).body(restExceptionResponse);
    }

    private ResponseEntity<RestExceptionResponse> construirResponseEntity(
        final GeralException ex) {

        final var restExceptionResponse = new RestExceptionResponse(ex.getCodigoErro().name(), ex.getMensagemSimples());

        final var httpStatus = TipoErroResponseEnum.valueOf(ex.getTipoErro().name());

        return ResponseEntity.status(httpStatus.getErrohttp()).body(restExceptionResponse);
    }

    /**
     * Representação equivalente na camada de domínio do modelo {@link TipoErroEnum}, atribuindo um
     * {@code HttpStatus} equivalente ao tipo do erro.
     */
    @Getter
    enum TipoErroResponseEnum {

        /**
         * Se refere aos tipos de erro onde a requisição está estruturalmente incorreta (JSON
         * faltando colchetes, chaves ou vírgulas, por exemplo).
         */
        ESTRUTURAL(HttpStatus.BAD_REQUEST),
        /**
         * Se refere aos tipos de erro na qual tem relação com o negócio.
         */
        NEGOCIO(HttpStatus.BAD_REQUEST),
        /**
         * Se refere aos tipos de erro na qual a entrada seja inválida.
         */
        VALIDACAO(HttpStatus.UNPROCESSABLE_ENTITY),
        /**
         * Se refere aos tipos de erro na qual a entrada não foi encontrada.
         */
        NAO_ENCONTRADO(HttpStatus.NOT_FOUND),
        /**
         * Se refere aos tipos de erro na qual a entrada já foi ou esteja sendo processada.
         */
        CONFLITO(HttpStatus.CONFLICT),
        /**
         * Se refere aos tipos de erro gerados por qualquer outra fonte que não tenha vindo através
         * de uma porta de entrada.
         */
        OUTRO(HttpStatus.INTERNAL_SERVER_ERROR);

        private final HttpStatus errohttp;

        TipoErroResponseEnum(HttpStatus errohttp) {

            this.errohttp = errohttp;
        }

    }
}
