package interfaz;

/**
 * Clase que recoge los valores de entrada del usuario
 * 
 * Valores de entrada disponibles
 *  | tipo   | nombre en la constructora    | valor por defecto 	|
 *  |:------:|:-----------------------------|:---------------------:|
 *  | int  	 | tam							| 100 					|
 *  | int  	 | generaciones 				| 50 					|
 *  | double | cruce 						| 0.6 					|
 *  | double | mutacion 					| 0.05 					|
 *  | double | tolerancia					| 0.01 					|
 *  | int	 | funcion						| 1 					|
 * Nota: llamar a la contructora vacia para asignar los valores por defecto 
 * 	
 */
public class Parametros {
	/* Valores por defecto */
	private static int TAM_POBLACION_DEFECTO = 100;
	private static int NUM_GENERACIONES_DEFECTO = 50;
	private static double PROB_CRUCE_DEFECTO = 0.6;
	private static double PROB_MUTACION_DEFECTO = 0.05;
	private static double TOLERANCIA_DEFECTO = 0.01;
	private static int FUNCION_DEFECTO = 1;
	
	/* Atributos */
	private int tamPoblacion;
	private int numGeneraciones;
	private double probCruce;
	private double probMutacion;
	private double tolerancia;
	private int funcion;
	
	/* Constructoras */
	public Parametros (int tam, int generaciones, double cruce, double mutacion, double tolerancia, int funcion)
	{
		this.tamPoblacion = tam;
		this.numGeneraciones = generaciones;
		this.probCruce = cruce;
		this.probMutacion = mutacion;
		this.tolerancia = tolerancia;
		this.funcion = funcion;
	}
	
	public Parametros ()
	{
		this.tamPoblacion = TAM_POBLACION_DEFECTO;
		this.numGeneraciones = NUM_GENERACIONES_DEFECTO;
		this.probCruce = PROB_CRUCE_DEFECTO;
		this.probMutacion = PROB_MUTACION_DEFECTO;
		this.tolerancia = TOLERANCIA_DEFECTO;
		this.funcion = FUNCION_DEFECTO;
	}
	
	/* Getters & Setters */
	public int getTamPoblacion() { return tamPoblacion; }
	public void setTamPoblacion(int tamPoblacion) { this.tamPoblacion = tamPoblacion; }
	public int getNumGeneraciones() { return numGeneraciones; }
	public void setNumGeneraciones(int numGeneraciones) { this.numGeneraciones = numGeneraciones; }
	public double getProbCruce() { return probCruce; }
	public void setProbCruce(double probCruce) { this.probCruce = probCruce; }
	public double getProbMutacion() { return probMutacion; }
	public void setProbMutacion(double probMutacion) { this.probMutacion = probMutacion; }
	public double getTolerancia() { return tolerancia; }
	public void setTolerancia(double tolerancia) { this.tolerancia = tolerancia; }
	public int getFuncion() { return funcion; }
	public void setFuncion(int funcion) { this.funcion = funcion; }

}
