package br.ebr.agenda.repository;

import br.ebr.agenda.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    Optional<Agenda> findByHorario(LocalDateTime horario);

}