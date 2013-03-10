package algoritmoGenetico;

/**
 * Clase abstracta que describe a un cromosoma generico
 *
 */
public abstract class Cromosoma {
	
	/* Atributos */
	protected boolean[] genes;
	protected double fenotipo;
	protected double aptitud;
	protected double puntuacionAcumulada;
	protected int longitud;
	
	/* Constructoras */
	public Cromosoma(){}
	public Cromosoma(boolean[] genes, double fenotipo, double aptitud, double puntuacionAcumulada) 
	{
		this.genes = genes;
		this.fenotipo = fenotipo;
		this.aptitud = aptitud;
		this.puntuacionAcumulada = puntuacionAcumulada;
	}
	
	/* Getters & Setters */
	protected boolean[] getGenes() {return genes;}
	protected void setGenes(boolean[] genes) {this.genes = genes;}
	protected double getFenotipo() {return fenotipo;}
	protected void setFenotipo(double fenotipo) {this.fenotipo = fenotipo;}
	protected double getAptitud() {return aptitud;}
	protected void setAptitud(double aptitud) {this.aptitud = aptitud;}
	protected double getPuntuacionAcumulada() {return puntuacionAcumulada;}
	protected void setPuntuacionAcumulada(double puntuacionAcumulada) {this.puntuacionAcumulada = puntuacionAcumulada;}	
	protected int getLongitud() {return longitud;}
	protected void setLongitud(int longitud) {this.longitud = longitud;}
	
	/* Metodos declarados */
	protected abstract double evaluarCromosoma();
	protected abstract Cromosoma clone();	

	
	/* Metodos implementados */
	
	/**
	 * Metodo que genera una cadena aleatoria de genes.
	 * Ejemplo: inicializarGenes(6) => [1,0,0,0,1,0]
	 * 
	 * @param longitud de la cadena a generar
	 * @return cadena de genes (valores booleanos)
	 */
	protected boolean[] inicializarGenes(int longitud)
	{
		boolean[] genes = new boolean[longitud];
		
		double numero;
		for (int i = 0; i<longitud; i++) {
			numero = Math.random();
			if (numero < 0.5){
				genes[i] = false;
			} else {
				genes[i] = true;
			}
		}		
		return genes;
	}
	
	/**
	 * Metodo para calcular la longitud de los cromosomas
	 * en problemas de una sola variable;
	 * dependiendo de la tolerancia del problema y el intervalo
	 * de posibles valores
	 * 
	 * @param tolerancia del problema
	 * @param valor maximo del intervalo
	 * @param valor minimo del intervalo
	 * @return longitud de los cromosomas
	 */
	protected int calcularLongitud(double tolerancia, int xMax, int xMin)
	{
		double aux = 1 + ( (xMax - xMin) / tolerancia);	
		aux = (Math.log(aux) / Math.log(2));
		return (int) Math.ceil(aux);
	}

	/**
	 * Metodo para calcular la longitud de los cromosomas
	 * en problemas de dos variables;
	 * dependiendo de la tolerancia del problema y los intervalos
	 * de posibles valores de las variables.
	 * 
	 * @param tolerancia del problema
	 * @param valor maximo del intervalo de x
	 * @param valor minimo del intervalo de x
	 * @param valor maximo del intervalo de y
	 * @param valor minimo del intervalo de y
	 * @return longitud de los cromosomas
	 */
	protected int calcularLongitud(double tolerancia, double xMax, double xMin, double yMax, double yMin, int longitudx, int longitudy) 
	{
		double longx = 1 + ( (xMax - xMin) / tolerancia);	
		longx = (Math.log(longx) / Math.log(2));
		longitudx = (int) Math.ceil(longx);
		double longy = 1 + ( (yMax - yMin) / tolerancia);	
		longy = (Math.log(longy) / Math.log(2));
		longitudy = (int) Math.ceil(longy);
		return longitudx + longitudy;
	}
	
	/**
	 * Metodo para calcular el fenotipo de un cromosoma (su valor numerico)
	 * Ejemplo: cromosma.calcularFenotipo(6,0,30) => 25.531
	 * 
	 * @param longitud del cromosoma
	 * @param valor maximo del intervalo
	 * @param valor minimo del intervalo
	 * @return fenotipo del cromosoma (valor real)
	 */
	protected double calcularFenotipo(int longitud, int xMax, int xMin)
	{
		// FIXME
		// No es necesario pasar como argumento la longitud ya que podemos acceder a ella con
		// <this.longitud>
		return (xMin + (xMax - xMin) * Integer.parseInt(this.toString(), 2) ) / ( (Math.pow(2,longitud) - 1) );
	}	

	/**
	 * Metodo para cambiar un gen especifico del cromosoma.
	 * 
	 * @param posicion en la cadena de genes del gen que queremos cambiar
	 * @param nuevo valor para el gen (true = 1; false = 0)
	 */
	protected void setGen(int posicion, boolean gen)
	{
		this.genes[posicion] = gen;
	}

	/** 
	 * Metodo para alterar un gen especifico del cromosoma
	 * Los cromosomas validos estan compuestos por una cadena de valores booleanos
	 * El valor 1 (true) muta al valor 0 (false) y viceversa
	 * Ejemplo: 1101.mutaGen(1) => 1001
	 * 
	 * @param posicion en la cadena del gen a mutar
	 */
	protected void mutaGen(int posicion)
	{
		this.genes[posicion] = !this.genes[posicion];
	}
	
	@Override
	public String toString()
	{
		String s = "";
		for (int i = 0; i < genes.length; i++) {
			if (this.genes[i]) s += "1";
			else s += "0";
		}
		return s ;
	}
	
	public void mostrar(){
		// TODO
		System.out.println("Solucion => " + toString() + " Fenotipo =>" + getFenotipo() +
				" Aptitud =>" + getAptitud());
	}
	
}
