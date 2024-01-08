package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Comercial;

public interface ComercialDao {
	
	List<Comercial> findAll();
	Comercial findById(int idComercial);
	Comercial insertComercial (Comercial comercial);
	int updateComercial (Comercial comercial);
	int deleteComercial (int idComercial);

}
