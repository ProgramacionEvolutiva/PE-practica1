package algoritmoGenetico;

public class CromosomaF3 extends Cromosoma {

	private static final int xMax = 250;
	private static final int xMin = -250;
	private static int longitud = -1;
	
	public static int getLongitud(){
		return longitud;
	}
	
	public CromosomaF3(double tolerancia) 
	{	
		if (longitud == -1) {
			longitud = calcularLongitud(tolerancia, xMax, xMin);
		}
		this.genes = inicializarGenes(longitud);
		this.fenotipo = calcularFenotipo(longitud,xMax,xMin);
		this.aptitud = evaluarCromosoma();
	}
	
	public CromosomaF3(boolean[] genes, double fenotipo, double aptitud, double puntuacionAcumulada) 
	{
		super(genes,fenotipo,aptitud,puntuacionAcumulada);
	}

	@Override
	protected Cromosoma clone()
	{
		return new CromosomaF3(this.getGenes().clone(),this.getFenotipo(),this.getAptitud(),this.getPuntuacionAcumulada());
	}

	@Override
	protected double evaluarCromosoma() {
		double x = fenotipo;
		double resul=x*Math.sin(Math.sqrt(Math.abs(x)));
		resul=Math.abs(resul);
		return resul;
	}

}
