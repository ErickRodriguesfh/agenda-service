package br.ebr.agenda.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PacienteDTO {

    private Long id;

    @NotBlank(message = "Nome do paciente é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome do paciente é obrigatório")
    private String sobrenome;


    private String email;

    @NotBlank(message = "CPF do paciente é obrigatório")
    private String cpf;

    @NotBlank(message = "A senha é obrigatória") //refatorar isso pois se o usuário estiver atualizando suas
    // informações a senha não mudar ele dara erro
    private String senha;

}