package br.ebr.agenda.services;


import br.ebr.agenda.domain.Paciente;
import br.ebr.agenda.exception.EntidadeNaoEncontradaException;
import br.ebr.agenda.exception.NegocioException;
import br.ebr.agenda.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        if (Objects.nonNull(paciente.getId())) {
            return repository.save(paciente);
        }

        Optional<Paciente> pacienteCadastrado = repository.findByCpf(paciente.getCpf());

        if (pacienteCadastrado.isPresent()) {
            throw new NegocioException("Paciente já cadastrado no sistema!");
        }

       paciente.setSenha(passwordEncoder2().encode(paciente.getSenha()));
       return repository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Paciente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Paciente não encontrado com este id"));
    }

    public void deletar(Long id) {
        Optional<Paciente> paciente = repository.findById(id);

        if (paciente.isPresent()) {
            repository.deleteById(id);
        }

        else {
            throw new EntidadeNaoEncontradaException("Paciente não encontrado com o id " +id);
        }
    }

    @Bean
    PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }

}