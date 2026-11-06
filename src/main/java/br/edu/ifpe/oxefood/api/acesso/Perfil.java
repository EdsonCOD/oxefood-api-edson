package br.edu.ifpe.oxefood.api.acesso;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import br.edu.ifpe.oxefood.util.EntidadeNegocio;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
@Entity
@Table(name = "Perfil")
@SQLRestriction("habilitado = true")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil extends EntidadeNegocio implements GrantedAuthority {

   public static final String ROLE_CLIENTE = "CLIENTE";
   public static final String ROLE_FUNCIONARIO_ADMIN = "ROLE_FUNCIONARIO_ADMIN"; // READ, DELETE, WRITE, UPDATE.
   public static final String ROLE_FUNCIONARIO_USER = "ROLE_FUNCIONARIO_USER"; // READ, WRITE, UPDATE.

   @Column
   private String nome;

   @Override
   public String getAuthority() {
       return this.nome;
   }

}
