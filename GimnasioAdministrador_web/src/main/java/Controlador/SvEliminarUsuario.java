package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Goyes
 */
@WebServlet(name = "SvEliminarUsuario", urlPatterns = {"/SvEliminarUsuario"})
public class SvEliminarUsuario extends HttpServlet {

    //LLamado al metodo constructor de usuario para acceder a sus metodos
    Usuario controladorUsuario = new Usuario();
    LinkedList<Usuario> listaUsuarios;
    //Variable donde se guardará el numero de identificacion temporalmente
    private String identificacionTemp;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtenemos el contexto del servlet
        ServletContext context = getServletContext();

        String identificacion = request.getParameter("identificacion");
        String eliminarConfirmacion = request.getParameter("eliminarConfirmacion");

        if (identificacion != null) {
            identificacionTemp = identificacion.trim(); // Borramos los espacios dentro del String
        }
        if (eliminarConfirmacion != null) {
            //Deserializamos la lista para obtener los usuarios registrados
            listaUsuarios = controladorUsuario.deserializarListaUsuarios(context);
            //Comprobamos la eliminacion y de acuerdo a esto, mostramos un mensaje emergente
            controladorUsuario.eliminarUsuario(listaUsuarios, identificacionTemp, context);
        }

        //redireccionamos a la pagina Membresias.jsp para la visualizacion de las membresis en la tabla
        request.getRequestDispatcher("GimnasioAdministradorPrincipalInterfaz.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
