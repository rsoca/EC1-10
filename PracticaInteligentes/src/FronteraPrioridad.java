import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FronteraPrioridad extends Frontera{

	private Queue<Nodo> colaNodoFrontera;
	private List<Long> tiemposInsercion = new ArrayList<Long>(); //tiempos de insercion
	private long inicioInsercion;
	private long finInsercion;
	long tiempoTotal;
	long tiempoMaximo;
	long tiempoMinimo;
	
	/*
	 * El funcionamiento de PriorityQueue tiene un funcionamiento similar a la prioridad Heap (explicada en el laboratorio).
	 * 
	 */
	public FronteraPrioridad() {
		colaNodoFrontera = new PriorityQueue<Nodo>();
	}
	
	public Queue getFrontera() {
		return colaNodoFrontera;
	}
	
	public FronteraPrioridad crearFrontera() {
		FronteraPrioridad frontera = new FronteraPrioridad();
		return frontera;
	}
	
	public void insertarNodo(Nodo nodo) {
		inicioInsercion = System.currentTimeMillis();
		colaNodoFrontera.add(nodo);
		finInsercion = System.currentTimeMillis();
		tiemposInsercion.add(finInsercion - inicioInsercion);
	}
	
	public void insertarNodos(Nodo [] lista_nodos) {
		
		for(int i =0; i<lista_nodos.length;i++) {
			colaNodoFrontera.add(lista_nodos[i]);
		}
	}
	
	public Nodo eliminarNodo() {
		return colaNodoFrontera.remove();
	}
	
	public boolean estaVacia() {
		return colaNodoFrontera.isEmpty();
	}

	public void calcularTiempos() {
		for(Long tiempo: tiemposInsercion) {
			if (tiempo > tiempoMaximo) {
				tiempoMaximo = tiempo;
			}
			if (tiempo < tiempoMinimo) {
				tiempoMinimo = tiempo;
			}
			tiempoTotal += tiempo;
		}
	}
	
	public String toStringTiempo() {
		calcularTiempos();
		return "Tiempo minimo: "+tiempoMinimo+"\n Tiempo maximo: "+tiempoMaximo+"\n Tiempo medio: "+tiempoTotal/tiemposInsercion.size();
	}
}
