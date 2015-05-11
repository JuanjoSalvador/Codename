package jmusiclib;

/**
 *
 * @author Juanjo Salvador
 */
public class Playlist implements java.io.Serializable {
    String[] elements;
    
    // Constructor
    Playlist(String[] elements) {
        this.elements = elements;
    }
}
