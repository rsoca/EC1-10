import java.io.*;
import java.util.*;

public class Algoritmo {

	private static int idN = 0;
	private static final String ANCHURA = "ANCHURA";
	private static final String PROFUNDIDAD = "PROFUNDIDAD";
	private static final String COSTO_UNIFORME = "COSTO_UNIFORME";

	public static boolean busqueda(Problema problema, String estrategia, int profMax, int incProf) throws IOException {
		long profActual = incProf;
		boolean esSolucion = false;
		while (!esSolucion && profActual <= profMax) {
			esSolucion = busqueda_acotada(problema, estrategia, profMax);
			profActual = profActual + incProf;
		}
		return esSolucion;
	}

	public static boolean busqueda_acotada(Problema problema, String estrategia, int profMax) throws IOException { // en
																													// anchura
		boolean solucion = false;
		Cubo c = new Cubo();
		c.setPosiciones(problema.getPos());
		c.setEstado(Estado.obtenerID(c));
		List<Nodo> lista_nodos;
		String[][] lista_sucesores;
		Frontera frontera = new FronteraPrioridad();
		frontera.crearFrontera();
		Nodo nodo_actual = new Nodo(null, c, "", 0, 0, 0, 0); // nodo padre, estado, accion, coste, profundidad, id
		frontera.insertarNodo(nodo_actual, estrategia);

		while (solucion == false) {
			nodo_actual = frontera.sacarNodo();
			frontera.comprobacion(nodo_actual);
			solucion = Problema.esObjetivo(nodo_actual);

			if (solucion) {
				System.out.println("¡Hemos encontrado una solución!");
			} else {
				lista_sucesores = Estado.sucesores(nodo_actual.getEstado());
				lista_nodos = CrearListaNodos(lista_sucesores, nodo_actual, profMax, estrategia);
				frontera.insertarNodos(lista_nodos, estrategia);
			}

		}

		if (solucion) {
			System.out.println("Creamos la solucion... (archivo solucion.txt)");
			CrearSolucion(nodo_actual);
		} else {
			System.out.println("NO LA HEMOS ENCONTRADO");
		}

		return solucion;

	}// fin busqueda acotada

	public static List<Nodo> CrearListaNodos(String[][] lista_sucesores, Nodo nodo_actual, int pmaxima,
			String estrategia) throws IOException {

		List<Nodo> lista = new ArrayList<Nodo>();
		Nodo nodo = null;
		double valorF = 0.0;
		int id = nodo_actual.getId();
		System.out.println("Generamos los nodos de: " + id);
		System.out.println("Estado del nodo: " + nodo_actual.getEstado().getEstado());
		for (int i = 0; i < lista_sucesores.length; i++) { // recorremos las filas de los sucesores
			switch (estrategia) {
			case ANCHURA:
				valorF = nodo_actual.getD() + 1;
				break;
			case PROFUNDIDAD:
				valorF = (double)1/(nodo_actual.getD() + 1);
				break;
			case COSTO_UNIFORME:
				valorF = (double)nodo_actual.getCosto() + Double.parseDouble(lista_sucesores[i][2]);
				break;
			}

			idN = idN + 1; // Actualizamos el id de cada nodo
			// Nodo padre, Cubo estado, String accion, double costo, int d, int id, double
			// valor

			Cubo cubo = Estado.obtenerCubo(lista_sucesores[i][1]);
			String accion = lista_sucesores[i][0];
			double coste_sucesor = Double.parseDouble(lista_sucesores[i][2]);
			double nuevo_coste = coste_sucesor + nodo_actual.getCosto();
			int d = nodo_actual.getD() + 1; //

			nodo = new Nodo(nodo_actual, cubo, accion, nuevo_coste, d, idN, valorF);

			if (nodo.getD() < pmaxima) {
				lista.add(nodo);
			}
		}
		return lista;
	}

	// acabado
	public static void CrearSolucion(Nodo nodo_actual) throws IOException { // importar siempre el java.io

		Stack<Nodo> pila = new Stack<Nodo>(); // creamos la pila donde iran entrando los nodos
		boolean primero = false;
		pila.push(nodo_actual); // meto el nodo de la solucion

		do {
			nodo_actual = nodo_actual.getPadre(); // convierto al padre en nodo actual
			pila.push(nodo_actual); // meto ese nodo en la pila y me salgo del while

			if (nodo_actual.getId() == 0) { // si hemos llegado al primer nodo, ponemos a TRUE
				primero = true;
			}
		} while (primero == false);

		// ya tenemos la pila llena, ahora ir sacando y metiendo en el archivo de texto
		// convertir el cubo a MD5 y guardarlo asi

		// LIMPIAMOS EL ANTERIOR ARCHIVO POR SI HUBIERAN DATOS

		BufferedWriter bw = new BufferedWriter(new FileWriter("solucion.txt"));
		bw.write("");
		bw.close();

		// Formato de guardado de los datos en el archivo
		// Prepresentaci�n del nodo del �rbol: [ID_Nodo]([accion]
		// ID_ESTADO,costo,profundidad,valor del nodo (f))
		// La profundidad es el valor d

		Nodo n = null;
		FileWriter fichero = null;
		try {
			fichero = new FileWriter("solucion.txt");
			// pw = new PrintWriter(fichero);
			fichero.write("ID NODO, ACCION, ESTADO, COSTE, PROFUNDIDAD, VALOR DE F \n");
			while (!pila.empty()) {
				n = pila.pop();
				String md = Estado.getMD5(Estado.obtenerID(n.getEstado()));
				fichero.write("[" + n.getId() + "] ([" + n.getAccion() + "] " + md + ", " + n.getCosto() + ", "
						+ n.getD() + ", " + n.getF() + ")) \n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		fichero.close();

	}

}
