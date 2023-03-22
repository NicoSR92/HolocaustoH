package holocaustoH;

public class Jugador extends Personaje{
	
	final static int VIDA_INICIAL = 5;

	private int vida=VIDA_INICIAL;
	private boolean piezaHabitacion=false;
	
	public Jugador() {
		// TODO Auto-generated constructor stub
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean getPiezaHabitacion() {
		return piezaHabitacion;
	}

	public void setPiezaHabitacion() {
		System.out.println("¡¡HAS CONSEGUIDO LA LLAVE!!");
		this.piezaHabitacion = true;
	}
}
