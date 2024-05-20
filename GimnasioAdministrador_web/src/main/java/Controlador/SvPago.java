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

/**
 *
 * @author Juan Goyes
 */
@WebServlet(name = "SvPago", urlPatterns = {"/SvPago"})
public class SvPago extends HttpServlet {

    //LLmado al metodo constructor de usaurio para acceder a sus metodos
    Usuario controladorUsuario = new Usuario();
    //LLamado al metodo constructor de membresia para acceder a sus metodo
    Membresia controladorMembresia = new Membresia();
    //Variable donde se guardara la identifiacion del usuario temporalmente
    String identificacionUsuario = "";
    //Variable donde se guardara la membresia del usuario
    String idMembresiaPago = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Obtenemos los parametros para realizar el pago
        String pagoConf = request.getParameter("pagoConf");
        String identificacion = request.getParameter("idPagoUsuario");
        String idMembresia = request.getParameter("idMembresiaPago");
        //Obtenemos el contexto del servlet
        ServletContext context = getServletContext();
        System.out.println("la confirmacion del pago es: " + pagoConf);

        if (identificacion != null) {
            identificacionUsuario = identificacion.trim();
            System.out.println("El id del usaurio es: " + identificacionUsuario);
        }
        if (idMembresia != null) {
            System.out.println("El id de la membresia es: " + idMembresia);
            idMembresiaPago = idMembresia.trim();

        }
        if (pagoConf != null) {
            //Obtenemos la lista de membresias registradas
            LinkedList<Membresia> listaMembresias = controladorMembresia.deserializarListaMembresias(context);
            //Obtenemos la lista de usuarios
            LinkedList<Usuario> listaUsuarios = controladorUsuario.deserializarListaUsuarios(context);
            //Buscamos la membresia que se le asignará al usuario
            Membresia membresiaPago = controladorMembresia.buscarMembresiaId(listaMembresias, Integer.parseInt(idMembresiaPago));
            //LLamado al metodo para realizar el pago 
            controladorUsuario.realizarPago(identificacionUsuario, idMembresiaPago, listaUsuarios, membresiaPago, context);
        }

        //redireccionamos a la pagina Membresias.jsp para la visualizacion de las membresis en la tabla
        request.getRequestDispatcher("GimnasioAdministradorPrincipalInterfaz.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
