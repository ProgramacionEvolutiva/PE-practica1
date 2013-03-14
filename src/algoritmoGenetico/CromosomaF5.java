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
public class CromosomaF5 extends Cromosoma
{
	/* Valores definidos en el problema */
	private static final int xMax = 10;
	private static final int xMin = -10;
	
	public static int longitud = -1;
	public static int longitudX = -1;
	
	private double fenotipo2;	
	
	public CromosomaF5(double tolerancia) {
		if (longitud == -1) {
			longitud = calcularLongitud(tolerancia, xMax, xMin, xMax, xMin, longitudX, longitudX);
		}
		this.genes = inicializarGenes(longitud);
		this.fenotipo = calcularFenotipo(longitud,xMax,xMin);
		this.aptitud = evaluarCromosoma();
	}
	
	public CromosomaF5(boolean[] genes, double fenotipo, double aptitud, double puntuacionAcumulada) 
	{
		super(genes,fenotipo,aptitud,puntuacionAcumulada);
	}

	@Override
	protected Cromosoma clone() {
		return new CromosomaF5(this.getGenes().clone(),this.getFenotipo(),this.getAptitud(),this.getPuntuacionAcumulada());	
	}

	@Override
	protected double evaluarCromosoma() {
		double x1 = this.getFenotipo();
		double x2 = fenotipo2;
		int resul1=0;
		for (int i=1; i<=5; i++){
			resul1+=i*Math.cos((i+1)*x1+i);
		}
		int resul2=0;
		for (int i=1; i<=5; i++){
			resul2+=i*Math.cos((i+1)*x2+i);
		}
		return resul1*resul2;
	}

}
