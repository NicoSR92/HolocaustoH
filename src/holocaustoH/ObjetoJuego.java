package holocaustoH;

public class ObjetoJuego {
	//tipos de ObjetoJuego
	final static int JUGADOR = 0;
	final static int HADRON = 1;
	final static int PIEZA = 2;
	final static int OBJETO = 3;
	final static int PUERTA_IN = 4;
	final static int PUERTA_OUT = 5;
	
	//Propiedades
	private String nombre;
	private Posicion pos;
	private String letraMapa = "  ";
	private int tipoObjeto = -1;

	public ObjetoJuego() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		this.pos = pos;
	}

	public String getLetraMapa() {
		return letraMapa;
	}

	public void setLetraMapa(String letraMapa) {
		this.letraMapa = letraMapa;
	}

	public int getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(int tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

}
