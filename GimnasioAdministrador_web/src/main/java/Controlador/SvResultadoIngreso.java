/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Goyes
 */
@WebServlet(name = "SvResultadoIngreso", urlPatterns = {"/SvResultadoIngreso"})
public class SvResultadoIngreso extends HttpServlet {

    //LLamado al metodo constructor de usuario para acceder a sus metodos
    Usuario controladorUsuario = new Usuario();

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
        //Obtenemos el contexto del servelt
        ServletContext context = request.getServletContext();

        //Obtenemos la lista de usuario
        LinkedList<Usuario> listaUsuarios = controladorUsuario.deserializarListaUsuarios(context);

        //Obetnemos la cedula ingresada
        String cedulaIngreso = request.getParameter("cedulaIngreso");
        System.out.println("la cedula de ingreso es: " + cedulaIngreso);

        if (cedulaIngreso.trim() != null) {
            //Lllamado al metodo para obtener el usaurio segun su id
            Usuario usuarioInfo = controladorUsuario.obtenerUSuarioIdentifiacion(listaUsuarios, cedulaIngreso.trim(), context);
            // Envialos el usuario a travez de la sesion
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuarioInfo", usuarioInfo);

            //redireccionamos a la pagina Membresias.jsp para la visualizacion de las membresis en la tabla
            request.getRequestDispatcher("ResultadoIngreso.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
