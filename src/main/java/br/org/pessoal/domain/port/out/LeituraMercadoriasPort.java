package br.org.pessoal.domain.port.out;

import java.util.List;
import java.util.Optional;

import br.org.pessoal.domain.model.Mercadoria;

public interface LeituraMercadoriasPort {

    List<Mercadoria> listar();

    Optional<Mercadoria> recuperarPorId(Integer id);
}
