package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.repository.ComercialRepository;

@Repository
public class ComercialDaoImplMy8Jpa implements ComercialDao{
	
	@Autowired
	private ComercialRepository corepo;

	@Override
	public List<Comercial> findAll() {
		
		return corepo.findAll();
	}

	@Override
	public Comercial findById(int idComercial) {
		
		return corepo.findById(idComercial).orElse(null);
	}

	@Override
	public Comercial insertComercial(Comercial comercial) {
		try {
			return corepo.save(comercial);
		} catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int updateComercial(Comercial comercial) {
		if(findById(comercial.getIdComercial())!=null) {
			corepo.save(comercial);
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteComercial(int idComercial) {
		if (findById(idComercial) != null) {
			corepo.deleteById(idComercial);
			return 1;
		}else {
			return 0;
			
		}
	}

}
