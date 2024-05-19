package Controlador;

import Modelo.Membresia;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
@WebServlet(name = "SvAniadirUsuario", urlPatterns = {"/SvAniadirUsuario"})
public class SvAniadirUsuario extends HttpServlet {

    //LLamado al metodo constructor de Usuario para accerder a sus metodos
    Usuario controladorUsuario = new Usuario();
    //LinkedList donde se gurdará la lista de usuairos
    LinkedList<Usuario> listaUsuarios;
    //Llamado al metodo constructor de membresia para acceder a sus metodos
    Membresia controladorMembresia = new Membresia();
    //LinkedList donde se guarada la lista de Membresias
    LinkedList<Membresia> listaMembresias;
    //Variable donde se guardara el id de la membresia temporalmente
    int id;

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

        String idS = request.getParameter("id");
        String nomUsuario = request.getParameter("nomUsuario");
        String identificacion = request.getParameter("identificacion");
        System.out.println("La id de la membresia que se ha asignado es: " + idS);

        if (idS != null) {
            id = Integer.parseInt(idS);
        }
        if (nomUsuario != null && identificacion != null) {
            //Obtenemos el contexto del servlet
            ServletContext context = getServletContext();
            
            listaMembresias = controladorMembresia.deserializarListaMembresias(context);
            //Buscamos la membresia de acuerdo al id escogido
            Membresia membresiaAsignar = controladorMembresia.buscarMembresiaId(listaMembresias, id);
            System.out.println("el nombre de la membresia es: " + membresiaAsignar.getNomMembresia());
            LocalDate fechaInicio = controladorUsuario.obtenerFechaActual();
            long duracionMembresia = membresiaAsignar.getDuracion();
            LocalDate fechaFIn = controladorUsuario.convertirDuracionAFecha(duracionMembresia);
            LocalDate fechaActual = controladorUsuario.obtenerFechaActual();

            //LinkedList donde se guardara la lista de usuarios para hacer la comprobacion
            LinkedList<Usuario> listaUsuariosComprobacion = controladorUsuario.deserializarListaUsuarios(context);
            /**
             * Comprobamos si la lista de usuarios es vacia, si no esta vacia,
             * se guardara la lista de usuarios para que no se pierda al aniadir
             * un nuevo usuario
             */
            if (listaUsuariosComprobacion != null && !listaUsuariosComprobacion.isEmpty()) {
                listaUsuarios = listaUsuariosComprobacion;
            }
            controladorUsuario.aniadirUsuario(nomUsuario, identificacion, membresiaAsignar, fechaInicio,fechaActual, fechaFIn,"Activa", listaUsuarios, context);

        }
        //redireccionamos a la pagina Membresias.jsp para la visualizacion de las membresis en la tabla
        request.getRequestDispatcher("GimnasioAdministradorPrincipalInterfaz.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
