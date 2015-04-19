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
 * @author Juanjo Salvador
 * Métodos de prueba para desglosar e implementar en clases.
 */
public class testing {
    /**
     * Organiza los MP3 contenidos en "audios" en carpetas según su autor y album
     * @throws IOException 
     */
    static String music_path;
    
    /**
     * Método que comprueba si un archivo es MP3
     * @param file
     * @return extensión del fichero
     */
    public static String isMp3(String file) {
        int last_char = file.length();
        String exten = ""  + file.charAt(last_char - 3) + file.charAt(last_char - 2) + file.charAt(last_char - 1);
        return exten;
    }
    
    /**
     * En caso de que no pueda obtener los datos de un MP3 (o este no sea un MP3), mueve el archivo
     * a una carpeta aparte (Sin categoría).
     * @param noData 
     */
    public static void noData (File noData) {
        File milascea = new File(music_path + "/Sin categoría/");
        milascea.mkdirs();
        String name = noData.getName();
        try {
            InputStream in = new FileInputStream(noData);
            OutputStream out = new FileOutputStream(music_path + "/Sin categoría/" + name);

            byte[] buf = new byte[4096];
            int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            in.close();
            out.close();
            noData.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Organiza los archivos MP3 contenidos en music_path en carpetas siguiendo la premisa de autor/album/track.mp3
     * @throws IOException 
     */
    public static void OrganizeTestWin() throws IOException {
        System.out.println("ORGANIZAR");
        File dir = new File(music_path); // Abre el directorio "audios"
        File[] dirArray = dir.listFiles(); // Lista el directorio "audios" en un array
        Arrays.sort(dirArray); // Ordena el array
        for (int i = 0; i < dirArray.length; i++) {
            // Recorre el array de ficheros
            String name = dirArray[i].getName();
            File noData = dirArray[i];
            try {
                if (!dirArray[i].isDirectory() && isMp3(name).equals("mp3")) { // Comprueba si el elemento ES UN MP3
                    MP3 music = new MP3(dirArray[i]); // Crea un objeto MP3
                    String dirAutor = music.getLeadPerformer(); // Nombre del autor
                    String dirAlbum = music.getAlbum(); // Nombre del album
                                            
                    File folder = new File(dirAutor); // Crea un objeto File con el valor de "autores"
                    if (!folder.exists()) { // Si el directorio "autores" NO existe, lo crea.
                        File directorio = new File(music_path + "/" + dirAutor + "/" + dirAlbum);
                        directorio.mkdirs();
                    }
                    // Mueve los archivos MP3 en la carpeta de su autor
                    InputStream in = new FileInputStream(dirArray[i]);
                    OutputStream out = new FileOutputStream(music_path + "/" + dirAutor + "/" + dirAlbum + "/" + name);

                    byte[] buf = new byte[8192];
                    int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                    in.close();
                    out.close();
                    dirArray[i].delete();
                    } else { 
                    // Si no es un MP3 ni un directorio lo manda a audios/Sin categoria
                        if (isMp3(name).equals("lac") || isMp3(name).equals("ogg") && !dirArray[i].isDirectory()) {
                            System.out.println("El fichero [" + dirArray[i] + "] no es un MP3");
                            noData(dirArray[i]);
                        }
                    }        
            } catch (IOException ioe) {
                //System.out.println("No se pueden leer los metadatos de [" + dirArray[i] + "], omitiendo fichero.");
                noData(dirArray[i]);
            }
        }
        System.out.println("Operación finalizada.");
    }
    
    /**
     * Lee los metadatos contenidos en los MP3 y los muestra.
     * @throws IOException 
     */
    public static void ReadTest() throws IOException {
        System.out.println("LECTURA");
        File dir = new File(music_path);
        File[] dirArray = dir.listFiles();
        Arrays.sort(dirArray);
        for (int x = 0; x < dirArray.length; x++) {
            String name = dirArray[x].getName();
            try {
                if (!dirArray[x].isDirectory() && isMp3(name).equals("mp3")) {
                    System.out.println("Archivo: " + dirArray[x]);
                    MP3 mp3 = new MP3(dirArray[x]);
                    System.out.println( "Album..............: " + mp3.getAlbum()         + "\n" +
                                        "Artista............: " + mp3.getLeadPerformer() + "\n" +
                                        "Titulo.............: " + mp3.getTitle()         + "\n" +
                                        "Track #............: " + mp3.getTrack()         + "\n" +
                                        "Año................: " + mp3.getYear()          + "\n" );
                }
            } catch (IOException ex) {
                System.out.println("No se pueden leer los metadatos de [" + dirArray[x] + "]. \n");
            }
        }
    }
}


