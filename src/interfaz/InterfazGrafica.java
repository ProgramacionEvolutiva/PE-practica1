package interfaz;

import javax.swing.JFrame;

import algoritmoGenetico.Cromosoma;

public class InterfazGrafica extends JFrame{
	private static final long serialVersionUID = 1L;

	public Parametros getParametros(){
		//TODO
		return new Parametros(6, 2, 0.7, 0.01, 0.01);
	}
	
	/**
	 * 
	 * @param cromosoma a imprimir
	 */
	public void mostrar(Cromosoma cromosoma){
		// TODO
		System.out.println("Solucion => " + cromosoma.toString());
	}
}
