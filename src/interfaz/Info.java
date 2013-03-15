package interfaz;

/**
 * Estructura con la siguiente info
 * 
 *  - generacion
 *  - cadena 
 *  - fenotipo (numero)
 *  - aptitud
 */
public class Info 
{
	public int generacion;
	public String cadena;
	public double fenotipo;
	public double aptitud;
	
	public Info (int generacion, String cadena, double fenotipo, double aptitud)
	{
		this.generacion = generacion;
		this.cadena = cadena;
		this.fenotipo = fenotipo;
		this.aptitud = aptitud;
	}
}
