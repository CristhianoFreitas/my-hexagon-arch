package br.org.pessoal.domain.model;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Vigencia {

    @NotNull
    private LocalDate dtInicial;

    @NotNull
    private LocalDate dtFinal;

    @AssertTrue(message = "Data inicial está depois da data final.")
    public boolean isDataFinalDepoisDaInicial() {
        return !dtFinal.isBefore(dtInicial);
    }
}