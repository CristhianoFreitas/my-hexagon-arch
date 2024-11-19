package br.org.pessoal.adapter.in.rest.mapper;

import static java.util.Optional.ofNullable;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.org.pessoal.adapter.in.rest.dto.MercadoriaDTO;
import br.org.pessoal.domain.model.Mercadoria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("br.org.pessoal.adapter.in.rest.mapper.MercadoriaMapper")
@RequiredArgsConstructor
public class MercadoriaMapper {

    private final ModelMapper modelMapper;

    public Mercadoria mapearParaMercadoria(final MercadoriaDTO dto) {

        if (dto == null) {
            return null;
        }
        log.debug("Mapeando.");
        return modelMapper.map(dto, Mercadoria.class);
    }

    public List<MercadoriaDTO> mapearParaMercadoriasDTO(final List<Mercadoria> entities) {

        return ofNullable(entities).map(e -> e.stream().map(this::mapearParaMercadoriaDTO).toList()).orElse(null);
    }

    public MercadoriaDTO mapearParaMercadoriaDTO(final Mercadoria dominio) {

        if (dominio == null) {
            return null;
        }
        log.debug("Mapeando.");
        return modelMapper.map(dominio, MercadoriaDTO.class);
    }
}
