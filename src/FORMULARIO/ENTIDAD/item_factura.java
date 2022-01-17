	package FORMULARIO.ENTIDAD;
public class item_factura {

//---------------DECLARAR VARIABLES---------------
private int C1iditem_factura;
private String C2fecha_creado;
private String C3fecha_inicio;
private String C4fecha_final;
private String C5descripcion;
private double C6inicio_kw;
private double C7final_kw;
private double C8monto_tarifa;
private int C9fk_iddato_medidor;
private int C10fk_idtarifa;
private int C11fk_idfactura;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public item_factura() {
		setTb_item_factura("item_factura");
		setId_iditem_factura("iditem_factura");
	}
	public static String getTb_item_factura(){
		return nom_tabla;
	}
	public static void setTb_item_factura(String nom_tabla){
		item_factura.nom_tabla = nom_tabla;
	}
	public static String getId_iditem_factura(){
		return nom_idtabla;
	}
	public static void setId_iditem_factura(String nom_idtabla){
		item_factura.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1iditem_factura(){
		return C1iditem_factura;
	}
	public void setC1iditem_factura(int C1iditem_factura){
		this.C1iditem_factura = C1iditem_factura;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public String getC3fecha_inicio(){
		return C3fecha_inicio;
	}
	public void setC3fecha_inicio(String C3fecha_inicio){
		this.C3fecha_inicio = C3fecha_inicio;
	}
	public String getC4fecha_final(){
		return C4fecha_final;
	}
	public void setC4fecha_final(String C4fecha_final){
		this.C4fecha_final = C4fecha_final;
	}
	public String getC5descripcion(){
		return C5descripcion;
	}
	public void setC5descripcion(String C5descripcion){
		this.C5descripcion = C5descripcion;
	}
	public double getC6inicio_kw(){
		return C6inicio_kw;
	}
	public void setC6inicio_kw(double C6inicio_kw){
		this.C6inicio_kw = C6inicio_kw;
	}
	public double getC7final_kw(){
		return C7final_kw;
	}
	public void setC7final_kw(double C7final_kw){
		this.C7final_kw = C7final_kw;
	}
	public double getC8monto_tarifa(){
		return C8monto_tarifa;
	}
	public void setC8monto_tarifa(double C8monto_tarifa){
		this.C8monto_tarifa = C8monto_tarifa;
	}
	public int getC9fk_iddato_medidor(){
		return C9fk_iddato_medidor;
	}
	public void setC9fk_iddato_medidor(int C9fk_iddato_medidor){
		this.C9fk_iddato_medidor = C9fk_iddato_medidor;
	}
	public int getC10fk_idtarifa(){
		return C10fk_idtarifa;
	}
	public void setC10fk_idtarifa(int C10fk_idtarifa){
		this.C10fk_idtarifa = C10fk_idtarifa;
	}
	public int getC11fk_idfactura(){
		return C11fk_idfactura;
	}
	public void setC11fk_idfactura(int C11fk_idfactura){
		this.C11fk_idfactura = C11fk_idfactura;
	}
	public String toString() {
		return "item_factura(" + ",iditem_factura=" + C1iditem_factura + " ,fecha_creado=" + C2fecha_creado + " ,fecha_inicio=" + C3fecha_inicio + " ,fecha_final=" + C4fecha_final + " ,descripcion=" + C5descripcion + " ,inicio_kw=" + C6inicio_kw + " ,final_kw=" + C7final_kw + " ,monto_tarifa=" + C8monto_tarifa + " ,fk_iddato_medidor=" + C9fk_iddato_medidor + " ,fk_idtarifa=" + C10fk_idtarifa + " ,fk_idfactura=" + C11fk_idfactura + " )";
	}
}
