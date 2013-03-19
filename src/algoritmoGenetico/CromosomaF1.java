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
public class CromosomaF1 extends Cromosoma 
{
	/* Valores definidos en el problema */
	private static final double xMax = 32.0;
	private static final double xMin = 0.0;
	
	private int longitud;
	
	public static int dameLongitud(double tolerancia){
		return calcularLongitud(tolerancia, xMax, xMin);
	}
	
	public CromosomaF1(boolean[] genes)
	{
		this.longitud = genes.length;
		this.genes = genes;
		evaluarCromosoma();
	}
	
	public CromosomaF1(double tolerancia) 
	{	
		longitud = calcularLongitud(tolerancia, xMax, xMin);
		this.genes = inicializarGenes(longitud);
		// aptitud y fenotipo
		evaluarCromosoma();
	}
	
	public CromosomaF1(boolean[] genes, double fenotipo, double aptitud, double punt, double puntAcc)
	{
		super(genes, fenotipo, aptitud, punt ,puntAcc);
		this.longitud = genes.length;
	}
	
	/* Implementacion de metodos abstractos */
	
	@Override
	protected void evaluarCromosoma() 
	{
		fenotipo = calcularFenotipo(longitud,xMax,xMin);
		double x = this.fenotipo;
		double exp = -0.2 * Math.abs(x);
		double valor= Math.E +(-20*Math.pow(Math.E,exp));
		valor-=Math.pow(Math.E, Math.cos(2*Math.PI*x));
		valor = 20+valor;
		this.aptitud = valor;
	}	

	@Override
	protected Cromosoma clone() 
	{
		return new CromosomaF1(this.getGenes().clone(),this.getFenotipo(),this.getAptitud(),this.getPuntuacion(),this.getPuntuacionAcumulada());
	}
	
}
