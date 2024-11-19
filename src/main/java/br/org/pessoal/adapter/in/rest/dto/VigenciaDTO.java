package br.org.pessoal.adapter.in.rest.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VigenciaDTO {

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("dataInicialCompetencia")
    private LocalDate dtInicial;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("dataFinalCompetencia")
    private LocalDate dtFinal;

}