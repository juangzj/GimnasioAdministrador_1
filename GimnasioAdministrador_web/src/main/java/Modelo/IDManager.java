package Modelo;

/**
 *
 * @author Juan Goyes
 */
public class IDManager {

    private int nextID; // Siguiente ID disponible

    public IDManager() {
        nextID = 1; // Comenzamos en 1
    }

    public int getUniqueID() {
        return nextID++; // Devuelve el siguiente ID y luego lo incrementa
    }

}
