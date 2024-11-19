package br.org.pessoal.domain.port.in;

import java.util.List;

import br.org.pessoal.domain.model.Mercadoria;

public interface LeituraMercadoriasUseCase {

    List<Mercadoria> listar();

    Mercadoria recuperarPorId(Integer id);
}
