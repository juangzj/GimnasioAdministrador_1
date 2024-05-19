package Controlador;

import Modelo.Membresia;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Goyes
 *
 * En este servlet se manejara el cambio de información para la eliminacion de
 * una membresia
 */
@WebServlet(name = "SvEliminarMembresia", urlPatterns = {"/SvEliminarMembresia"})
public class SvEliminarMembresia extends HttpServlet {

    // atributo golbal  donde se gaurdara el id de la memrbesia
    int id;
    //linkedList donde se guardarn las membresias
    LinkedList<Membresia> listaMembresias = new LinkedList();
    //Llamado al metodo constructor de memrbesia para acceder a sus metodos(Controlador)
    Membresia controladorMembresia = new Membresia();
    //LLamado al metodo construcotr de Usuario para acceder a sus metodos
    Usuario controladorUsuario = new Usuario();
    //LinkedList donde se guardara la lista de usuarios
    LinkedList<Usuario> listaUsuarios = new LinkedList();

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
        processRequest(request, response);
        //Variable para la comprobacion y mensaje emergente de la eliminacion de la membresia
        String alertaEliminacionMem = "";

        //Pedimos el id para buscar la respectiva membresia y eliminarla
        String idS = request.getParameter("id");
        //verificamos que el usuario de al boton de eliminar para hacer la eliminacion de la memrbesia
        String valorBotonEliminar = request.getParameter("boton_eliminar");

        if (idS != null) {
            // conversionde el id de String a int
            id = Integer.parseInt(idS);
            //System.out.println("el valor del id guardado es: " + id);
        }
        if (valorBotonEliminar != null) {

            //Obtenemos el contexto del servlet
            ServletContext context = getServletContext();

            //System.out.println("proiceso para la eliminacion");
            //Deserializamos la lista de usuarios para la eliminacion de membresias
            listaUsuarios = controladorUsuario.deserializarListaUsuarios(context);
            //deserializamos la lista de memrbesias para buscar la membresia que se va a elimianr
            listaMembresias = controladorMembresia.deserializarListaMembresias(context);
            //LLamdo al metodo que eliminara la membresia y posteriomente serializará la lista de membresias de nuevo
            boolean comprobacionEliminacion = controladorMembresia.eliminarMembresia(listaMembresias, listaUsuarios, id, context);

            /**
             * Comprobamos si la eliminacion de la Membresia se realizó de forma
             * correcta para mostrar el mensaje emergente
             */
            if (comprobacionEliminacion == true) {
                alertaEliminacionMem = "true";
                HttpSession miSesion = request.getSession();//obtenemos la sesion 
                miSesion.setAttribute("alertaEliminacionMem", alertaEliminacionMem);// Mandamos el resultado de la eliminacion de la membresia mediante la sesion
            } else {
                alertaEliminacionMem = "false";
                HttpSession miSesion = request.getSession();//obtenemos la sesion 
                miSesion.setAttribute("alertaEliminacionMem", alertaEliminacionMem);// Mandamos el resultado de la eliminacion de la membresia mediante la sesion
            }

        }
        //Redirecionamos 
        request.getRequestDispatcher("Membresias.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
