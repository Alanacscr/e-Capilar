package unitins.br.tp1.service.Pedido;

import java.util.List;

import unitins.br.tp1.dto.Pagamento.BoletoDTO;
import unitins.br.tp1.dto.Pagamento.PixDTO;
import unitins.br.tp1.dto.Pedido.PedidoDTO;
import unitins.br.tp1.dto.Pedido.PedidoResponseDTO;

public interface PedidoService {

    List<PedidoResponseDTO> findByEmail(String email);
    PedidoResponseDTO findById(long idPedido, String email); 
    PedidoResponseDTO create(PedidoDTO pedido, String email);
    // Métodos para buscar e atualizar o pedido
    PedidoResponseDTO buscarPedido(Long idPedido);

    // Métodos específicos para adicionar pagamento por Pix e Boleto
    PedidoResponseDTO adicionarPagamentoPix(Long idPedido, PixDTO pixDTO);
    PedidoResponseDTO adicionarPagamentoBoleto(Long idPedido, BoletoDTO boletoDTO);
}
    