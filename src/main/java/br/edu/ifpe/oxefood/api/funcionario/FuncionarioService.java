package br.edu.ifpe.oxefood.api.funcionario;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifpe.oxefood.api.acesso.Perfil;
import br.edu.ifpe.oxefood.api.acesso.PerfilRepository;
import br.edu.ifpe.oxefood.api.acesso.Usuario;
import br.edu.ifpe.oxefood.api.acesso.UsuarioService;
import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final UsuarioService usuarioService;
    private final PerfilRepository perfilUsuarioRepository;


    public FuncionarioService(FuncionarioRepository repository, UsuarioService usuarioService, PerfilRepository perfilUsuarioRepository) {

        this.repository = repository;
        this.usuarioService = usuarioService;
        this.perfilUsuarioRepository = perfilUsuarioRepository;

    }

    public Funcionario build(FuncionarioDTO dto) {

        Funcionario funcionario = null;

        if (dto.getId() == null) { // Montado para o cadastro

            funcionario = new Funcionario();
            Usuario usuario = new Usuario();
            usuario.setUsername(dto.getEmail());
            usuario.setPassword(dto.getPassword());

            if (dto.getTipo().equals(TipoFuncionario.ADMINISTRADOR)) {
                usuario.getRoles().add(new Perfil(Perfil.ROLE_FUNCIONARIO_ADMIN));
            } else if (dto.getTipo().equals(TipoFuncionario.OPERADOR)) {
                usuario.getRoles().add(new Perfil(Perfil.ROLE_FUNCIONARIO_USER));
            }

            funcionario.setUsuario(usuario);


        } else { // Consultado para a alteração

            funcionario = repository.findById(dto.getId()).get();
        }

        funcionario.setTipo(dto.getTipo());
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setRg(dto.getRg());
        funcionario.setDataNascimento(dto.getDataNascimento());
        funcionario.setFoneCelular(dto.getFoneCelular());
        funcionario.setFoneFixo(dto.getFoneFixo());
        funcionario.setSalario(dto.getSalario());
        funcionario.setEnderecoRua(dto.getEnderecoRua());
        funcionario.setEnderecoNumero(dto.getEnderecoNumero());
        funcionario.setEnderecoBairro(dto.getEnderecoBairro());
        funcionario.setEnderecoCidade(dto.getEnderecoCidade());
        funcionario.setEnderecoCep(dto.getEnderecoCep());
        funcionario.setEnderecoUf(dto.getEnderecoUf());
        funcionario.setEnderecoComplemento(dto.getEnderecoComplemento());

        return funcionario;
    }

    @Transactional
    public Funcionario cadastrar(FuncionarioDTO dto) {

        Funcionario funcionario = build(dto);
         usuarioService.save(funcionario.getUsuario());

        for (Perfil perfil : funcionario.getUsuario().getRoles()) {
            perfil.setHabilitado(Boolean.TRUE);
            perfilUsuarioRepository.save(perfil);
        }

        funcionario.setHabilitado(true);
        return repository.save(funcionario);
    }

    public List<Funcionario> listar() {

        return repository.findAll();
    }

    public Funcionario buscarPorId(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
    public Funcionario atualizar(FuncionarioDTO dto) {

        Funcionario funcionario = build(dto);
        return repository.save(funcionario);
    }

    @Transactional
    public void remover(Long id) {

        Funcionario funcionario = repository.findById(id).get();
        funcionario.setHabilitado(false);
        repository.save(funcionario);
    }

}