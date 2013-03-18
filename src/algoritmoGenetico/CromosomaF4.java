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
	private static final int xMax = 100;
	private static final int xMin = 0;
	private static int n=1;
	
	private int longitud;
	private double[] fenotipo2;
	
	public static int dameLongitud(double tolerancia){
		return calcularLongitudConArray(tolerancia, xMax, xMin, n);
	}
	
	public CromosomaF4(double tolerancia) 
	{	
		longitud = calcularLongitudConArray(tolerancia, xMax, xMin, n);
		this.genes = inicializarGenes(longitud);
		evaluarCromosoma();
	}

	public CromosomaF4(boolean[] genes, double fenotipo, double aptitud, double puntuacion, double puntuacionAcumulada) 
	{
		super(genes,fenotipo,aptitud,puntuacion,puntuacionAcumulada);
	}

	@Override
	protected Cromosoma clone()
	{
		return new CromosomaF4(this.getGenes().clone(),this.getFenotipo(),this.getAptitud(),this.getPuntuacion(),this.getPuntuacionAcumulada());	
	}

	@Override
	protected void evaluarCromosoma() {
		this.fenotipo2 = calcularFenotipo(longitud,xMax,xMin,n);		
		double sum = 0;
        for (int i=0; i<n; i++){
               sum -= (fenotipo2[i] * Math.sin(Math.sqrt(Math.abs(fenotipo2[i]))));
        }     
        this.aptitud =  (double) (sum);
	}
}
