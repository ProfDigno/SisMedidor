	package FORMULARIO.ENTIDAD;
public class cliente {

    /**
     * @return the C9dia_fac_global
     */
    public static int getC9dia_fac_global() {
        return C9dia_fac_global;
    }

    /**
     * @param aC9dia_fac_global the C9dia_fac_global to set
     */
    public static void setC9dia_fac_global(int aC9dia_fac_global) {
        C9dia_fac_global = aC9dia_fac_global;
    }

    /**
     * @return the C9dia_fac
     */
    public int getC9dia_fac() {
        return C9dia_fac;
    }

    /**
     * @param C9dia_fac the C9dia_fac to set
     */
    public void setC9dia_fac(int C9dia_fac) {
        this.C9dia_fac = C9dia_fac;
    }

    /**
     * @return the C10activo
     */
    public boolean getC10activo() {
        return C10activo;
    }

    /**
     * @param C10activo the C10activo to set
     */
    public void setC10activo(boolean C10activo) {
        this.C10activo = C10activo;
    }

    /**
     * @return the C8fk_iddato_medidor_global
     */
    public static int getC8fk_iddato_medidor_global() {
        return C8fk_iddato_medidor_global;
    }

    /**
     * @param aC8fk_iddato_medidor_global the C8fk_iddato_medidor_global to set
     */
    public static void setC8fk_iddato_medidor_global(int aC8fk_iddato_medidor_global) {
        C8fk_iddato_medidor_global = aC8fk_iddato_medidor_global;
    }

//---------------DECLARAR VARIABLES---------------
private int C1idcliente;
private static int C1idcliente_global;
private String C2fecha_creacion;
private String C3nombre;
private String C4cedula;
private String C5ruc;
private String C6telefono;
private String C7ubicacion;
private int C8fk_iddato_medidor;
private int C9dia_fac;
private static int C9dia_fac_global;
private boolean C10activo;
private static int C8fk_iddato_medidor_global;
private static String nom_tabla;
private static String nom_idtabla;
private static boolean busca_factura;
private static boolean busca_usuario;
//---------------TABLA-ID---------------
	public cliente() {
		setTb_cliente("cliente");
		setId_idcliente("idcliente");
	}
	public static String getTb_cliente(){
		return nom_tabla;
	}
	public static void setTb_cliente(String nom_tabla){
		cliente.nom_tabla = nom_tabla;
	}
	public static String getId_idcliente(){
		return nom_idtabla;
	}
	public static void setId_idcliente(String nom_idtabla){
		cliente.nom_idtabla = nom_idtabla;
	}
//---------------GET-SET-CAMPOS---------------
	public int getC1idcliente(){
		return C1idcliente;
	}
	public void setC1idcliente(int C1idcliente){
		this.C1idcliente = C1idcliente;
	}
	public String getC2fecha_creacion(){
		return C2fecha_creacion;
	}
	public void setC2fecha_creacion(String C2fecha_creacion){
		this.C2fecha_creacion = C2fecha_creacion;
	}
	public String getC3nombre(){
		return C3nombre;
	}
	public void setC3nombre(String C3nombre){
		this.C3nombre = C3nombre;
	}
	public String getC4cedula(){
		return C4cedula;
	}
	public void setC4cedula(String C4cedula){
		this.C4cedula = C4cedula;
	}
	public String getC5ruc(){
		return C5ruc;
	}
	public void setC5ruc(String C5ruc){
		this.C5ruc = C5ruc;
	}
	public String getC6telefono(){
		return C6telefono;
	}
	public void setC6telefono(String C6telefono){
		this.C6telefono = C6telefono;
	}
	public String getC7ubicacion(){
		return C7ubicacion;
	}
	public void setC7ubicacion(String C7ubicacion){
		this.C7ubicacion = C7ubicacion;
	}
	public int getC8fk_iddato_medidor(){
		return C8fk_iddato_medidor;
	}
	public void setC8fk_iddato_medidor(int C8fk_iddato_medidor){
		this.C8fk_iddato_medidor = C8fk_iddato_medidor;
	}
	public String toString() {
		return "cliente(" + ",idcliente=" + C1idcliente + " ,fecha_creacion=" + C2fecha_creacion + " ,nombre=" + C3nombre + " ,cedula=" + C4cedula + " ,ruc=" + C5ruc + " ,telefono=" + C6telefono + " ,ubicacion=" + C7ubicacion + " ,fk_iddato_medidor=" + C8fk_iddato_medidor + " )";
	}

    /**
     * @return the C1idcliente_global
     */
    public static int getC1idcliente_global() {
        return C1idcliente_global;
    }

    /**
     * @param aC1idcliente_global the C1idcliente_global to set
     */
    public static void setC1idcliente_global(int aC1idcliente_global) {
        C1idcliente_global = aC1idcliente_global;
    }

    public static boolean isBusca_factura() {
        return busca_factura;
    }

    public static void setBusca_factura(boolean busca_factura) {
        cliente.busca_factura = busca_factura;
    }

    public static boolean isBusca_usuario() {
        return busca_usuario;
    }

    public static void setBusca_usuario(boolean busca_usuario) {
        cliente.busca_usuario = busca_usuario;
    }
    
}
