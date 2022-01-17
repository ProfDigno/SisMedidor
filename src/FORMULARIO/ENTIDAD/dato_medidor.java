	package FORMULARIO.ENTIDAD;
public class dato_medidor {

//---------------DECLARAR VARIABLES---------------
private int C1iddato_medidor;
private int C2numero_medidor;
private String C3nombre;
private int C4pulso_kw;
private String C5face;
private int C6fk_idtarifa;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public dato_medidor() {
		setTb_dato_medidor("dato_medidor");
		setId_iddato_medidor("iddato_medidor");
	}
	public static String getTb_dato_medidor(){
		return nom_tabla;
	}
	public static void setTb_dato_medidor(String nom_tabla){
		dato_medidor.nom_tabla = nom_tabla;
	}
	public static String getId_iddato_medidor(){
		return nom_idtabla;
	}
	public static void setId_iddato_medidor(String nom_idtabla){
		dato_medidor.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iddato_medidor(){
		return C1iddato_medidor;
	}
	public void setC1iddato_medidor(int C1iddato_medidor){
		this.C1iddato_medidor = C1iddato_medidor;
	}
	public int getC2numero_medidor(){
		return C2numero_medidor;
	}
	public void setC2numero_medidor(int C2numero_medidor){
		this.C2numero_medidor = C2numero_medidor;
	}
	public String getC3nombre(){
		return C3nombre;
	}
	public void setC3nombre(String C3nombre){
		this.C3nombre = C3nombre;
	}
	public int getC4pulso_kw(){
		return C4pulso_kw;
	}
	public void setC4pulso_kw(int C4pulso_kw){
		this.C4pulso_kw = C4pulso_kw;
	}
	public String getC5face(){
		return C5face;
	}
	public void setC5face(String C5face){
		this.C5face = C5face;
	}
	public int getC6fk_idtarifa(){
		return C6fk_idtarifa;
	}
	public void setC6fk_idtarifa(int C6fk_idtarifa){
		this.C6fk_idtarifa = C6fk_idtarifa;
	}
	public String toString() {
		return "dato_medidor(" + ",iddato_medidor=" + C1iddato_medidor + " ,numero_medidor=" + C2numero_medidor + " ,nombre=" + C3nombre + " ,pulso_kw=" + C4pulso_kw + " ,face=" + C5face + " ,fk_idtarifa=" + C6fk_idtarifa + " )";
	}
}
