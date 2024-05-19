package Controlador;

import Modelo.Membresia;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.LinkedList;
import javax.servlet.ServletConfig;
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
 */
@WebServlet(name = "SvMembresia", urlPatterns = {"/SvMembresia"})
public class SvMembresia extends HttpServlet {

    //LinkedList donde se guardara la lista de membresias 
    LinkedList<Membresia> listaMembresias = new LinkedList();
    //Llamado al metodo constructor de membresia vacio para acceder a sus metodos
    Membresia membresia = new Membresia();
    //Obtenemos el contexto del servlet
    private ServletContext context;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Variabes para la comprobacion al momento de agregar una nueva Membresia y mostrar el mensaje emergente
        String alertaMembresia = "";

        //Obtenemos el contexto del servlet
        ServletContext context = request.getServletContext();

        //=========================================================================================
        //Pedimos todos los datos ingresador por el usuario en la interfaz
        String nomMembresia = request.getParameter("nomMembresia");
        String duracionS = request.getParameter("duracion");
        String precioS = request.getParameter("precio");

        System.out.println("El nombre de la membresia es: " + nomMembresia);

        //===========================AGREGAR MEMBRESIA==========================================
        if (nomMembresia != null && duracionS != null && precioS != null) {

            //Se covierte el tipo de dato al necesario
            long duracion = Long.parseLong(duracionS);
            double precio = Double.parseDouble(precioS);

            //comprobamos si ya hay Membresias en la lista
            LinkedList<Membresia> membresiaComprobacion = membresia.deserializarListaMembresias(context);

            /**
             * Comprobamos si la lista de membresias "listaMembresias" es vacia,
             * de lo contrario se guarda la lista deserializada en la lista de
             * Membresias para que no se pierdan las membresias ya registradas
             */
            if (membresiaComprobacion != null && !membresiaComprobacion.isEmpty()) {
                listaMembresias = membresiaComprobacion;
            }

            //Llamado al metodo para añadir membresia a la lista y serializar
            listaMembresias = membresia.aniadirMembresia(nomMembresia, duracion, precio, "descripcion", listaMembresias);

            //Llamado a metodo para obtener la comprobacion de la membresia (Si se agrego de forma correcta o no se puedo agregar)
            boolean comprobacionMembresiaME = membresia.isAniadirMembresiaComporbacion();

            /**
             * Comprobamos el estado de la variable boolean
             * comprobacionMembresiaME para mostrar un mensaje en la interfaz
             * que diga que se agrego correctamente o no la membresia
             */
            if (comprobacionMembresiaME == true) {// SI se agregó correctamente la membresia se mostrara el mensaje emergente
                alertaMembresia = "true";
                HttpSession miSesion = request.getSession();//Obtenemos la sesion
                miSesion.setAttribute("alertaMembresia", alertaMembresia);//Enviamos el resutlado de la variable mediante la sesion
            }

            //llamado al metodo para dar las IDs a las membresias
            membresia.darId(listaMembresias, context);

        }

        //redireccionamos a la pagina Membresias.jsp para la visualizacion de las membresis en la tabla
        request.getRequestDispatcher("Membresias.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
