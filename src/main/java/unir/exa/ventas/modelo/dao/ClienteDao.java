package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Cliente;

public interface ClienteDao {
	
	List<Cliente> findAll ();
	Cliente findById (int idCliente);
	Cliente insertClient (Cliente cliente);
	int updateCliente (Cliente cliente);
	int removeCliente (int idCliente);
	

}
