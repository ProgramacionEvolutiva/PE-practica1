package algoritmoGenetico;

/**
 * Atributos heredados
 * ---------------------	
 * private boolean[] genes;
 * private double fenotipo;
 * private double aptitud;
 * private double puntuacion;
 * private double puntuacionAcumulada;
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
	private static int longitud = -1;
	
	public static int getLongitud(){
		return longitud;
	}
	
	public CromosomaF1(double tolerancia) 
	{	
		if (longitud == -1) {
			longitud = calcularLongitud(tolerancia, xMax, xMin);
		}
		this.genes = inicializarGenes(longitud);
		this.fenotipo = calcularFenotipo(longitud,xMax,xMin);
		this.aptitud = evaluarCromosoma();
	}
	

	public CromosomaF1(boolean[] genes, double fenotipo, double aptitud, double puntuacionAcumulada) 
	{
		super(genes,fenotipo,aptitud,puntuacionAcumulada);
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

	protected CromosomaF1 clone()
	{
		return new CromosomaF1(this.getGenes().clone(),this.getFenotipo(),this.getAptitud(),this.getPuntuacionAcumulada());
	}
}
