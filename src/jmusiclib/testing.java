/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmusiclib;

import com.beaglebuddy.mp3.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Juanjo Salvador
 * Método de prueba que lee un directorio y me lista los metadatos de los archivos MP3 contenidos
 */
public class testing {
    public static void test() throws IOException {
        File dir = new File("audios");
        String[] dirArray = dir.list();
        Arrays.sort(dirArray);
        for (int x=0; x<dirArray.length; x++) {
            System.out.println("Archivo: " + dirArray[x]);
            MP3 mp3 = new MP3("audios/" + dirArray[x]);
            System.out.println( "Album..............: " + mp3.getAlbum()         + "\n" +
                                "Artista............: " + mp3.getLeadPerformer() + "\n" +
                                "Titulo.............: " + mp3.getTitle()         + "\n" +
                                "Track #............: " + mp3.getTrack()         + "\n" +
                                "Año................: " + mp3.getYear()          + "\n" );
        }
    }
}
