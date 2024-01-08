package unir.exa.ventas.modelo.dao;
import java.util.List;

import unir.exa.ventas.modelo.entity.Pedido;

public interface PedidoDao {
	
	List<Pedido> findAll();
	Pedido findById(int idPedido);
	int insertPedido (Pedido pedido);
	int updatePedido (Pedido pedido);
	int deletePedido (int idPedido);
	List<Pedido> pedidoPorCliente (int idCliente);
	List<Pedido> pedidoPorComercial (int idComercial);

}
