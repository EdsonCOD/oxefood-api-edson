package br.edu.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import br.edu.ifpe.oxefood.util.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//JPA {
@Entity
@Table(name = "Produto")
@SQLRestriction("habilitado = true")
// }

//lombok{
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// }

public class Produto extends EntidadeAuditavel  {
  
   @ManyToOne
private Empresa empresa;

@ManyToOne
private CategoriaProduto categoria;

@Column
private String codigo;

@Column
private String titulo;

@Column
private String descricao;

@Column
private Double valorUnitario;

@Column
private Integer tempoEntregaMinimo;

@Column
private Integer tempoEntregaMaximo;

}


