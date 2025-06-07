package unitins.br.tp1.service.Endereco;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.Endereco.EnderecoDTO;
import unitins.br.tp1.dto.Endereco.EnderecoResponseDTO;
import unitins.br.tp1.model.Endereco.Endereco;
import unitins.br.tp1.model.Endereco.Municipio;
import unitins.br.tp1.repository.Endereco.EnderecoRepository;
import unitins.br.tp1.repository.Endereco.MunicipioRepository;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    MunicipioRepository municipioRepository;

    @Override
    @Transactional
    public EnderecoResponseDTO create(EnderecoDTO dto) {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setLogradouro(dto.logradouro());
        novoEndereco.setNumero(dto.numero());
        novoEndereco.setBairro(dto.bairro());
        novoEndereco.setCep(dto.cep());
        novoEndereco.setComplemento(dto.complemento());

        // buscando o Municipio pelo id
        Municipio municipio = municipioRepository.findById(dto.idMunicipio());

        novoEndereco.setMunicipio(municipio);

        // realizando inclusao
        enderecoRepository.persist(novoEndereco);

        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public void update(long id, EnderecoDTO dto) {
        Endereco edicaoEndereco = enderecoRepository.findById(id);

        edicaoEndereco.setLogradouro(dto.logradouro());
        edicaoEndereco.setNumero(dto.numero());
        edicaoEndereco.setBairro(dto.bairro());
        edicaoEndereco.setCep(dto.cep());
        edicaoEndereco.setComplemento(dto.complemento());

        // buscando o Municipio pelo id
        Municipio municipio = municipioRepository.findById(dto.idMunicipio());
        edicaoEndereco.setMunicipio(municipio);
    }

    @Override
    @Transactional
    public void delete(long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public EnderecoResponseDTO findById(long id) {
        return EnderecoResponseDTO.valueOf(enderecoRepository.findById(id));
    }

    @Override
    public List<EnderecoResponseDTO> findByBairro(String bairro) {
        return enderecoRepository.findByBairro(bairro)
        .stream().map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EnderecoResponseDTO> findAll() {
        return enderecoRepository.findAll().stream().map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EnderecoResponseDTO> findByMunicipio(Long idMunicipio) {
        return enderecoRepository.findByMunicipio(idMunicipio)
        .stream().map(e -> EnderecoResponseDTO.valueOf(e)).toList();
    }
    
}