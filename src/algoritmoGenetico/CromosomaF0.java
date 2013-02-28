package algoritmoGenetico;

/**
 * Atributos heredados
 * ---------------------	
 * private boolean[] genes;
 * private double fenotipo;
 * private double aptitud;
 * private double puntuacion;
 * private double puntuacionAcumulada;
 * ----------------------------------
 *	protected int calcularLongitud(double tolerancia, int xMax, int xMin)
 */
public class CromosomaF0 extends Cromosoma 
{
	
	public CromosomaF0(double tolerancia, int xMax, int xMin) 
	{
		int longitud = calcularLongitud(tolerancia, xMax, xMin);
		this.genes = inicializarGenes(longitud);
		this.fenotipo = calcularFenotipo();
	}
}
