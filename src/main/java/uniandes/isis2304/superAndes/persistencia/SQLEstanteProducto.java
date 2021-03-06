/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.EstanteProducto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Estante de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLEstanteProducto 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLEstanteProducto (PersistenciaSuperAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Estante a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la Estante
	 * @param espacio - espacio de la Estante
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarEstanteProducto(PersistenceManager pm, long idEstante, String codProd, long cantidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante () + "(idEstante, cod_prod, cantidad) values (?, ?, ?)");
        q.setParameters(idEstante, codProd, cantidad);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Estante de la base de datos de Parranderos, por sus identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la Estante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEstanteProducto (PersistenceManager pm,  long idEstante, String codProd)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante () + " WHERE idEstante = ? AND cod_prod = ?" );
        q.setParameters(idEstante, codProd);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los Estante de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Estante
	 */
	public List<EstanteProducto> darEstantesProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante ());
		q.setResultClass(EstanteProducto.class);
		List<EstanteProducto> resp = (List<EstanteProducto>) q.execute();
		return resp;
	}

}
