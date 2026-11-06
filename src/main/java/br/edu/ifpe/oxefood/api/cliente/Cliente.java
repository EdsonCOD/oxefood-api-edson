package br.edu.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;

import br.edu.ifpe.oxefood.api.acesso.Usuario;
import br.edu.ifpe.oxefood.util.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

  
    @Column(nullable = false, length = 100)
    private String nome;

   @Column
   private LocalDate dataNascimento;

   @Column(unique = true)
   private String cpf;

   @Column
   private String foneCelular;

   @Column
   private String foneFixo;

}

