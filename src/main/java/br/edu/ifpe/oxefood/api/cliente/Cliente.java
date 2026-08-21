package br.edu.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import br.edu.ifpe.oxefood.util.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//JPA {
@Entity
@Table(name = "Cliente")
@SQLRestriction("habilitado = true")
// }

//lombok{
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// }

public class Cliente extends EntidadeAuditavel  {
  
    @Column //JPA
    private String nome;

   @Column
   private LocalDate dataNascimento;

   @Column
   private String cpf;

   @Column
   private String foneCelular;

   @Column
   private String foneFixo;

}

