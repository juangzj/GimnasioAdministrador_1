package Controlador;

import Modelo.TiempoEjecucion;
import Modelo.Usuario;
import Modelo.UsuarioAdministrador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Goyes
 *
 * Servlet para el usuarioAdministrador
 */
@WebServlet(name = "SvUsuarioAdministrador", urlPatterns = {"/SvUsuarioAdministrador"})
public class SvUsuarioAdministrador extends HttpServlet {

    TiempoEjecucion tiempo;

    @Override
    public void init() throws ServletException {
        //LLamado al metodo constructor de usuario para acceder a sus metodos
        Usuario usuarioP = new Usuario();
        //Obtenemos el contexto del servlet
        ServletContext context = getServletContext();
        //LinkedList donde se guardaran los usuarios
        LinkedList<Usuario> listaUsuarios = usuarioP.deserializarListaUsuarios(context);

        if (listaUsuarios != null) {
            try {
                usuarioP.disminuciondiasDiasMembresia(listaUsuarios, context);//Hacemos la disminucion de los dias al ejecutar el programa
            } catch (IOException ex) {
                Logger.getLogger(SvUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                usuarioP.serializarListaUsuarios(listaUsuarios, context); //Serealizamos con los cambis anteriormente hecho
            } catch (IOException ex) {
                Logger.getLogger(SvUsuarioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tiempo = new TiempoEjecucion(context);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Variables globales para las alertas de inicio de sesion
        String alertaInicioSesion = "";

        //Pedimos los datos ingresador por el usuario 
        String nomUsuario = request.getParameter("nomUsuario");
        String contrasenia = request.getParameter("contrasenia");

        System.out.println("el usuario es: " + nomUsuario + " y la contraseña es: " + contrasenia);

        //Llamado al metodo contructor del modelo: "UsuarioAdminsitrados" para acceder a sus metodos  
        UsuarioAdministrador usuarioAdministrador = new UsuarioAdministrador();

        //Llamado al metodo de inisioSesion para hacer la verificacion del inicio de sesion y de acuerdo a esta, dar acceso o negar el acceso
        boolean comprobacionInicioSesion = usuarioAdministrador.inicioSesion(nomUsuario, contrasenia);

        //Redireccionar ala plataforma o emitir un mensaje emergente que diga que el acceso no fue exitoso
        if (comprobacionInicioSesion == true) { // Si los credenciales son correctos, el usuario accedera a la plataforma
            System.out.println("Ingreso a la plataforma de manera exitosa");
            response.sendRedirect("GimnasioAdministradorPrincipalInterfaz.jsp");

        } else {//De lo contrario, se mostrara una alerta de error y se redireccionara a la misma pagina
            alertaInicioSesion = "false";
            HttpSession miSesion = request.getSession();//Obtenemos la sesion
            miSesion.setAttribute("alertaInicioSesion", alertaInicioSesion);// Mandamos el resutlado del inicio de sesion
            response.sendRedirect("index.jsp");//redireccion a la misma pagina 
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
