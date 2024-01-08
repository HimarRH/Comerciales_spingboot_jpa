package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Pedido;
import unir.exa.ventas.repository.PedidoRepository;

@Repository
public class PedidoDaoImplMy8Jpa implements PedidoDao {
	
	@Autowired
	private PedidoRepository perepo;

	@Override
	public List<Pedido> findAll() {
		
		return perepo.findAll();
	}

	@Override
	public Pedido findById(int idPedido) {
		
		return perepo.findById(idPedido).orElse(null);
	}

	@Override
	public int insertPedido(Pedido pedido) {
		try {
			perepo.save(pedido);
			return 1;
			
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updatePedido(Pedido pedido) {
		if(findById(pedido.getIdPedido())!= null) {
			perepo.save(pedido);
			return 1;
		} else {
			
			return 0;
		}
	}

	@Override
	public int deletePedido(int idPedido) {
		if(findById(idPedido) != null) {
			
			try {
				perepo.deleteById(idPedido);
				return 1;
				
			}catch(Exception e){
				
				return 0;
			}
		}
		return 0;
	}

	@Override
	public List<Pedido> pedidoPorCliente(int idCliente) {
		
		return perepo.pedidoPorCliente(idCliente);
	}

	@Override
	public List<Pedido> pedidoPorComercial(int idComercial) {
		
		return perepo.pedidoPorComercial(idComercial);
	}

}
