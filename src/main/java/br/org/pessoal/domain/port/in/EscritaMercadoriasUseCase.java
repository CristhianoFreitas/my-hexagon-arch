package br.org.pessoal.domain.port.in;

import org.springframework.validation.annotation.Validated;

import br.org.pessoal.domain.model.Mercadoria;
import jakarta.validation.Valid;

@Validated
public interface EscritaMercadoriasUseCase {

    void criar(@Valid Mercadoria mercadoria);

}