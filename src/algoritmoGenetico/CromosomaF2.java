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
public class CromosomaF2 extends Cromosoma
{
	/* Valores definidos en el problema */
	private static final double xMax = 12.1;
	private static final double xMin = -3;
	private static final double yMax = 5.8;
	private static final double yMin = 4.1;
	
	public static int longitudX = -1;
	public static int longitudY = -1;
	public static int longitud = -1;
	
	private double fenotipo2;	

	
	public CromosomaF2(double tolerancia) 
	{	
		if (longitud == -1) {
			longitud = calcularLongitud(tolerancia, xMax, xMin, yMax, yMin, longitudX, longitudY);
		}
		this.genes = inicializarGenes(longitud);
		this.fenotipo = calcularFenotipo(longitud,xMax,xMin);
		this.aptitud = evaluarCromosoma();
	}

	public CromosomaF2(boolean[] genes, double fenotipo, double fenotipo2, double aptitud, double puntuacionAcumulada) 
	{
		super(genes,fenotipo,aptitud,puntuacionAcumulada);
		this.fenotipo2=fenotipo2;
	}
	
	public double getFenotipo2() {
		return fenotipo2;
	}

	public void setFenotipo2(double fenotipo2) {
		this.fenotipo2 = fenotipo2;
	}

	@Override
	protected Cromosoma clone() {
		return new CromosomaF2(this.getGenes().clone(),this.getFenotipo(),this.getFenotipo2(),this.getAptitud(),this.getPuntuacionAcumulada());
	}

	@Override
	protected double evaluarCromosoma() {
		double x = this.fenotipo;
		double y = this.fenotipo2;
		double resul = 21.5 + x*Math.sin(4*Math.PI*x)+y*Math.sin(20*Math.PI*y);
		return resul;
	}

}
