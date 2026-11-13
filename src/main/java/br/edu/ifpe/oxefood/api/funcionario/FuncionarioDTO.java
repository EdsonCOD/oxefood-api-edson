package br.edu.ifpe.oxefood.api.funcionario;

import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipo;

    @NotBlank
    private String email;

    @NotBlank
    private String password;


    @NotBlank
    private String nome;

    private String cpf;

    private String rg;

    private LocalDate dataNascimento;

    private String foneCelular;

    private String foneFixo;

    private Double salario;

    private String enderecoRua;

    private String enderecoNumero;

    private String enderecoBairro;

    private String enderecoCidade;

    private String enderecoCep;

    private String enderecoUf;

    private String enderecoComplemento;

}