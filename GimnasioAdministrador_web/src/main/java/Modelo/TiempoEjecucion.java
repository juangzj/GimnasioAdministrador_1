package Modelo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 *
 * @author Juan Goyes
 */
public class TiempoEjecucion {

    //
    private final ServletContext context;
    //Timer para ejecutar y dar tiempo
    private final Timer tiempo = new Timer();

    /**
     * Metodo contructor para TiempoEjecucion
     *
     * @param context
     */
    public TiempoEjecucion(ServletContext context) {
        this.context = context;
        tiempo.schedule(tarea, 0, 24 * 60 * 60 * 1000);
    }

    TimerTask tarea = new TimerTask() {
        @Override
        public void run() {
            LocalDate fechaActual = LocalDate.now();
            Usuario controladorUsuario = new Usuario();
            LinkedList<Usuario> listaUsuarios = controladorUsuario.deserializarListaUsuarios(context);
            if (listaUsuarios != null) {
                for (Usuario usuario : listaUsuarios) {
                    usuario.setFechaActual(fechaActual);

                }
                for (Usuario usuarioEstado : listaUsuarios) {
                    if (fechaActual.equals(usuarioEstado.getFechaFIn())) {
                        usuarioEstado.setEstado("Vencida");
                    }
                }

                try {
                    controladorUsuario.serializarListaUsuarios(listaUsuarios, context);
                } catch (IOException ex) {
                    Logger.getLogger(TiempoEjecucion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Si la lista de usuarios es null, inicialízala como una lista vacía
                listaUsuarios = new LinkedList<>();
                try {
                    controladorUsuario.serializarListaUsuarios(listaUsuarios, context);
                } catch (IOException ex) {
                    Logger.getLogger(TiempoEjecucion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    };

}
