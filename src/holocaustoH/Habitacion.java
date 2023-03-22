package holocaustoH;

public class Habitacion {
	//Tamaño de habitacion
	public static final int ANCHO = 30;
	public static final int ALTO = 20;
	
	//Puertas
	private Posicion puertaEntrada;
	private Posicion puertaSalida;
	
	//Conjunto de objetos
	private ObjetoJuego[] objetosJ = new ObjetoJuego[20];
	private int numObjetos = 0;
	
	//Jugador
	private Jugador jugador;
	
	public Habitacion() {
		System.out.println("Creación de una habitación.");
	}

	public Habitacion(Posicion puertaIn, Posicion puertaOut, Jugador j) {
		this.puertaEntrada = puertaIn;
		this.puertaSalida = puertaOut;
		this.jugador = j;
		this.jugador.setPos(this.puertaEntrada);
	}

	public void setObjetoJ(ObjetoJuego obj) {
		objetosJ[numObjetos] = obj;
		numObjetos++;
	}

	public ObjetoJuego getObjetoJ(int objPos) {
		return objetosJ[objPos];
	}

	public int hayObjeto(Posicion p) {
		for (int i = 0; i < numObjetos; i++) {
			ObjetoJuego obj = objetosJ[i];
			Posicion objPos = obj.getPos();
			if (p.mismaPosicion(objPos)) return i;
		}
		return -1;
	}
	
	public int hayObjetoSinJugador(Posicion p) {
		for (int i = 1; i < numObjetos; i++) {
			ObjetoJuego obj = objetosJ[i];
			Posicion objPos = obj.getPos();
			if (p.mismaPosicion(objPos)) return i;
		}
		return -1;
	}
	
	public void eliminarObjetoJ(int objPos) {
		int posObjetos = 0;
		for(int i = 0; i < numObjetos; i++) {
			if(objPos!=i) {
				objetosJ[posObjetos] = objetosJ[i];
				posObjetos++;
			}
		}
		numObjetos--;
	}
	
	//v0.0
	
	public Posicion getPuertaEntrada() {
		return puertaEntrada;
	}

	public void setPuertaEntrada(Posicion puertaEntrada) {
		Posicion p = new Posicion();
		
		if((puertaEntrada.getPosX() != 0 && puertaEntrada.getPosX() != ANCHO - 1 && puertaEntrada.getPosY() != 0 && puertaEntrada.getPosY() != ALTO - 1) ||
				puertaEntrada.getPosX() < 0 || puertaEntrada.getPosX() > ANCHO - 1 ||
				puertaEntrada.getPosY() < 0 || puertaEntrada.getPosY() > ALTO - 1
				) {
			p.setPosX(1);
			p.setPosY(0);
		}
		else p = puertaEntrada;
		this.puertaEntrada = p;
	}
	public Posicion getPuertaSalida() {
		return puertaSalida;
	}

	public void setPuertaSalida(Posicion puertaSalida) {
		Posicion p = new Posicion();
		
		if((puertaSalida.getPosX() != 0 && puertaSalida.getPosX() != ANCHO - 1 && puertaSalida.getPosY() != 0 && puertaSalida.getPosY() != ALTO - 1) ||
				puertaSalida.getPosX() < 0 || puertaSalida.getPosX() > ANCHO - 1 ||
				puertaSalida.getPosY() < 0 || puertaSalida.getPosY() > ALTO - 1
				) {
			p.setPosX(0);
			p.setPosY(1);
		}
		else p = puertaSalida;
		this.puertaSalida = p;
	}

	public Personaje getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public boolean esUnaPuerta (Posicion p) {
		if(p.mismaPosicion(puertaEntrada) || p.mismaPosicion(puertaSalida)) return true;
		else return false;
	}
	
	public boolean estaAqui(Posicion p) {
		if(p.mismaPosicion(this.jugador.getPos())) return true;
		else return false;
	}
}
