package algoritmoGenetico;

/**
 * Atributos heredados
 * ---------------------	
 * private boolean[] genes;
 * private double fenotipo;
 * private double aptitud;
 * private double puntuacion;
 * private double puntuacionAcumulada;
 * protected int longitud;
 * 
 * ----------------------------------
 * 
 *  protected boolean[] inicializarGenes(int longitud)
 *	protected int calcularLongitud(double tolerancia, int xMax, int xMin)
 *  protected int calcularFenotipo(int longitud, int xMax, int xMin)
 *  protected void setGen(int posicion, boolean gen)
 *  
 */
public class CromosomaF1 extends Cromosoma 
{
	private static final int xMax = 32;
	private static final int xMin = 0;
	
	public CromosomaF1(double tolerancia) 
	{		
		this.longitud = calcularLongitud(tolerancia, xMax, xMin);
		this.genes = inicializarGenes(longitud);
		this.fenotipo = calcularFenotipo(longitud,xMax,xMin);
		this.aptitud = evaluarCromosoma(); //FIXME
	}

	@Override
	protected double evaluarCromosoma() 
	{
		double x = this.fenotipo;
		double exp = -0.2 * Math.abs(x);
		double valor= Math.E *(-20*Math.pow(Math.E,exp));
		valor*=-Math.pow(Math.E, Math.cos(2*Math.PI*x));
		return 20+valor;
	}
	
	@Override
	public String toString()
	{
		String s = "";
		for (int i = 0; i < this.longitud; i++) {
			String v = "";
			if (this.genes[i]) v = "1";
			else v = "0";
			
			s+= "| " + v + " |";
		}
		return s;
	}
}
