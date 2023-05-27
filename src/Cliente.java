
public class Cliente {

	private String nombre,telefono,direccion,instituto;
	private double deuda;
	
	
	public Cliente() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public double getDeuda() {
		return deuda;
	}
	
	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}
	
	
	
}
