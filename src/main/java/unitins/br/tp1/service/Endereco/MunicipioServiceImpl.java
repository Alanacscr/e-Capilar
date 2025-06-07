package unitins.br.tp1.service.Endereco;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.Endereco.MunicipioDTO;
import unitins.br.tp1.dto.Endereco.MunicipioResponseDTO;
import unitins.br.tp1.model.Endereco.Estado;
import unitins.br.tp1.model.Endereco.Municipio;
import unitins.br.tp1.repository.Endereco.EstadoRepository;
import unitins.br.tp1.repository.Endereco.MunicipioRepository;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {

    @Inject
    MunicipioRepository municipioRepository;
    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public MunicipioResponseDTO create(MunicipioDTO dto) {
        Municipio novoMunicipio = new Municipio();
        novoMunicipio.setNome(dto.nome());

        //buscando o estado pelo id
        Estado estado = estadoRepository.findById(dto.idEstado());

        novoMunicipio.setEstado(estado);

        // realizando inclusao
        municipioRepository.persist(novoMunicipio);

        return MunicipioResponseDTO.valueOf(novoMunicipio);
    }

    @Override
    @Transactional
    public void update(long id, MunicipioDTO dto) {
        Municipio edicaoMunicipio = municipioRepository.findById(id);

        edicaoMunicipio.setNome(dto.nome());
        // buscando o estado pelo id
        Estado estado = estadoRepository.findById(dto.idEstado());
        edicaoMunicipio.setEstado(estado);
    }

    @Override
    @Transactional
    public void delete(long id) {
        municipioRepository.deleteById(id);
    }

    @Override
    public MunicipioResponseDTO findById(long id) {
        return MunicipioResponseDTO.valueOf(municipioRepository.findById(id));
    }

    @Override
    public List<MunicipioResponseDTO> findByEstado(Long idEstado) {
        return municipioRepository.findByEstado(idEstado)
        .stream().map(e -> MunicipioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MunicipioResponseDTO> findByNome(String nome) {
        return municipioRepository.findByNome(nome)
                .stream().map(e -> MunicipioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<MunicipioResponseDTO> findAll() {
        return municipioRepository.findAll().stream().map(e -> MunicipioResponseDTO.valueOf(e)).toList();
    }

}