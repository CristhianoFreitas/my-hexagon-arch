package br.org.pessoal.adapter.in.rest.exception;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

/**
 * Representação de um response de erro.
 */
@Getter
public class RestExceptionResponse {

    private final ZonedDateTime timestamp;
    private final Collection<CodigoErroResponse> errosServidor;

    public RestExceptionResponse(final String codigoErro, final String detalhes) {

        this.timestamp = ZonedDateTime.now();

        final var codigoErroResponse = new CodigoErroResponse(codigoErro, detalhes);

        this.errosServidor = Collections.singleton(codigoErroResponse);
    }

    @JsonInclude(Include.NON_NULL)
    record CodigoErroResponse(String codigoErro, String detalhes) {
    }

}