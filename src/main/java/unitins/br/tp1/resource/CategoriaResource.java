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
import unitins.br.tp1.dto.CategoriaDTO;
import unitins.br.tp1.dto.CategoriaResponseDTO;
import unitins.br.tp1.service.CategoriaService;

@Path("categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService service;

    @GET
    public List<CategoriaResponseDTO> buscarTodos() { 
        return service.findAll();
    }

    @GET
    @Path("/produto/{id}")
    public List<CategoriaResponseDTO>  buscarPorProduto(Long id) { 
        return service.findByProduto(id);
    }

    @GET
    @Path("/nome/{nome}")
    public List<CategoriaResponseDTO>  buscarPorNome(String nome) { 
        return service.findByNome(nome);
    }

    @POST
    public CategoriaResponseDTO incluir(CategoriaDTO dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public void alterar(Long id, CategoriaDTO dto) {
        service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void apagar(Long id) {
        service.delete(id);
    }

}