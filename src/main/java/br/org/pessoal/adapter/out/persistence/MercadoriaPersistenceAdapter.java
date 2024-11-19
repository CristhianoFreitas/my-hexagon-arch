package br.org.pessoal.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.org.pessoal.adapter.out.persistence.mapper.MercadoriaMapper;
import br.org.pessoal.adapter.out.persistence.repository.MercadoriaRepository;
import br.org.pessoal.domain.exception.CodigoErroEnum;
import br.org.pessoal.domain.exception.GeralException;
import br.org.pessoal.domain.exception.TipoErroEnum;
import br.org.pessoal.domain.model.Mercadoria;
import br.org.pessoal.domain.port.out.EscritaMercadoriasPort;
import br.org.pessoal.domain.port.out.LeituraMercadoriasPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MercadoriaPersistenceAdapter implements LeituraMercadoriasPort, EscritaMercadoriasPort {

    private final MercadoriaRepository repository;

    private final MercadoriaMapper mapper;

    @Override
    public void criar(final Mercadoria mercadoria) {
        try {
            repository.save(mapper.mapearParaMercadoriaEntity(mercadoria));

        } catch (DataAccessException e) {
             throw new GeralException(e.getMessage(),
             "Falha ao criar mercadoria",
             TipoErroEnum.OUTRO,
             CodigoErroEnum.ERRO_LISTAGEM,
             e);
        }
    }

    @Override
    public List<Mercadoria> listar() {
        try {
            var entities = repository.findAll();
            return mapper.mapearParaMercadorias(entities);

        } catch (DataAccessException e) {
            throw new GeralException(e.getMessage(),
                "Falha na listagem de mercadorias",
                TipoErroEnum.OUTRO,
                CodigoErroEnum.ERRO_LISTAGEM,
                e);
        }
    }

    @Override
    public Optional<Mercadoria> recuperarPorId(final Integer id) {
        try {
            return repository.findById(id).map(mapper::mapearParaMercadoria);

        } catch (DataAccessException e) {
            throw new GeralException(e.getMessage(),
                "Falha ao recuperar mercadoria pelo id",
                TipoErroEnum.OUTRO,
                CodigoErroEnum.ERRO_LISTAGEM,
                e);
        }
    }
}