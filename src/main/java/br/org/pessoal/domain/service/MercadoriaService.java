package br.org.pessoal.domain.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.org.pessoal.domain.exception.CodigoErroEnum;
import br.org.pessoal.domain.exception.NaoEncontradoException;
import br.org.pessoal.domain.exception.NegocioException;
import br.org.pessoal.domain.model.Mercadoria;
import br.org.pessoal.domain.port.in.EscritaMercadoriasUseCase;
import br.org.pessoal.domain.port.in.LeituraMercadoriasUseCase;
import br.org.pessoal.domain.port.out.EscritaMercadoriasPort;
import br.org.pessoal.domain.port.out.LeituraMercadoriasPort;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MercadoriaService implements LeituraMercadoriasUseCase, EscritaMercadoriasUseCase {

    private final Validator validator;

    private final LeituraMercadoriasPort leituraMercadoriasPort;

    private final EscritaMercadoriasPort escritaMercadoriasPort;

    @Override
    public void criar(final Mercadoria mercadoria) {
        log.info("Iniciando serviço de criar mercadoria.");

        Set<ConstraintViolation<Mercadoria>> errosCV = validator.validate(mercadoria);

        errosCV.forEach(erro -> System.out.println(erro.getMessage()));

        if (leituraMercadoriasPort.recuperarPorId(mercadoria.getId()).isPresent()) {
            throw new NegocioException("Mercadoria já existe no banco.", CodigoErroEnum.ERRO_PERSISTENCIA);
        }
        escritaMercadoriasPort.criar(mercadoria);
    }

    @Override
    public Mercadoria recuperarPorId(final Integer id) {
        log.info("Iniciando serviço de recuperar mercadoria pelo id {}.", id);

        return leituraMercadoriasPort.recuperarPorId(id)
            .orElseThrow(() -> new NaoEncontradoException("Mercadoria não encontrada.", CodigoErroEnum.ERRO_CONSULTA));
    }

    @Override
    public List<Mercadoria> listar() {
        log.info("Iniciando serviço de listar mercadoria.");

        return leituraMercadoriasPort.listar();
    }

}
