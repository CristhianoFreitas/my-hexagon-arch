package br.org.pessoal.adapter.in.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.pessoal.adapter.in.rest.dto.MercadoriaDTO;
import br.org.pessoal.adapter.in.rest.mapper.MercadoriaMapper;
import br.org.pessoal.domain.port.in.EscritaMercadoriasUseCase;
import br.org.pessoal.domain.port.in.LeituraMercadoriasUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mercadorias")
@RequiredArgsConstructor
public class MercadoriaController {

    private final LeituraMercadoriasUseCase leituraMercadoriasUseCase;

    private final EscritaMercadoriasUseCase escritaMercadoriasUseCase;

    private final MercadoriaMapper mapper;

    @PostMapping
    public ResponseEntity<Void> criar(
        @RequestBody final MercadoriaDTO request) {

        final var entrada = mapper.mapearParaMercadoria(request);
        escritaMercadoriasUseCase.criar(entrada);
        return status(CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MercadoriaDTO> recuperarPorId(
        @PathVariable final Integer id) {

        final var retorno = leituraMercadoriasUseCase.recuperarPorId(id);
        final var response = mapper.mapearParaMercadoriaDTO(retorno);
        return ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MercadoriaDTO>> listar() {

        final var retorno = leituraMercadoriasUseCase.listar();
        final var response = mapper.mapearParaMercadoriasDTO(retorno);
        return ok(response);
    }
}
