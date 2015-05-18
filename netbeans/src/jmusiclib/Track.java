package jmusiclib;

/**
 *
 * @author Juanjo Salvador
 */
public class Track {
    String author;
    String album;
    String year;
    int track;
    
    Track(String aut, String alb, String yea, int tra) {
        author = aut;
        album = alb;
        year = yea;
        track = tra;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getAlbum() {
        return album;
    }
    
    public String getYear() {
        return year;
    }
    
    public int getTrack() {
        return track;
    }
}
