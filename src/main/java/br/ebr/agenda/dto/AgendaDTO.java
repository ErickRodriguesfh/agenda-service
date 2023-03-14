package br.ebr.agenda.dto;

import br.ebr.agenda.domain.Paciente;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class AgendaDTO {

    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime horario;

    private LocalDateTime dataCriacao;

    private Paciente paciente;

}