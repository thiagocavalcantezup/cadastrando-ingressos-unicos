package br.com.zup.handora.cadastrandoingressosunicos.ingresso;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(IngressoController.BASE_URI)
public class IngressoController {

    public final static String BASE_URI = "/ingressos";

    private final IngressoRepository ingressoRepository;

    public IngressoController(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid IngressoRequest ingressoRequest,
                                    UriComponentsBuilder ucb) {
        if (ingressoRepository.existsByNumeroAndDataHoraEvento(
            ingressoRequest.getNumero(), ingressoRequest.getDataHoraEvento()
        )) {
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Já existe um ingresso cadastrado com esse número para esse evento."
            );
        }

        Ingresso ingresso = ingressoRepository.save(ingressoRequest.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(ingresso.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
