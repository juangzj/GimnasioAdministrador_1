package Controlador;

import Modelo.Membresia;
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
@WebServlet(name = "SvEditarMembresia", urlPatterns = {"/SvEditarMembresia"})
public class SvEditarMembresia extends HttpServlet {

    // Llamado al metodo contructor de membresia para acceder a sus metodos(Controlador)
    Membresia controladorMembresia = new Membresia();
    //LinkedList donde se guardaran los usuarios
    LinkedList listaMembresias = new LinkedList();
    //Variable global donde se guarada el id temporalmente
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
        String valorBotonEditar = request.getParameter("boton_editar");

        System.out.println("El valor del boton para la edicion de la membresia es: " + valorBotonEditar);

        if (idS != null) {
            id = Integer.parseInt(idS);
            //System.out.println("el id de la membresia que se va a editar es: " + id);
        }
        if (valorBotonEditar == null) {
            String nomMembresiaEditarS = request.getParameter("nomMembresiaEditar");
            String duracionEditarS = request.getParameter("duracionEditar");
            String precioEditarS = request.getParameter("precioEditar");
            //System.out.println("Membresia editar: " + nomMembresiaEditarS);
            //System.out.println("Duracion Editar: " + duracionEditarS);
            //System.out.println("precio Editar: " + precioEditarS);

            if (nomMembresiaEditarS != null && duracionEditarS != null && precioEditarS != null) {

                //Obtenemos el contexto del servlet
                ServletContext context = getServletContext();

                //Conversion de variables 
                long duracionEditar = Long.parseLong(duracionEditarS);
                double precioEditar = Double.parseDouble(precioEditarS);

                //Deserializamos la lista de membresias con las listas existentes
                listaMembresias = controladorMembresia.deserializarListaMembresias(context);

                //Llamado al metodo para editar la membresia y serializarla posteriormente
                controladorMembresia.editarMembresia(listaMembresias, id, context, nomMembresiaEditarS, duracionEditar, precioEditar);

            }
        }
        //Redireccionar a la pagina "Membresias.jsp"  para ver las memrbesias
        request.getRequestDispatcher("Membresias.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
