package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.repository.ClienteRepository;

@Repository
public class ClienteDaoImplMy8Jpa implements ClienteDao {

	@Autowired
	private ClienteRepository clrepo;

	@Override
	public List<Cliente> findAll() {
		
		return clrepo.findAll();
	}

	@Override
	public Cliente findById(int idCliente) {
		
		return clrepo.findById(idCliente).orElse(null);
	}

	@Override
	public Cliente insertClient(Cliente cliente) {
		try {
			return clrepo.save(cliente);
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateCliente(Cliente cliente) {
		if(findById(cliente.getIdCliente())!= null){
			clrepo.save(cliente);
			return 1;
		}
		return 0;
	}

	@Override
	public int removeCliente(int idCliente) {
		if(findById(idCliente) != null) {
			clrepo.deleteById(idCliente);
			return 1;
		}
		return 0;
	}
	
}
