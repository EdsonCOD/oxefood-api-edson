package br.edu.ifpe.oxefood.api.cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpe.oxefood.api.acesso.Perfil;
import br.edu.ifpe.oxefood.api.acesso.PerfilRepository;
import br.edu.ifpe.oxefood.api.acesso.Usuario;
import br.edu.ifpe.oxefood.api.acesso.UsuarioService;
import br.edu.ifpe.oxefood.util.EmailSender;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final UsuarioService usuarioService;
    private final PerfilRepository perfilUsuarioRepository;
    private final EmailSender emailSender;



    public ClienteService(ClienteRepository repository,  UsuarioService usuarioService, PerfilRepository perfilUsuarioRepository, EmailSender emailSender) {
       this.repository = repository;


       this.usuarioService = usuarioService;
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.emailSender = emailSender;


    }

    public Cliente build(ClienteDTO dto) {

        Cliente cliente = null;

      if (dto.getId() == null) { //Montado para o cadastro

         cliente = new Cliente();
         Usuario usuario = new Usuario();
            usuario.setUsername(dto.getEmail());
            usuario.setPassword(dto.getPassword());
            usuario.getRoles().add(new Perfil(Perfil.ROLE_CLIENTE));
            cliente.setUsuario(usuario);
            


      } else { //Consultado para a alteração

         cliente = repository.findById(dto.getId()).get();
      }


        cliente.setNome(dto.getNome());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setCpf(dto.getCpf());
        cliente.setFoneCelular(dto.getFoneCelular());
        cliente.setFoneFixo(dto.getFoneFixo());

        return cliente;
    }

    @Transactional
    public Cliente cadastrar(ClienteDTO dto,Usuario usuarioLogado) {

        Cliente cliente = build(dto);

        usuarioService.save(cliente.getUsuario());

        for (Perfil perfil : cliente.getUsuario().getRoles()) {
            perfil.setHabilitado(Boolean.TRUE);
            perfilUsuarioRepository.save(perfil);
        }

        cliente.setCriadoPor(usuarioLogado);
        cliente.setHabilitado(true);
        Cliente clienteSalvo = repository.save(cliente);
        emailSender.enviarEmailConfirmacaoCadastroCliente(clienteSalvo);
        return clienteSalvo;


    }
    public List<Cliente> listar() {

        return repository.findAll(); //SELECT * FROM cliente
    }

    public Cliente buscarPorId(Long id) {

        return repository.findById(id).get(); //SELECT * FROM Cliente WHERE id = ?
    }

    @Transactional
    public Cliente atualizar(ClienteDTO dto,  Usuario usuarioLogado) {

    Cliente cliente = build(dto);
    cliente.setUltimaModificacaoPor(usuarioLogado);
    return repository.save(cliente);
    }

    @Transactional
   public void remover(Long id) {

        Cliente cliente = repository.findById(id).get();
        cliente.setHabilitado(false);

        repository.save(cliente);
   }



}
