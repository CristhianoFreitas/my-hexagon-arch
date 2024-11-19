package br.org.pessoal.adapter.in.rest.exception;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;

/**
 * Representação de um response de erro.
 */
@Getter
public class RestExceptionResponse {

    private ZonedDateTime timestamp;

    @JsonInclude(Include.NON_NULL)
    private Collection<ErroResponse> errosServidor = null;

    @JsonInclude(Include.NON_NULL)
    private Collection<ErroResponse> errosValidacao = null;

    @JsonInclude(Include.NON_NULL)
    private Collection<ErroResponse> errosNegocio = null;

    private RestExceptionResponse() {

        this.timestamp = ZonedDateTime.now();
    }

    public RestExceptionResponse(final String codigoErro, final String detalhes) {

        this();
        final var erroResponse = new ErroResponse(null, codigoErro, detalhes);
        this.errosServidor = Collections.singleton(erroResponse);
    }

    public RestExceptionResponse(Collection<ErroResponse> errosValidacao) {

        this();
        this.errosValidacao = errosValidacao;
    }

    public RestExceptionResponse(ErroResponse erroNegocio) {

        this();
        this.errosNegocio = Collections.singleton(erroNegocio);
    }

    @JsonInclude(Include.NON_NULL)
    @Getter
    static class ErroResponse {

        public static final Comparator<ErroResponse> ORDEM_CRESCENTE_INDICE = Comparator
            .comparing(ErroResponse::getIndice, Comparator.nullsLast(Comparator.naturalOrder()));

        private String campo;
        private String codigoErro;
        private String detalhes;
        @JsonIgnore
        private Integer indice;

        ErroResponse(String codigoErro) {

            this.codigoErro = codigoErro;
        }

        ErroResponse(String campo, String codigoErro, String detalhes) {

            this.campo = campo;
            this.codigoErro = codigoErro;
            this.detalhes = detalhes;
        }

        ErroResponse(String campo, String codigoErro, String detalhes, Integer indice) {

            this(campo, codigoErro, detalhes);
            this.indice = indice;
        }

    }

}