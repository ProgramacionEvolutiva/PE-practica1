package interfaz;

import algoritmoGenetico.Cromosoma;

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
	public Cromosoma cromosoma;
	
	public Info (int generacion, String cadena, double fenotipo, double aptitud, Cromosoma cromosoma)
	{
		this.generacion = generacion;
		this.cadena = cadena;
		this.fenotipo = fenotipo;
		this.aptitud = aptitud;
		this.cromosoma = cromosoma;
	}
}
