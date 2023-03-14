package br.ebr.agenda.security;

import br.ebr.agenda.exception.EntidadeNaoEncontradaException;
import br.ebr.agenda.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var usuario = pacienteRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado " +email));

        return new UserDetailsImpl(usuario);
    }

}
