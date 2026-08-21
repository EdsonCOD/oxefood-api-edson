package br.edu.ifpe.oxefood.api.cliente;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
       this.repository = repository;
    }

    public Cliente build(ClienteDTO dto) {

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setCpf(dto.getCpf());
        cliente.setFoneCelular(dto.getFoneCelular());
        cliente.setFoneFixo(dto.getFoneFixo());

        return cliente;
    }

    @Transactional
    public Cliente cadastrar(ClienteDTO dto) {

        Cliente cliente = build(dto);
        cliente.setHabilitado(true);
        return repository.save(cliente);
    }
}
