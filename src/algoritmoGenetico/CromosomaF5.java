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
	private static final int xMax = 32;
	private static final int xMin = 0;
	
	public static int longitud = -1;
	
	public CromosomaF5(double d) {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Cromosoma clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected double evaluarCromosoma() {
		// TODO Auto-generated method stub
		return 0;
	}

}
