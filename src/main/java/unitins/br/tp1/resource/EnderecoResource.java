package unitins.br.tp1.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import unitins.br.tp1.dto.EnderecoDTO;
import unitins.br.tp1.dto.EnderecoResponseDTO;
import unitins.br.tp1.service.EnderecoService;

@Path("Enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @GET
    public List<EnderecoResponseDTO> buscarTodos() {
        return service.findAll();
    }

    @GET
    @Path("/municipio/{id}")
    public List<EnderecoResponseDTO> buscarPorMunicipio(Long id) {
        return service.findByMunicipio(id);
    }

    @GET
    @Path("/bairro/{bairro}")
    public List<EnderecoResponseDTO> buscarPorBairro(String bairro) {
        return service.findByBairro(bairro);
    }

    @POST
    public EnderecoResponseDTO incluir(EnderecoDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, EnderecoDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}