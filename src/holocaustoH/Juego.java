package holocaustoH;
import java.util.concurrent.ThreadLocalRandom;

public class Juego {

	final static int LANZAR_DADO = 1;
	final static int SALIR_JUEGO = 0;

	static Posicion[] posiciones=new Posicion[20];
	static int numPosiciones=0;

	public Juego() {
		// TODO Auto-generated constructor stub
	}
	
	public static void pintarHabitacion(Habitacion h) {
		for(int i = -1;i < Habitacion.ALTO;i++) { // filas
			for(int j = 0;j <= Habitacion.ANCHO;j++) { // columnas
				Posicion posActual = new Posicion(j,i);
				int posObj = h.hayObjeto(posActual);
				
				if(posObj != -1) {
					ObjetoJuego objJ = h.getObjetoJ(posObj);
					//Comprobamos si el jugador ha conseguido la pieza para pintar la salida
					//El jugador siempre estará en la posicion 0 de los objetos de la habitación
					if(objJ.getTipoObjeto()==ObjetoJuego.PUERTA_OUT)
						if(((Jugador)h.getObjetoJ(0)).getPiezaHabitacion()) {
							System.out.print(objJ.getLetraMapa());
						}else {
							if(j == 0) System.out.print("║ "); // paredes laterales
							if(j == Habitacion.ANCHO-1) System.out.print(" ║"); // paredes laterales
							if(i==0 || i==Habitacion.ALTO-1) System.out.print("══"); //Paredes primera y ultima fila							
						}
						else System.out.print(objJ.getLetraMapa());
				}
				else if(i==-1 && j != Habitacion.ANCHO) System.out.print(pintarCoords(j));
				else if(i!=-1 && j == Habitacion.ANCHO) System.out.print(pintarCoords(i));
				else if(j == 0 && i == 0) System.out.print("╔═"); // esquina superior izquierda
				else if(j == Habitacion.ANCHO-1 && i == 0) System.out.print("═╗"); // esquina superior derecha
				else if(j == Habitacion.ANCHO-1 && i == Habitacion.ALTO-1) System.out.print("═╝"); // esquina inferior derecha
				else if(j == 0 && i == Habitacion.ALTO-1) System.out.print("╚═"); // esquina inferior izquierda
				else if(j == 0) System.out.print("║ "); // paredes laterales
				else if(j == Habitacion.ANCHO-1) System.out.print(" ║"); // paredes laterales
				else if(i == 0 || i == Habitacion.ALTO -1) System.out.print("══"); // paredes superiores/inferiores
				else System.out.print("  "); // mapa
				
				//☺☻♥♦♣♠•◘○◙♂♀♪♫☼►◄↕‼¶§▬↨↑↓→←↔▲▼
				
				 if(j == 0 || j == Habitacion.ALTO-1) System.out.print("");
			}
			System.out.println("");
		}
	}
	
	public static String pintarCoords(int c) {
		
		String r;
		if((c+"").length() < 2) r = "|" + c;
		else r = c+"";
		r = "|" + r.substring(r.length()-1,r.length());
		return r;
	}
	
	public static void pintarMenu() {
		System.out.println("--- HOLOCAUSTO H (HADRON) ---");
		System.out.println("-- [" + LANZAR_DADO + "] LANZAR DADO MOVIMIENTO ");
		System.out.println("-- [" + SALIR_JUEGO + "] SALIR DEL JUEGO ");
		System.out.println("-----------------------------");
	}
	
	
	public static int lanzarDado(int numCaras) {
		int valorDado = ThreadLocalRandom.current().nextInt(1, numCaras + 1);
		System.out.println("[DADO LANZADO] " + valorDado);
		return valorDado;
	}

	public static void resolverObjeto(ObjetoJuego obj) {
		System.out.println("--------- OBJETO ------------");
		System.out.println(obj.getClass().getName());
		System.out.println("-----------------------------");
	}
	
	public static int explotaHadron(int vidaPerdida) {
		int muerte=-1;
		System.out.println("--------- HADRÓN ------------");
		System.out.println("¡¡¡¡¡¡HAS CHOCADO CONTRA UN HADRÓN!!!!!!");
		System.out.println("Has perdido " + vidaPerdida + " puntos de vida");
		if(vidaPerdida>=Jugador.VIDA_INICIAL) {
			System.out.println("----> Estas muerto <----");
			muerte=Juego.SALIR_JUEGO;
		}
		else System.out.println("Has tenido suerte puedes continuar");
		System.out.println("-----------------------------");
		return muerte;

	}

	public static void habitacionSuperada(Habitacion h) {
		System.out.println("---- HABITACIÓN SUPERADA ----");
		Juego.pintarHabitacion(h);
		System.out.println("-----------------------------");
	}
	
	public static Posicion posAleatoria() {
		int posX=ThreadLocalRandom.current().nextInt(2, Habitacion.ANCHO-2);
		int posY=ThreadLocalRandom.current().nextInt(2, Habitacion.ALTO-2);
		Posicion posTemp=new Posicion(posX,posY);
		//Comprobación de todas las posiciones diferentes
		if(numPosiciones>0) {
			for(int i=0;i<numPosiciones;i++) {
				if(posTemp.mismaPosicion(posiciones[i])) {
					posX=ThreadLocalRandom.current().nextInt(2, Habitacion.ANCHO-2);
					posY=ThreadLocalRandom.current().nextInt(2, Habitacion.ALTO-2);
					posTemp.setPosX(posX);
					posTemp.setPosX(posY);
					i=0;
				}
			}
		}
		posiciones[numPosiciones]=posTemp;
		numPosiciones++;
		return posiciones[numPosiciones-1];
	}
}
