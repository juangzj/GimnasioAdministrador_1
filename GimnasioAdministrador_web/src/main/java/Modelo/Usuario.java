package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.ServletContext;

/**
 *
 * @author Juan Goyes
 */
public class Usuario implements Serializable {

    //Variables globales
    private boolean eliminarUsuarioComprobacion = false;

    //Atributos de un usuario
    private String nomUsuario;
    private String numIdentificacion;
    private Membresia membresia;
    private LocalDate fechaActual;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Long diasFaltantes;
    private String estado;

    /**
     * Metodo contructor vacio
     */
    public Usuario() {
        
    }
    
    public Usuario(String nomUsuario, String numIdentificacion, Membresia membresia, LocalDate fechaInicio, LocalDate fechaActual, LocalDate fechaFin, String estado) {
        this.nomUsuario = nomUsuario;
        this.numIdentificacion = numIdentificacion;
        this.membresia = membresia;
        this.fechaInicio = fechaInicio;
        this.fechaActual = fechaActual;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }

//================================================================================================
    /**
     * Metodos getters y setters para acceder a los atributos de un usuario
     */
    public String getNomUsuario() {
        return nomUsuario;
    }
    
    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }
    
    public String getNumIdentificacion() {
        return numIdentificacion;
    }
    
    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }
    
    public Membresia getMembresia() {
        return membresia;
    }
    
    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }
    
    public LocalDate getFechaActual() {
        return fechaActual;
    }
    
    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    
    public void setFechaInicio(LocalDate FechaInicio) {
        this.fechaInicio = FechaInicio;
    }
    
    public LocalDate getFechaFIn() {
        return fechaFin;
    }
    
    public void setFechaFIn(LocalDate fechaFIn) {
        this.fechaFin = fechaFIn;
    }
    
    public Long getDiasFaltantes() {
        return diasFaltantes;
    }
    
    public void setDiasFaltantes(Long diasFaltantes) {
        this.diasFaltantes = diasFaltantes;
    }
    
    public String isEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    //================================================================================
    /**
     * Metdo para obtener la fecha actual segun el sistema
     *
     * @return fechaActual
     */
    public LocalDate obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        return fechaActual;
    }

    /**
     * Metdo para convertir la duracion de dias ingresado por el usuario a una
     * fecha de finalizacion de tipo LocalDate
     *
     * @param dias
     * @return fechaFin
     */
    public LocalDate convertirDuracionAFecha(Long dias) {
        //obtenemos la fechaActual
        LocalDate fechaActual = LocalDate.now();
        //Sumamos los dias que el usuario haya ingresado como duracion de la membresia
        LocalDate fechaFin = fechaActual.plusDays(dias);
        //Retornamos la fecha de finalizacion de la membresia
        return fechaFin;
    }

    /**
     * Metodo para dar el nombre de la membresia asignada al usuario
     *
     * @param listaUsuarios
     * @param listaMembresias
     * @param usuario
     * @return
     */
    public String buscarMembresiaDeUnUsuario(LinkedList<Usuario> listaUsuarios, LinkedList<Membresia> listaMembresias, Usuario usuario) {
        // Obtener la membresía del usuario
        Membresia membresiaUsuario = usuario.getMembresia();

        // Verificar si el usuario tiene una membresía asignada
        if (membresiaUsuario != null) {
            // Obtener el ID de la membresía del usuario
            int idMembresiaUsuario = membresiaUsuario.getId();

            // Iterar sobre la lista de membresías para encontrar la membresía con el mismo ID
            for (Membresia membresia : listaMembresias) {
                if (membresia.getId() == idMembresiaUsuario) {
                    // Retornar el nombre de la membresía
                    return membresia.getNomMembresia();
                }
            }
        }
        // Si el usuario no tiene una membresía asignada o no se encuentra la membresía, retornar una cadena vacía o un mensaje de error
        return ""; // O podrías devolver un mensaje de error específico, como "Membresía no encontrada"
    }

    /**
     * Metodo para agregar un nuevo usuario Este metodo verifica si la cedula
     * ingresada ya esta en uso, si esta ya esta en uso, no dejara agregar el
     * usuario
     *
     * @param nomUsuario
     * @param numIdentificacion
     * @param membresia
     * @param fechaInicio
     * @param fechaActual
     * @param fechaFin
     * @param listaUsuarios
     * @param context
     * @return
     * @throws java.io.IOException
     */
    public boolean aniadirUsuario(String nomUsuario, String numIdentificacion, Membresia membresia, LocalDate fechaInicio, LocalDate fechaActual, LocalDate fechaFin, String estado, LinkedList<Usuario> listaUsuarios, ServletContext context) throws IOException {
        // Inicialización de variable booleana para hacer la comprobación
        boolean comprobacionNuevoRegistro = false;
        
        if (listaUsuarios == null) { // Si la lista de usuarios está vacía, se agrega el primer usuario
            // Inicializar la lista de usuarios
            listaUsuarios = new LinkedList<>();
            // Llamado al método constructor con atributos para crear un nuevo usuario con los atributos ingresados por el usuario
            Usuario primerUsuario = new Usuario(nomUsuario, numIdentificacion, membresia, fechaInicio, fechaActual, fechaFin, estado);
            // Se agrega el usuario a la lista de usuarios
            listaUsuarios.add(primerUsuario);
            // Se serializa la lista de usuarios
            serializarListaUsuarios(listaUsuarios, context);
            //System.out.println("Se agregó el primer usuario");
        } else {
            // Si la lista de usuarios no está vacía, verificamos si la cédula ya está en uso
            for (Usuario usuario : listaUsuarios) {
                // Verificar si el número de identificación no es nulo antes de llamar a equals
                if (numIdentificacion != null && numIdentificacion.equals(usuario.getNumIdentificacion())) {
                    comprobacionNuevoRegistro = true; // Si la cédula ya está en uso, se convierte la variable de comprobación "comprobacionNuevoRegistro" en true
                    // System.out.println("La cédula ya está en uso");
                    break;
                }
            }
            // Si la cédula no está en uso, agregamos el nuevo usuario
            if (!comprobacionNuevoRegistro) {
                // Creamos un nuevo Usuario de acuerdo a los parámetros ingresados por el usuario
                Usuario nuevoUsuario = new Usuario(nomUsuario, numIdentificacion, membresia, fechaInicio, fechaActual, fechaFin, estado);
                // Guardamos el usuario en la lista de usuarios
                listaUsuarios.add(nuevoUsuario);
                // Llamado al método para serializar la lista de usuarios
                serializarListaUsuarios(listaUsuarios, context);
            }
        }
        return comprobacionNuevoRegistro;
    }

    /**
     * Metodo para serializar la lista de usuarios
     *
     * @param listaUsuarios
     * @param context
     * @throws IOException
     */
    public void serializarListaUsuarios(LinkedList<Usuario> listaUsuarios, ServletContext context) throws IOException {
        String path = "data/ListaUsuarios.dat";
        String Rpath = context.getRealPath(path);
        try (FileOutputStream archivo = new FileOutputStream(Rpath); ObjectOutputStream out = new ObjectOutputStream(archivo)) {
            out.writeObject(listaUsuarios);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Metodo para deserializar la lista de usuarios
     *
     * @param context
     * @return
     */
    public LinkedList<Usuario> deserializarListaUsuarios(ServletContext context) {
        LinkedList<Usuario> listaUsuarios = null;
        try {
            String path = "data/ListaUsuarios.dat";
            String Rpath = context.getRealPath(path);
            try (FileInputStream archivo = new FileInputStream(Rpath); ObjectInputStream in = new ObjectInputStream(archivo)) {
                listaUsuarios = (LinkedList<Usuario>) in.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    /**
     * Metodo para hacer la disminucion de dias de la membresia al ejecutar el
     * programa y verifica si los usuarios aun tienen la fecha activa
     *
     * @param listaUsuarios
     * @param context
     * @throws IOException
     */
    public void disminuciondiasDiasMembresia(LinkedList<Usuario> listaUsuarios, ServletContext context) throws IOException {
        // obtenemos la fecha actual
        LocalDate fechaActual = LocalDate.now();
        //Rocorremos todos los usuarios de la lista
        for (Usuario usuario : listaUsuarios) {
            //Comprobar si la fecha actual del usuario es nula o coincide con la fecha actual
            if (usuario.getFechaActual() == null || !usuario.getFechaActual().equals(fechaActual)) {

                // Obtiene la "fechaInicio" (fecha de inicio) del usuario.
                LocalDate fechaInicioUsuario = usuario.getFechaInicio();
                // Calcula el número de días transcurridos desde la fecha de inicio
                int diasTranscurridos = (int) fechaInicioUsuario.until(fechaActual).getDays();
                // Calcula la nueva "fechaActual" sumando el número de días transcurridos a la fecha de inicio
                LocalDate nuevaFechaActual = fechaInicioUsuario.plusDays(diasTranscurridos);
                // Actualiza la "fechaActual" del usuario con la nueva fecha calculada
                usuario.setFechaActual(nuevaFechaActual);
            }
        }
        estadoMembresia(listaUsuarios, context, fechaActual);
        
    }

    /**
     * Metodo para verificar el estado de la membresia, si la fecha actual es la
     * misma que la fecha de finalizacion, la membresia cambiara el estado a
     * "Vencida"
     *
     * @param listaUsuarios
     * @param context
     * @param fechaActual
     * @throws IOException
     */
    private void estadoMembresia(LinkedList<Usuario> listaUsuarios, ServletContext context, LocalDate fechaActual) throws IOException {
        
        for (Usuario usuario : listaUsuarios) {
            if (fechaActual.equals(usuario.getFechaFIn())) {
                usuario.setEstado("Vencida");
            }
        }
        // Serializa la lista actualizada de usuarios para persistir los cambios
        serializarListaUsuarios(listaUsuarios, context);
    }

    /**
     * Metodo para eliminar un usuario
     *
     * @param listaUsuarios
     * @param identificacionEliminacion
     * @param context
     * @return
     * @throws IOException
     */
    public boolean eliminarUsuario(LinkedList<Usuario> listaUsuarios, String identificacionEliminacion, ServletContext context) throws IOException {
        eliminarUsuarioComprobacion = false;

        //Iterar sobre la lista de usuarios
        Iterator<Usuario> iterator = listaUsuarios.iterator();
        while (iterator.hasNext()) {
            //Avanzamos dentro de la lista enlazada
            Usuario usuarioEliminar = iterator.next();
            if (identificacionEliminacion.equals(usuarioEliminar.getNumIdentificacion())) {// Verificaar si el Numero de identificacion coincide
                //Eliminamos el usuarios 
                iterator.remove();
                //Boolean para mostrar mensaje emergente
                eliminarUsuarioComprobacion = true;
                break; // Salir del bucle una vez que se haya eliminado el usuairo
            }
            
        }
        //Serializamos la lista actualizada
        serializarListaUsuarios(listaUsuarios, context);
        
        return eliminarUsuarioComprobacion;
    }

    /**
     * Metodo para obtener el usaurio segun el numero de identificacion
     *
     * @param listaUsuario
     * @param identificacion
     * @param context
     * @return
     */
    public Usuario obtenerUSuarioIdentifiacion(LinkedList<Usuario> listaUsuario, String identificacion, ServletContext context) {
        Usuario usuarioId = null;
        //Iterar sobre la lista de usuario         
        Iterator<Usuario> iterator = listaUsuario.iterator();
        
        while (iterator.hasNext()) {
            //Avanzamos dentro de la lista enlazada
            Usuario usuario = iterator.next();
            if (identificacion.trim().equals(usuario.getNumIdentificacion())) {
                usuarioId = usuario;
                break;
            }
        }
        
        return usuarioId;
    }

    /**
     * Metodo para hacer el pago de una nueva membresia
     *
     */
    public boolean realizarPago(String identificacion, String idMembresia, LinkedList<Usuario> listaUsuarios, Membresia membresia, ServletContext context) throws IOException {
        boolean pago = false;

        //Iterar sobre la lista de usuarior
        Iterator<Usuario> iterator = listaUsuarios.iterator();
        while (iterator.hasNext()) {
            //Avanzamos dentro de la lista enlazada
            Usuario usuario = iterator.next();
            if (identificacion.equals(usuario.getNumIdentificacion())) {
                //le damos la nueva fecha de inicio al usuairo 
                usuario.setFechaInicio(usuario.obtenerFechaActual());
                //obtenemos la duracion de la nuvea mebbresia
                long duracion = membresia.getDuracion();
                //Le damos la fecha de finalizacion segun su membresia
                usuario.setFechaFIn(usuario.convertirDuracionAFecha(duracion));
                break;
            }
            
        }
        serializarListaUsuarios(listaUsuarios, context);
        return pago;
    }
    
}
