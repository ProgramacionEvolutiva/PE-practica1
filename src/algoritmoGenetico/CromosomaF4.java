package algoritmoGenetico;

/**
 * Atributos heredados
 *  ---------------------	
 *  private boolean[] genes;
 *  private double fenotipo;
 *  private double aptitud;
 *  private double puntuacion;
 *  private double puntuacionAcumulada;
 *  protected int longitud;
 * 
 * Metodos abstractos
 *  ----------------------------------
 *  evaluarCromosoma()
 *  clone() 
 * 
 * Metodos implementados 
 *  ----------------------------------
 *  inicializarGenes(int)
 *  calcularLongitud(double, int, int)
 *  calcularLongitud(double, double, double, double, double, int, int)
 *  calcularFenotipo(int, int, int)
 *  setGen(int, boolean)
 *  mutaGen(int)
 *  toString()
 *  mostrar()
 */
public class CromosomaF4 extends Cromosoma 
{
	/* Valores definidos en el problema */
	// TODO
	private static final int xMax = 100;
	private static final int xMin = 0;
	
	public static int longitud = -1;
	
	public CromosomaF4(double tolerancia) 
	{	
		if (longitud == -1) {
			longitud = calcularLongitud(tolerancia, xMax, xMin);
		}
		this.genes = inicializarGenes(longitud);
		this.fenotipo = calcularFenotipo(longitud,xMax,xMin);
		this.aptitud = evaluarCromosoma();
	}
	
	public CromosomaF4(boolean[] genes, double fenotipo, double aptitud, double puntuacionAcumulada) 
	{
		super(genes,fenotipo,aptitud,puntuacionAcumulada);
	}

	@Override
	protected Cromosoma clone() {
		return new CromosomaF4(this.getGenes().clone(),this.getFenotipo(),this.getAptitud(),this.getPuntuacionAcumulada());	
	}

	@Override
	protected double evaluarCromosoma() {
		// TODO Auto-generated method stub
		return 0;
	}
}
