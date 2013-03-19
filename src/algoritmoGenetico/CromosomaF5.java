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
	
	private double fenotipo2;	
	private int longitud;
	
	public static int dameLongitud(double tolerancia){
		return calcularLongitudConArray(tolerancia, xMax, xMin,2);
	}
	
	public CromosomaF5(double tolerancia) 
	{
		this.longitud = calcularLongitudConArray(tolerancia, xMax, xMin,2);
		this.genes = inicializarGenes(longitud);
		evaluarCromosoma();
	}
	
	public CromosomaF5(boolean[] genes)
	{
		this.genes = genes;
		this.longitud = genes.length;
		evaluarCromosoma();
	}

	public CromosomaF5(boolean[] genes, double fenotipo, double fenotipo2 ,double aptitud, double puntuacion, double puntuacionAcumulada) 
	{
		super(genes,fenotipo,aptitud,puntuacion,puntuacionAcumulada);
		this.fenotipo2=fenotipo2;
		this.longitud=genes.length;
	}

	@Override
	protected Cromosoma clone() {
		return new CromosomaF5(this.getGenes().clone(),this.getFenotipo(),this.getFenotipo2(),this.getAptitud(),this.getPuntuacion(),this.getPuntuacionAcumulada());	
	}

	@Override
	protected void evaluarCromosoma() {
		//double[] fenotipos=calcularFenotipo(longitud, xMax, xMin, 2);	
		double[] fenotipos = calcularFenotipos(longitud, xMax, xMin, xMax, xMin, longitud/2, longitud/2);
		fenotipo=fenotipos[0];
		fenotipo2=fenotipos[1];	
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
		this.aptitud =  resul1*resul2;
	}
	
	public double getFenotipo2() {
		return fenotipo2;
	}

}
