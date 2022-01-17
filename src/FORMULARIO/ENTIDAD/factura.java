	package FORMULARIO.ENTIDAD;
public class factura {

//---------------DECLARAR VARIABLES---------------
private int C1idfactura;
private String C2fecha_creado;
private double C3monto_total;
private double C4iva10;
private String C5monto_letra;
private String C6estado;
private int C7fk_idcliente;
private int C8fk_idempresa;
private int C9fk_idusuario;
private static String nom_tabla;
private static String nom_idtabla;
//---------------TABLA-ID---------------
	public factura() {
		setTb_factura("factura");
		setId_idfactura("idfactura");
	}
	public static String getTb_factura(){
		return nom_tabla;
	}
	public static void setTb_factura(String nom_tabla){
		factura.nom_tabla = nom_tabla;
	}
	public static String getId_idfactura(){
		return nom_idtabla;
	}
	public static void setId_idfactura(String nom_idtabla){
		factura.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idfactura(){
		return C1idfactura;
	}
	public void setC1idfactura(int C1idfactura){
		this.C1idfactura = C1idfactura;
	}
	public String getC2fecha_creado(){
		return C2fecha_creado;
	}
	public void setC2fecha_creado(String C2fecha_creado){
		this.C2fecha_creado = C2fecha_creado;
	}
	public double getC3monto_total(){
		return C3monto_total;
	}
	public void setC3monto_total(double C3monto_total){
		this.C3monto_total = C3monto_total;
	}
	public double getC4iva10(){
		return C4iva10;
	}
	public void setC4iva10(double C4iva10){
		this.C4iva10 = C4iva10;
	}
	public String getC5monto_letra(){
		return C5monto_letra;
	}
	public void setC5monto_letra(String C5monto_letra){
		this.C5monto_letra = C5monto_letra;
	}
	public String getC6estado(){
		return C6estado;
	}
	public void setC6estado(String C6estado){
		this.C6estado = C6estado;
	}
	public int getC7fk_idcliente(){
		return C7fk_idcliente;
	}
	public void setC7fk_idcliente(int C7fk_idcliente){
		this.C7fk_idcliente = C7fk_idcliente;
	}
	public int getC8fk_idempresa(){
		return C8fk_idempresa;
	}
	public void setC8fk_idempresa(int C8fk_idempresa){
		this.C8fk_idempresa = C8fk_idempresa;
	}
	public int getC9fk_idusuario(){
		return C9fk_idusuario;
	}
	public void setC9fk_idusuario(int C9fk_idusuario){
		this.C9fk_idusuario = C9fk_idusuario;
	}
	public String toString() {
		return "factura(" + ",idfactura=" + C1idfactura + " ,fecha_creado=" + C2fecha_creado + " ,monto_total=" + C3monto_total + " ,iva10=" + C4iva10 + " ,monto_letra=" + C5monto_letra + " ,estado=" + C6estado + " ,fk_idcliente=" + C7fk_idcliente + " ,fk_idempresa=" + C8fk_idempresa + " ,fk_idusuario=" + C9fk_idusuario + " )";
	}
}
