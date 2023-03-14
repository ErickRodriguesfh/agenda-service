package br.ebr.agenda.controller;

import br.ebr.agenda.domain.Agenda;
import br.ebr.agenda.dto.AgendaDTO;
import br.ebr.agenda.services.AgendaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService service;
    private final ModelMapper mapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AgendaDTO> buscarTodos() {
        return service.buscarTodos().stream().map(agenda -> mapper.map(agenda, AgendaDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AgendaDTO buscarPorId(@PathVariable Long id) {
        final var agendaRetornada = service.buscarPorId(id);
        return mapper.map(agendaRetornada, AgendaDTO.class);
    }

    @PostMapping
    public AgendaDTO salvar(@Valid @RequestBody AgendaDTO agendaDTO) {
        final var agendaSalva = service.salvar(mapper.map(agendaDTO, Agenda.class));
        return mapper.map(agendaSalva, AgendaDTO.class);
    }

}