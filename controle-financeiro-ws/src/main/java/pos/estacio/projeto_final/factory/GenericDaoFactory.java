package pos.estacio.projeto_final.factory;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import pos.estacio.projeto_final.dao.GenericDao;

public class GenericDaoFactory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 609479736844037169L;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Produces
	public GenericDao createDAO(InjectionPoint injectionPoint) throws ClassNotFoundException {
		ParameterizedType type = (ParameterizedType) injectionPoint.getType();
		Class classe = (Class) type.getActualTypeArguments()[0];
		return new GenericDao(classe);
	}

}