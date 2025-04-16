package unitins.br.tp1.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.br.tp1.dto.TelefoneDTO;
import unitins.br.tp1.dto.TelefoneResponseDTO;
import unitins.br.tp1.model.Telefone;
import unitins.br.tp1.repository.TelefoneRepository;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {

    @Inject
    TelefoneRepository telefoneRepository;

    @Override
    @Transactional
    public TelefoneResponseDTO create(TelefoneDTO telefone) {
        Telefone novoTelefone = new Telefone();
        novoTelefone.setCodigoArea(telefone.codigoArea());
        novoTelefone.setNumero(telefone.numero());

        telefoneRepository.persist(novoTelefone);

        return TelefoneResponseDTO.valueOf(novoTelefone);
    }

    @Override
    @Transactional
    public void update(long id, TelefoneDTO telefone) {
        Telefone edicaoTelefone = telefoneRepository.findById(id);
        edicaoTelefone.setCodigoArea(telefone.codigoArea());
        edicaoTelefone.setNumero(telefone.numero());
    }

    @Override
    @Transactional
    public void delete(long id) {
        telefoneRepository.deleteById(id);
    }

    @Override
    public TelefoneResponseDTO findById(long id) {
        return TelefoneResponseDTO.valueOf(telefoneRepository.findById(id));
    }

    @Override
    public TelefoneResponseDTO findByNumero(String numero) {
        return TelefoneResponseDTO.valueOf(telefoneRepository.findByNumero(numero));
    }

    @Override
    public List<TelefoneResponseDTO> findAll() {
        return telefoneRepository.findAll().stream().map(e -> TelefoneResponseDTO.valueOf(e)).toList();
    }

}
