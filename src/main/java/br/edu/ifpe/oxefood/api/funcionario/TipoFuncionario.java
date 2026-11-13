package br.edu.ifpe.oxefood.api.funcionario;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoFuncionario {

    ADMINISTRADOR(
            "Administrador"),
    OPERADOR(
            "Operador");

    private String tipo;

    private TipoFuncionario(String tipo) {
        this.tipo = tipo;
    }

    @JsonValue
    public String getTipo() {
        return tipo;
    }

}