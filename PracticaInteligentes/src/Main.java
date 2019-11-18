import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		Cubo c= new Cubo();
		Cubo clon = new Cubo(c);
		Lectura leer = new Lectura();
		int [][][] posiciones = leer.leerArchivo();
		Problema problema = new Problema();
		
		//Algoritmo.busqueda_acotada(problema, "ANCHURA", 6);
		
		
		//PRUEBA ORDENACION DE LA COLA 
		/*int profMax = 3;
		String estrategia = "ANCHURA";
		Frontera frontera = new FronteraPrioridad();
		frontera.crearFrontera();
		Nodo nodo_padre=null;
		Nodo nodo_actual = null;
		Nodo nodo_inicial = new Nodo(nodo_padre, c, "", 0, 0, 0, 0);
		
		List<Nodo> lista_nodos;
		String[][] lista_sucesores;
		frontera.insertarNodo(nodo_inicial);
		
		int cr=0;
		do {
		nodo_actual = frontera.sacarNodo();
		System.out.println("El estado es: " + Estado.obtenerID(nodo_actual.getEstado())+
				" El Id es: "+nodo_actual.getId()+" La F es: "+nodo_actual.getF());
		lista_sucesores = Estado.sucesores(nodo_actual.getEstado());
		lista_nodos = Algoritmo.CrearListaNodos(lista_sucesores, nodo_actual, profMax, estrategia);
		frontera.insertarNodos(lista_nodos);
		cr++;
		}while(cr<100);
		
		while(frontera.estaVacia()!=true) {
			Nodo nodo = frontera.sacarNodo();
			
			System.out.println("El estado es: " + Estado.obtenerID(nodo.getEstado())+
					" El Id es: "+nodo.getId()+" La F es: "+nodo.getF());
		}*/
		
		
		
		
		
		/*Nodo padre = null;
		Nodo h1 = new Nodo(padre, c, "L0", 0, 0, 0, 1);
		if (problema.esObjetivo(h1)==true) {
			System.out.println("El cubo es objetivo");
		}else {
			System.out.println("El cubo no es objetivo");
		}*/
		
		/*c=Movimiento.movimiento(c, 'L', 2);
		c=Movimiento.movimiento(c, 'D', 2);
		c=Movimiento.movimiento(c, 'B', 2);
		Estado.imprimirCubo(c);*/
		
		
		
	
		
		//Estado.sucesores(c);
		//Estado.imprimirCubo(c);
		//System.out.println("Cubo movido");
		//c=Movimiento.movimiento(c, 'd', 2);
		//Estado.imprimirCubo(c);
		
		//----Comprobacion- Crear Solucion-------------------
		//Nodo padre, Cubo estado, String accion, double costo, int d, int id, double valor
		/*Nodo padre = null;
		Nodo h1 = new Nodo(padre, c, "L0", 0, 0, 0, 1);
		c=Movimiento.movimiento(c, 'd', 2);
		Nodo h2 = new Nodo(h1, c, "d2", 2, 2, 1, 2);
		clon=Movimiento.movimiento(c, 'D', 2);
		Nodo h3 = new Nodo(h2, clon, "D2", 3, 3, 2, 3);
		clon=Movimiento.movimiento(c, 'D', 2);
		Nodo h4 = new Nodo(h3, c, "LD2", 22, 12212, 221,112122);
		//Algoritmo.CrearSolucion(h4);
		
		System.out.println("H1"+h1.getEstado().getEstado());
		System.out.println("H1      "+Estado.obtenerID(h1.getEstado()));
		System.out.println("H2"+h2.getEstado().getEstado());
		System.out.println("H3"+h3.getEstado().getEstado());
		System.out.println("H4"+h4.getEstado().getEstado());*/

		
		/*  QUITAR ESTA LINEA PARA QUE EJECUTE EL CODIGO

//-----------------------------------------------------------------------------------------------------------------
		Movimiento.movimiento(clon, 'L', 0); 
		String id=Estado.obtenerID(clon);
		System.out.println("Estado"+id);    
		Movimiento.movimiento(clon, 'l', 0);   // AQUI HAGO LA INVERSA PARA QUE COMPROBEIS QUE SALE LO MISMO, 
		Movimiento.movimiento(clon, 'l', 0);
		id=Estado.obtenerID(clon);
		System.out.println("Estado"+id);
		Movimiento.movimiento(clon, 'L', 0);     // AQUI HAGO LA INVERSA PARA QUE COMPROBEIS QUE SALE LO MISMO
		Movimiento.movimiento(clon, 'D', 0);
		id=Estado.obtenerID(clon);
		System.out.println("Estado"+id);
		Movimiento.movimiento(clon, 'd', 0);    // AQUI HAGO LA INVERSA PARA QUE COMPROBEIS QUE SALE LO MISMO
//------------------------------------------------------------------------------------------------------------------        		
        		//BORRAR CODIGO ENTRE GUIONES PARA QUE HAGA SUCESORES SIN COMPROBAR LOS MOVIMIENTOS
        		
	    
		Estado.sucesores(clon);
		
		
		//c=Movimiento.movimiento(clon, 'L', 0);
		//Estado.imprimirCubo(c);
		//clon=Movimiento.movimiento(clon, 'l', 0);
		//Estado.imprimirCubo(clon);

		//Algoritmos.busqueda_acotada(leer.leerArchivo(), "anchura", 6);
				
		
		//Nodo n1 = null;
		//Nodo n2 = new Nodo(n1, clon, "s", 1, 2, 0);
		
		//boolean objetivo = Problema.esObjetivo(n2);
		//System.out.println("Nodo prueba (Objetivo): "+objetivo);
		//Estado.sucesores(c);
		
		//Cubo nuevo = Estado.obtenerCubo("133133133211211211022022022444444444555555555003003003");
		//System.out.println("Situacion del cubo nuevo");
		//Estado.imprimirCubo(nuevo);
		
		/*
		 * MOVIMIENTOS Y MD5 CUBO 
		 * 
		String id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		String md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);	
		//Estado.imprimir(clon.getPosiciones());
		//comprobamos movimientos
		clon=Movimiento.movimiento(clon, 'l', 3);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		//Estado.imprimir(clon.getPosiciones());
		clon=Movimiento.movimiento(clon, 'D', 1);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		//Estado.imprimir(clon.getPosiciones());
		clon=Movimiento.movimiento(clon, 'l', 1);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		//Estado.imprimir(clon.getPosiciones());
		clon=Movimiento.movimiento(clon, 'd', 0);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		//Estado.imprimir(clon.getPosiciones());
		clon=Movimiento.movimiento(clon, 'B', 0);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		//Estado.imprimir(clon.getPosiciones());
		clon=Movimiento.movimiento(clon, 'b', 5);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		//Estado.imprimir(clon.getPosiciones());
		clon=Movimiento.movimiento(clon, 'l', 2);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		//Estado.imprimir(clon.getPosiciones());
		clon=Movimiento.movimiento(clon, 'd', 1);
		id = Estado.obtenerID(clon);
		System.out.println("El ID es:"+id);	
		md= Estado.getMD5(id);
		System.out.println("El MD5 ES:"+md);
		
*/
	}
}