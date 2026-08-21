//class de endpoints
package br.edu.ifpe.oxefood.api.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService service) {
        this.clienteService = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody ClienteDTO dto) {

        Cliente clienteCadastrado = clienteService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
    }

}

