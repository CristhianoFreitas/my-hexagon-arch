package br.org.pessoal.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.pessoal.domain.model.Mercadoria;
import br.org.pessoal.domain.port.in.EscritaMercadoriasUseCase;
import br.org.pessoal.domain.port.in.LeituraMercadoriasUseCase;
import br.org.pessoal.domain.port.out.EscritaMercadoriasPort;
import br.org.pessoal.domain.port.out.LeituraMercadoriasPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MercadoriaService implements LeituraMercadoriasUseCase, EscritaMercadoriasUseCase {

    private final LeituraMercadoriasPort leituraMercadoriasPort;

    private final EscritaMercadoriasPort escritaMercadoriasPort;

    @Override
    public void criar(final Mercadoria mercadoria) {

        if (leituraMercadoriasPort.recuperarPorId(mercadoria.getId()).isPresent()) {
            log.error("Mercadoria já existe no banco");
        }
        escritaMercadoriasPort.criar(mercadoria);
    }

    @Override
    public Mercadoria recuperarPorId(final Integer id) {

        // TODO
        return leituraMercadoriasPort.recuperarPorId(id).orElseThrow(null);

    }

    @Override
    public List<Mercadoria> listar() {

        return leituraMercadoriasPort.listar();
    }

}
