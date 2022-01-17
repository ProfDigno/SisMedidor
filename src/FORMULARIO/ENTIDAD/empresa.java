	package FORMULARIO.ENTIDAD;
public class empresa {

//---------------DECLARAR VARIABLES---------------
private int C1idempresa;
private String C2nombre;
private String C3ruc;
private String C4telefono;
private String C5direccion;
private String C6timbrado;
private String C7fecha_vencimiento;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public empresa() {
		setTb_empresa("empresa");
		setId_idempresa("idempresa");
	}
	public static String getTb_empresa(){
		return nom_tabla;
	}
	public static void setTb_empresa(String nom_tabla){
		empresa.nom_tabla = nom_tabla;
	}
	public static String getId_idempresa(){
		return nom_idtabla;
	}
	public static void setId_idempresa(String nom_idtabla){
		empresa.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idempresa(){
		return C1idempresa;
	}
	public void setC1idempresa(int C1idempresa){
		this.C1idempresa = C1idempresa;
	}
	public String getC2nombre(){
		return C2nombre;
	}
	public void setC2nombre(String C2nombre){
		this.C2nombre = C2nombre;
	}
	public String getC3ruc(){
		return C3ruc;
	}
	public void setC3ruc(String C3ruc){
		this.C3ruc = C3ruc;
	}
	public String getC4telefono(){
		return C4telefono;
	}
	public void setC4telefono(String C4telefono){
		this.C4telefono = C4telefono;
	}
	public String getC5direccion(){
		return C5direccion;
	}
	public void setC5direccion(String C5direccion){
		this.C5direccion = C5direccion;
	}
	public String getC6timbrado(){
		return C6timbrado;
	}
	public void setC6timbrado(String C6timbrado){
		this.C6timbrado = C6timbrado;
	}
	public String getC7fecha_vencimiento(){
		return C7fecha_vencimiento;
	}
	public void setC7fecha_vencimiento(String C7fecha_vencimiento){
		this.C7fecha_vencimiento = C7fecha_vencimiento;
	}
	public String toString() {
		return "empresa(" + ",idempresa=" + C1idempresa + " ,nombre=" + C2nombre + " ,ruc=" + C3ruc + " ,telefono=" + C4telefono + " ,direccion=" + C5direccion + " ,timbrado=" + C6timbrado + " ,fecha_vencimiento=" + C7fecha_vencimiento + " )";
	}
}
