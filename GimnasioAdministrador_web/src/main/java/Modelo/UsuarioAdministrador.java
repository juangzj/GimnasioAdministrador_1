package Modelo;

/**
 * @author Juan Goyes Clase del UsuarioAdministrador para el usuario que
 * administrará el gimnasio
 */
public class UsuarioAdministrador {

    //Atributos de UsuarioAdmionistrador
    private String usuario = "usuario";
    private String contrasenia = "usuario";
    //====================== Getters / Setters ==================================

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String Usuario) {
        this.usuario = Usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    //====================================================================

    /**
     * Metodo constructor vacio
     */
    public UsuarioAdministrador() {
    }
    /**
     * Metodo para hacer la verificacion de los credenciales que ingrese el usuario y asi dar acceso o negar a la plataforma segun sea el caso
     * @param nomUsuarioV
     * @param contraseniaV
     * @return boolean comprobacionIncioSesion 
     */

    public boolean inicioSesion(String nomUsuarioV, String contraseniaV){
        boolean comprobacionInicioSesion = false;
        if(nomUsuarioV.equals(usuario) && contraseniaV.equals(contrasenia)){
            comprobacionInicioSesion = true;
        }
        return  comprobacionInicioSesion;
    }
}
