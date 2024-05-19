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
public class Membresia implements Serializable {

    private static final long serialVersionUID = 519624087322994244L; // Mantener el mismo serialVersionUID

    //Varibales globales
    private boolean aniadirMembresiaComporbacion = false;
    private boolean eliminarMembresiaComprobacion = false;

    //Atributos de una Membresia
    private int id;
    private String nomMembresia;
    private Long duracion;
    private Double precio;
    private String descripcion;

    //Contructor de Membresia vacio
    public Membresia() {
    }

    /**
     * Metodo contructor con atributos de membresia
     *
     * @param id
     * @param nomMembresia
     * @param duracion
     * @param precio
     * @param descripcion
     */
    public Membresia(String nomMembresia, Long duracion, Double precio, String descripcion) {
        this.nomMembresia = nomMembresia;
        this.duracion = duracion;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    //=====================================METODOS GETTERS Y SETTERS =============================================
    public boolean isEliminarMembresiaComprobacion() {
        return eliminarMembresiaComprobacion;
    }

    public boolean isAniadirMembresiaComporbacion() {
        return aniadirMembresiaComporbacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMembresia() {
        return nomMembresia;
    }

    public void setNomMembresia(String nomMembresia) {
        this.nomMembresia = nomMembresia;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //==============================================================================
    /**
     * Metodo para dar IDs a las membresias
     *
     * @param listaMembresias
     * @return
     */
    public void darId(LinkedList<Membresia> listaMembresias, ServletContext context) throws IOException {
        IDManager idManager = new IDManager();

        for (Membresia membresia : listaMembresias) {
            int id = idManager.getUniqueID();
            membresia.setId(id);
        }

        serializarListaMembresias(context, listaMembresias);

    }

    /**
     * Metedo para agregar una nueva Membresia
     *
     * @param nomMembresia
     * @param duracion
     * @param precio
     * @param descripcion
     * @param listaMembresia
     * @param context
     * @return
     */
    public LinkedList<Membresia> aniadirMembresia(String nomMembresia, Long duracion, double precio, String descripcion, LinkedList<Membresia> listaMembresia) {

        aniadirMembresiaComporbacion = false;

        if (nomMembresia != null && duracion != null && duracion != null) {
            //Llamado al metodo contrucor de una membresia
            Membresia nuevaMembresia = new Membresia(nomMembresia, duracion, precio, descripcion);
            // Agregamos la membresia a la lista de Membresias
            listaMembresia.add(nuevaMembresia);

            aniadirMembresiaComporbacion = true;
        }
        return listaMembresia;
    }

    /**
     * Metodo para serializar la lista de membresias
     *
     * @param context
     * @param listaMembresias
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void serializarListaMembresias(ServletContext context, LinkedList<Membresia> listaMembresias) throws FileNotFoundException, IOException {

        String path = "data/ListaMembresias.dat";

        String Rpath = context.getRealPath(path);

        //System.out.println("La ubicacione es: " + Rpath);
        FileOutputStream archivo = new FileOutputStream(Rpath);

        try (ObjectOutputStream out = new ObjectOutputStream(archivo)) {
            out.writeObject(listaMembresias);
            archivo.close();
            //System.out.println("Se ha guardado las membresias de forma correcta");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Metodo para deserializar la lista de membresias
     *
     * @param context
     * @return
     */
    public LinkedList<Membresia> deserializarListaMembresias(ServletContext context) {

        LinkedList<Membresia> listaMembresias = null;

        try {
            String path = "data/ListaMembresias.dat";
            String Rpath = context.getRealPath(path);
            FileInputStream archivo = new FileInputStream(Rpath);

            try (ObjectInputStream in = new ObjectInputStream(archivo)) {
                listaMembresias = (LinkedList<Membresia>) in.readObject();

                //for(Membresia membresia: listaMembresias){
                //    System.out.println("Se cargaron los usuarios de forma correcta");
                //}
            }
        } catch (FileNotFoundException e) {
            // Manejar FileNotFoundException aquí
            e.printStackTrace();
        } catch (IOException e) {
            // Manejar IOException aquí
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Manejar ClassNotFoundException aquí
            e.printStackTrace();
        }

        return listaMembresias;
    }

    /**
     * Metodo para eliminar una membresia
     *
     * @param listaMembresias
     * @param listaUsuarios
     * @param id
     * @param context
     * @return
     * @throws IOException
     */
    public boolean eliminarMembresia(LinkedList<Membresia> listaMembresias, LinkedList<Usuario> listaUsuarios, int id, ServletContext context) throws IOException {

        eliminarMembresiaComprobacion = false;
        boolean comprobacionMembresiaUsuario = false;

        // Iterar sobre la lista de membresías
        Iterator<Membresia> iterador = listaMembresias.iterator();
        while (iterador.hasNext()) {
            Membresia membresia = iterador.next();
            // Verificar si el ID de la membresía coincide
            if (membresia.getId() == id) {
                comprobacionMembresiaUsuario = false;
                for (Usuario usuarioMembresia : listaUsuarios) {
                    if (membresia.getId() == usuarioMembresia.getMembresia().getId()) {
                        comprobacionMembresiaUsuario = true;
                        break;
                    }
                }
                if (comprobacionMembresiaUsuario == false) {
                    comprobacionMembresiaUsuario = true;
                    // Eliminar la membresía actual del iterador
                    iterador.remove();
                    // Establecer la bandera en false ya que la membresía aún está en uso
                    eliminarMembresiaComprobacion = true;
                    break;
                } else {
                    //Se cambia la comprobacion para enviar un mensaje emergente
                    comprobacionMembresiaUsuario = true;
                    break;
                }
            }

        }
        // Serializar la lista actualizada de membresías
        serializarListaMembresias(context, listaMembresias);
        return eliminarMembresiaComprobacion;
    }

    /**
     * Metodo para editar una membresia
     *
     * @param listaMembresias
     * @param id
     * @param context
     * @param nomMembresia
     * @param duracion
     * @param precio
     * @throws IOException
     */
    public void editarMembresia(LinkedList<Membresia> listaMembresias, int id, ServletContext context, String nomMembresia, Long duracion, double precio) throws IOException {
        //Iterar sobre la lista de memrbesias
        Iterator<Membresia> iterator = listaMembresias.iterator();
        while (iterator.hasNext()) {
            //Avanzar dentro del ciclo while
            Membresia membresia = iterator.next();
            //Verificar si la membresia actual coincide 
            if (membresia.getId() == id) {
                //editar la membresia con el id ingresado
                membresia.setNomMembresia(nomMembresia);
                membresia.setDuracion(duracion);
                membresia.setPrecio(precio);

                break;//salir del bucle una vez se haya editado la membresia
            }
        }
        //Serializar la lista de membresias editadas
        serializarListaMembresias(context, listaMembresias);

    }

    /**
     * Metodo para buscar la membresia por el id
     *
     * @param listaMembresias
     * @param id
     * @return resultadoMembresia(retorna la membresia que tenga el mismo id que
     * el ingresado)
     */
    public Membresia buscarMembresiaId(LinkedList<Membresia> listaMembresias, int id) {
        Membresia resultadoMembresia = null;
        if (listaMembresias == null) {
            // System.out.println("No hay membresias con ese id: " + id);
        } else {

            //Iterar sobre la lista de membresias
            Iterator<Membresia> iterator = listaMembresias.iterator();

            while (iterator.hasNext()) {
                //Avanzar dentro del ciclo while
                Membresia membresia = iterator.next();

                if (membresia.getId() == id) {
                    resultadoMembresia = membresia;
                    // System.out.println("EL NOMBRE DE LA MEMBRESIA ASIGNADa ES: " + resultadoMembresia.getNomMembresia());
                    break;
                }
            }
        }
        return resultadoMembresia;
    }

}
