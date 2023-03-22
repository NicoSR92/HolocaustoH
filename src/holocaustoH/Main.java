package holocaustoH;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//Variables impresicindibles para el juego
		int accionJuego = -1;
		Scanner sc = new Scanner(System.in);
		int objeto=0;
		
		//Puertas
		Posicion pEntrada = new Posicion(0,3);
		Posicion pSalida = new Posicion(5,Habitacion.ALTO-1);
		ObjetoJuego puertaEntrada = new ObjetoJuego();
		puertaEntrada.setPos(pEntrada);
		puertaEntrada.setLetraMapa("►◄");
		puertaEntrada.setTipoObjeto(ObjetoJuego.PUERTA_IN);
		ObjetoJuego puertaSalida = new ObjetoJuego();
		puertaSalida.setPos(pSalida);
		puertaSalida.setLetraMapa("►◄");
		puertaSalida.setTipoObjeto(ObjetoJuego.PUERTA_OUT);
		
		//Jugador
		Jugador jugador = new Jugador();
		Posicion pJ = new Posicion(pEntrada.getPosX(),pEntrada.getPosY());
		jugador.setPos(pJ);
		jugador.setLetraMapa("☺ ");
		jugador.setTipoObjeto(ObjetoJuego.JUGADOR);
		
		//Enemigos
		Hadron hadron = new Hadron();
		Posicion pH = Juego.posAleatoria();
		hadron.setPos(pH);
		hadron.setLetraMapa("◙◙");
		hadron.setTipoObjeto(ObjetoJuego.HADRON);
		
		//Pieza
		ObjetoJuego pieza = new ObjetoJuego();
		Posicion pP = Juego.posAleatoria();
		pieza.setPos(pP);
		pieza.setLetraMapa("§§");
		pieza.setTipoObjeto(ObjetoJuego.PIEZA);
		
		//Objeto Oculto
		ObjetoJuego objOculto = new ObjetoJuego();
		Posicion pobjOculto = Juego.posAleatoria();
		objOculto.setPos(pobjOculto);
		objOculto.setLetraMapa("()");
		objOculto.setTipoObjeto(ObjetoJuego.OBJETO);
		
		//Habitacion
		//Habitacion habInicial = new Habitacion(puertaEntrada, puertaSalida, jugador);
		Habitacion hab = new Habitacion();
		hab.setObjetoJ(jugador);
		hab.setObjetoJ(puertaEntrada);
		hab.setObjetoJ(puertaSalida);
		hab.setObjetoJ(hadron);
		hab.setObjetoJ(pieza);
		hab.setObjetoJ(objOculto);
		
		
		while(accionJuego != Juego.SALIR_JUEGO) {
			//Pintamos el mapa
			Juego.pintarHabitacion(hab);
			Juego.pintarMenu();
			
			int numMov = 0;
			
			accionJuego = sc.nextInt();
			switch(accionJuego) {
				case Juego.LANZAR_DADO:
					numMov = Juego.lanzarDado(10);
					System.out.println("[MOVIMIENTO COLUMNAS/X] ¿Cuantas columnas quieres moverte? Tienes " + numMov + " movimientos.");
					int numColumnas = sc.nextInt();
					if(numMov - Math.abs(numColumnas) < 0) {
						if(numColumnas < 0) numColumnas = -numMov;
						else numColumnas = numMov;
						System.out.println("Ha superado el límite de movimientos. Te moveras las columnas máxima del dado: " + numColumnas);
					}
					boolean posibleX = jugador.movX(numColumnas);
					while(!posibleX) {
						System.out.println("El movimiento se sale del mapa.");
						System.out.println("[MOVIMIENTO COLUMNAS/X] ¿Cuantas columnas quieres moverte? Tienes " + numMov + " movimientos.");
						numColumnas = sc.nextInt();
						if(numMov - Math.abs(numColumnas) < 0) {
							if(numColumnas < 0) numColumnas = -numMov;
							else numColumnas = numMov;
							System.out.println("Ha superado el límite de movimientos. Te moveras las columnas máxima del dado: " + numColumnas);
						}
						posibleX = jugador.movX(numColumnas);
					}
					numMov = numMov - Math.abs(numColumnas);
					if(numMov > 0) {
						System.out.println("[MOVIMIENTO FILAS/Y] ¿Cuantas filas quieres moverte? Tienes " + numMov + " movimientos.");
						int numFilas = sc.nextInt();
						if(numMov - Math.abs(numFilas) < 0) {
							if(numFilas < 0) numFilas = -numMov;
							else numFilas = numMov;
							System.out.println("Ha superado el límite de movimientos. Te moveras las filas máxima restantes: " + numFilas);
						}
						boolean posibleY =jugador.movY(numFilas);
						while(!posibleY) {
							System.out.println("El movimiento se sale del mapa.");
							System.out.println("[MOVIMIENTO FILAS/Y] ¿Cuantas filas quieres moverte? Tienes " + numMov + " movimientos.");
							numFilas = sc.nextInt();
							if(numMov - Math.abs(numFilas) < 0) {
								if(numFilas < 0) numFilas = -numMov;
								else numFilas = numMov;
								System.out.println("Ha superado el límite de movimientos. Te moveras las filas máxima restantes: " + numFilas);
							}
							posibleY = jugador.movY(numFilas);
						}
						//jugador.movY(numFilas);
					}
					objeto=hab.hayObjetoSinJugador(jugador.getPos());
					break;
				case Juego.SALIR_JUEGO:
					break;
				default:
					System.out.println("OPCIÓN NO VALIDA. USE [0] O [1]");
			}
			
			if(objeto>0) {
				if(hab.getObjetoJ(objeto).getTipoObjeto() == ObjetoJuego.HADRON) {
					int vidaPerdida = Juego.lanzarDado(2) * numMov;
					accionJuego = Juego.explotaHadron(vidaPerdida);
				}
				else if(hab.getObjetoJ(objeto).getTipoObjeto() == ObjetoJuego.PIEZA) {
					jugador.setPiezaHabitacion();
				}
				else if(hab.getObjetoJ(objeto).getTipoObjeto() == ObjetoJuego.PUERTA_OUT) {
					Juego.habitacionSuperada(hab);
					accionJuego = Juego.SALIR_JUEGO;
				}
				hab.eliminarObjetoJ(objeto);
				objeto = -1;
			}
			
			
		}

	}

}
