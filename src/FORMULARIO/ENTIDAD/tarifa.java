	package FORMULARIO.ENTIDAD;
public class tarifa {

//---------------DECLARAR VARIABLES---------------
private int C1idtarifa;
private String C2fecha_creado;
private String C3tipo;
private double C4monto_tarifa;
private static double C4monto_tarifa_global;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public tarifa() {
		setTb_tarifa("tarifa");
		setId_idtarifa("idtarifa");
	}
	public static String getTb_tarifa(){
		return nom_tabla;
	}
	public static void setTb_tarifa(String nom_tabla){
		tarifa.nom_tabla = nom_tabla;
	}
	public static String getId_idtarifa(){
		return nom_idtabla;
	}
	public static void setId_idtarifa(String nom_idtabla){
		tarifa.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idtarifa(){
		return C1idtarifa;
	}
	public void setC1idtarifa(int C1idtarifa){
		this.C1idtarifa = C1idtarifa;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public String getC3tipo(){
		return C3tipo;
	}
	public void setC3tipo(String C3tipo){
		this.C3tipo = C3tipo;
	}
	public double getC4monto_tarifa(){
		return C4monto_tarifa;
	}
	public void setC4monto_tarifa(double C4monto_tarifa){
		this.C4monto_tarifa = C4monto_tarifa;
	}
	public String toString() {
		return "tarifa(" + ",idtarifa=" + C1idtarifa + " ,fecha_creado=" + C2fecha_creado + " ,tipo=" + C3tipo + " ,monto_tarifa=" + C4monto_tarifa + " )";
	}

    /**
     * @return the C4monto_tarifa_global
     */
    public static double getC4monto_tarifa_global() {
        return C4monto_tarifa_global;
    }

    /**
     * @param aC4monto_tarifa_global the C4monto_tarifa_global to set
     */
    public static void setC4monto_tarifa_global(double aC4monto_tarifa_global) {
        C4monto_tarifa_global = aC4monto_tarifa_global;
    }
}
