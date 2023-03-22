package holocaustoH;

public class Hadron extends Personaje{
	
	private boolean visible;
	
	public Hadron() {
		super.setTipoObjeto(ObjetoJuego.HADRON);
	}
	
	public boolean cambioVisibilidad() {
		if(this.visible) this.visible = false;
		else this.visible = true;
		
		return this.visible;
	}

}
