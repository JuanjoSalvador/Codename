package jmusiclib;

/**
 *
 * @author Juanjo Salvador
 */

/**
 * Playlist object constructor.
 * @author juanjo
 */
public class Playlist implements java.io.Serializable {
    String[] elements;
    
    // Constructor
    Playlist(String[] elements) {
        this.elements = elements;
    }
}
