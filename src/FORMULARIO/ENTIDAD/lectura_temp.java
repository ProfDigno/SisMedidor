	package FORMULARIO.ENTIDAD;
public class lectura_temp {

//---------------DECLARAR VARIABLES---------------
private int C1idlectura_temp;
private String C2fecha_creado;
private double C3lectura_kw;
private double C4numero_medidor;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public lectura_temp() {
		setTb_lectura_temp("lectura_temp");
		setId_idlectura_temp("idlectura_temp");
	}
	public static String getTb_lectura_temp(){
		return nom_tabla;
	}
	public static void setTb_lectura_temp(String nom_tabla){
		lectura_temp.nom_tabla = nom_tabla;
	}
	public static String getId_idlectura_temp(){
		return nom_idtabla;
	}
	public static void setId_idlectura_temp(String nom_idtabla){
		lectura_temp.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idlectura_temp(){
		return C1idlectura_temp;
	}
	public void setC1idlectura_temp(int C1idlectura_temp){
		this.C1idlectura_temp = C1idlectura_temp;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public double getC3lectura_kw(){
		return C3lectura_kw;
	}
	public void setC3lectura_kw(double C3lectura_kw){
		this.C3lectura_kw = C3lectura_kw;
	}
	public double getC4numero_medidor(){
		return C4numero_medidor;
	}
	public void setC4numero_medidor(double C4numero_medidor){
		this.C4numero_medidor = C4numero_medidor;
	}
	public String toString() {
		return "lectura_temp(" + ",idlectura_temp=" + C1idlectura_temp + " ,fecha_creado=" + C2fecha_creado + " ,lectura_kw=" + C3lectura_kw + " ,numero_medidor=" + C4numero_medidor + " )";
	}
}
