package br.org.pessoal.adapter.out.persistence.mapper;

import static java.util.Optional.ofNullable;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.org.pessoal.adapter.out.persistence.entity.MercadoriaEntity;
import br.org.pessoal.domain.model.Mercadoria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("br.org.pessoal.adapter.out.rest.mapper.MercadoriaMapper")
@RequiredArgsConstructor
public class MercadoriaMapper {

    private final ModelMapper modelMapper;

    public List<Mercadoria> mapearParaMercadorias(final List<MercadoriaEntity> entities) {

        return ofNullable(entities).map(e -> e.stream().map(this::mapearParaMercadoria).toList()).orElse(null);
    }

    public Mercadoria mapearParaMercadoria(final MercadoriaEntity entity) {

        if (entity == null) {
            return null;
        }
        log.debug("Mapeando.");
        return modelMapper.map(entity, Mercadoria.class);
    }

    public MercadoriaEntity mapearParaMercadoriaEntity(final Mercadoria domain) {

        if (domain == null) {
            return null;
        }
        log.debug("Mapeando.");
        return modelMapper.map(domain, MercadoriaEntity.class);
    }
}