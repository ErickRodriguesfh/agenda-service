package br.ebr.agenda.controller;

import br.ebr.agenda.domain.Paciente;
import br.ebr.agenda.dto.PacienteDTO;
import br.ebr.agenda.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService service;
    private final ModelMapper mapper;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public PacienteDTO salvar(@RequestBody @Valid PacienteDTO paciente) {
        final var pacienteSalvo = service.salvar(mapper.map(paciente, Paciente.class));
        return mapper.map(pacienteSalvo, PacienteDTO.class);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping
    public List<PacienteDTO> buscarTodos() {
        return service.listarTodos().stream().map(paciente -> mapper.map(paciente, PacienteDTO.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/{id}")
    public PacienteDTO buscarPorId(@PathVariable Long id) {
        final var pacienteRetornado = service.buscarPorId(id);
        return mapper.map(pacienteRetornado, PacienteDTO.class);
    }

    @PutMapping
    public PacienteDTO atualizar(@RequestBody PacienteDTO paciente) {
        final var pacienteSalvo = service.salvar(mapper.map(paciente, Paciente.class));
        return mapper.map(pacienteSalvo, PacienteDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

}