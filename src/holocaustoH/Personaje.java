package holocaustoH;

public class Personaje extends ObjetoJuego {
	
	
	public Personaje() {
		System.out.println("Creación de un personaje.");
	}
	
	public boolean movX(int x) {
		Posicion pos = super.getPos();
		boolean posible;
		if(pos.getPosX() + x < 0 || pos.getPosX() + x >= Habitacion.ANCHO) posible = false;
		else {
			pos.setPosX(pos.getPosX() + x);
			posible = true;
		}
		return posible;
	}
	
	public boolean movY(int y) {
		Posicion pos = super.getPos();
		boolean posible;
		if(pos.getPosY() + y < 0 || pos.getPosY() + y >= Habitacion.ALTO) posible = false;
		else {
			pos.setPosY(pos.getPosY() + y);
			posible = true;
		}
		return posible;
	}

}
