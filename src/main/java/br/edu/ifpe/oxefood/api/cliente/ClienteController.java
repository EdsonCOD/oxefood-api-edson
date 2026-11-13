//class de endpoints
package br.edu.ifpe.oxefood.api.cliente;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpe.oxefood.api.acesso.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
@Tag(
   name = "API Cliente",
   description = "API responsável pelos servidos de cliente no sistema"
)
public class ClienteController {

    private final ClienteService clienteService;
    private final UsuarioService usuarioService;


    public ClienteController(ClienteService service, UsuarioService usuarioService) {
        this.clienteService = service;
        this.usuarioService = usuarioService;

    }
  @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody @Valid ClienteDTO dto, HttpServletRequest request) {

        Cliente clienteCadastrado = clienteService.cadastrar(dto, usuarioService.obterUsuarioLogado(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {

        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizar(@RequestBody @Valid ClienteDTO dto, HttpServletRequest request) {

	    Cliente clienteAtualizado = clienteService.atualizar(dto, usuarioService.obterUsuarioLogado(request));
	    return ResponseEntity.ok(clienteAtualizado);
    }
    
    @DeleteMapping("/{id}")
   public ResponseEntity<Void> remover(@PathVariable Long id) {

        clienteService.remover(id);
        return ResponseEntity.noContent().build();
   }



}

