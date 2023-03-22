package holocaustoH;

public class AgujeroNegro {
	private int posX;
	private int posY;
	
	public AgujeroNegro() {
		this.posX = 0;
		this.posY = 0;
	}

	
	public AgujeroNegro(int posx, int posy) {
		this.posX = posx;
		this.posY = posy;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
