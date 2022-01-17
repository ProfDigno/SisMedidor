	package FORMULARIO.ENTIDAD;
public class item_consumo_medidor {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_consumo_medidor;
private String C2fecha_creado;
private int C3lectura_kw;
private int C4fk_iddato_medidor;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_consumo_medidor() {
		setTb_item_consumo_medidor("item_consumo_medidor");
		setId_iditem_consumo_medidor("iditem_consumo_medidor");
	}
	public static String getTb_item_consumo_medidor(){
		return nom_tabla;
	}
	public static void setTb_item_consumo_medidor(String nom_tabla){
		item_consumo_medidor.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_consumo_medidor(){
		return nom_idtabla;
	}
	public static void setId_iditem_consumo_medidor(String nom_idtabla){
		item_consumo_medidor.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_consumo_medidor(){
		return C1iditem_consumo_medidor;
	}
	public void setC1iditem_consumo_medidor(int C1iditem_consumo_medidor){
		this.C1iditem_consumo_medidor = C1iditem_consumo_medidor;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public int getC3lectura_kw(){
		return C3lectura_kw;
	}
	public void setC3lectura_kw(int C3lectura_kw){
		this.C3lectura_kw = C3lectura_kw;
	}
	public int getC4fk_iddato_medidor(){
		return C4fk_iddato_medidor;
	}
	public void setC4fk_iddato_medidor(int C4fk_iddato_medidor){
		this.C4fk_iddato_medidor = C4fk_iddato_medidor;
	}
	public String toString() {
		return "item_consumo_medidor(" + ",iditem_consumo_medidor=" + C1iditem_consumo_medidor + " ,fecha_creado=" + C2fecha_creado + " ,lectura_kw=" + C3lectura_kw + " ,fk_iddato_medidor=" + C4fk_iddato_medidor + " )";
	}
}
