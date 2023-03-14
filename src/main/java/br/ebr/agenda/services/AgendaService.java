package br.ebr.agenda.services;

import br.ebr.agenda.domain.Agenda;
import br.ebr.agenda.exception.EntidadeNaoEncontradaException;
import br.ebr.agenda.exception.NegocioException;
import br.ebr.agenda.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;

    public List<Agenda> buscarTodos() {
        return repository.findAll();
    }

    public Agenda buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Agenda não encontrada com este id"));
    }

    public Agenda salvar(Agenda agenda) {
        final var paciente = pacienteService.buscarPorId(agenda.getPaciente().getId());
        final var horario = agenda.getHorario();
        final var horarioJaDisponivel = repository.findByHorario(horario);

        if (horarioJaDisponivel.isPresent()) {
            throw new NegocioException("Já existe agendamento para este horário");
        }

        agenda.setPaciente(paciente);
        agenda.setDataCriacao(LocalDateTime.now());

        return repository.save(agenda);
    }

}