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
	
	private double fenotipo2;	
	private int longitud;
	private int longitudX;
	private int longitudY;
	
	public static int dameLongitud(double tolerancia){
		return calcularLongitud(tolerancia, xMax, xMin, yMax, yMin)[2];
	}
	
	public CromosomaF2(double tolerancia) 
	{	
		int[] longs = calcularLongitud(tolerancia, xMax, xMin, yMax, yMin);
		longitudX=longs[0];
		longitudY=longs[1];
		longitud=longs[2];
		this.genes = inicializarGenes(longitud);
		evaluarCromosoma();
	}

	public CromosomaF2(boolean[] genes, double fenotipo, double fenotipo2, double aptitud, double puntuacion, double puntuacionAcumulada, int longitud, int longitudX, int longitudY) 
	{
		super(genes,fenotipo,aptitud,puntuacion,puntuacionAcumulada);
		this.fenotipo2=fenotipo2;
		this.longitudX=longitudX;
		this.longitudY=longitudY;
		this.longitud=longitud;
	}
	
	public double getFenotipo2() {
		return fenotipo2;
	}

	public void setFenotipo2(double fenotipo2) {
		this.fenotipo2 = fenotipo2;
	}

	@Override
	protected Cromosoma clone() {
		return new CromosomaF2(this.getGenes().clone(),this.getFenotipo(),this.getFenotipo2(),this.getAptitud(),this.getPuntuacion(),this.getPuntuacionAcumulada(),this.longitud,this.longitudX,this.longitudY);
	}

	@Override
	protected void evaluarCromosoma() {
		double[] fenotipos=calcularFenotipos(longitud,xMax,xMin,yMax,yMin,longitudX,longitudY);
		fenotipo=fenotipos[0];
		fenotipo2=fenotipos[1];
		double x = this.fenotipo;
		double y = this.fenotipo2;
		this.aptitud = 21.5 + (x*Math.sin(4*Math.PI*x))+(y*Math.sin(20*Math.PI*y));
	}

}
