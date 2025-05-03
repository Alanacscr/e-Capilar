package unitins.br.tp1.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.EstadoDTO;
import unitins.br.tp1.dto.EstadoResponseDTO;
import unitins.br.tp1.model.Estado;
import unitins.br.tp1.repository.EstadoRepository;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Override
    @Transactional
    public EstadoResponseDTO create(EstadoDTO estado) {
        Estado novoEstado = new Estado();
        novoEstado.setSigla(estado.sigla());
        novoEstado.setNome(estado.nome());
        
        // realizando inclusao
        estadoRepository.persist(novoEstado);

        return EstadoResponseDTO.valueOf(novoEstado);
    }

    @Override
    @Transactional
    public void update(long id, EstadoDTO estado) {
        Estado edicaoEstado = estadoRepository.findById(id);

        edicaoEstado.setSigla(estado.sigla());
        edicaoEstado.setNome(estado.nome());
    }

    @Override
    @Transactional
    public void delete(long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public EstadoResponseDTO findById(long id) {
        return EstadoResponseDTO.valueOf(estadoRepository.findById(id));
    }

    @Override
    public EstadoResponseDTO findBySigla(String sigla) {
        return EstadoResponseDTO.valueOf(estadoRepository.findBySigla(sigla));
    }

    @Override
    public List<EstadoResponseDTO> findAll() {
        return estadoRepository.findAll().stream().map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

}
