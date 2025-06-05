package unitins.br.tp1.service;

import java.util.List;

import unitins.br.tp1.dto.PedidoDTO;
import unitins.br.tp1.dto.PedidoResponseDTO;

public interface PedidoService {

    List<PedidoResponseDTO> findByEmail(String email);
    PedidoResponseDTO findById(long idPedido, String email); 
    PedidoResponseDTO create(PedidoDTO pedido, String email);
    
}