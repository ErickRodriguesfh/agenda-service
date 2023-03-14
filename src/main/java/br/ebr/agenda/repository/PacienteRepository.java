package br.ebr.agenda.repository;

import br.ebr.agenda.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findByCpf(String cpf);

    Optional<Paciente> findOneByEmail(String email);

}