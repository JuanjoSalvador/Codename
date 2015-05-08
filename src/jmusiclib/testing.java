/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmusiclib;

import com.beaglebuddy.mp3.*;
import java.io.*;
import java.util.Arrays;

/**
 * @author Juanjo Salvador
 * Métodos de prueba para desglosar e implementar en clases.
 */
public class testing { 
    public static void readDir(File dir) throws IOException {      
        File[] dirArray = dir.listFiles();
        Arrays.sort(dirArray); // Array de File ordenado con el contenido del directorio de música
        for (int i = 0; i < dirArray.length; i++) {
            String name = dirArray[i].getName();
            
            if (dirArray[i].isDirectory()) {
                File subdir = dirArray[i];
                //System.out.println(dirArray[i] + " es un directorio");
                readDir(subdir);
            }
            if (Library.extension(name).equals("mp3")) {
                Library.ReadMp3(dirArray[i]);
                //System.out.println(dirArray[i] + " es un MP3");
            }
        }
    }
    
    public static void organizeDir(File dir) throws IOException {
        File[] dirArray = dir.listFiles();
        Arrays.sort(dirArray); // Array de File ordenado con el contenido del directorio de música
        for (int i = 0; i < dirArray.length; i++) {
            String name = dirArray[i].getName();
            
            if (dirArray[i].isDirectory()) {
                File subdir = dirArray[i];
                organizeDir(subdir);
            }
            
            if (Library.extension(name).equals("mp3")) {
                if (Library.extension(name).equals("lac") || Library.extension(name).equals("ogg")) {
                Library.noData(dirArray[i]);
            } else {
                if (Library.extension(name).equals("mp3")) {
                    if (getMp3Artist(dirArray[i]) == null && getMp3Album(dirArray[i]) == null) {
                        Library.noData(dirArray[i]);
                    } else {
                        createMp3Folder(dirArray[i]);
                        moveMp3(dirArray[i]);
                    }
                }
            }
                
            } 
        }
    }
    
    public static void createMp3Folder(File mp3) throws IOException {
        String fArtist = getMp3Artist(mp3);
        String fAlbum  = getMp3Album(mp3);
        // Crea la carpeta Artista/Album
        File folder = new File(fArtist + "/" + fAlbum);
        if (!folder.exists()) { // Si el directorio "autores" NO existe, lo crea.
            File directorio = new File(Library.music_path() + "/" + fArtist + "/" + fAlbum);
            directorio.mkdirs();
        }
    }
    
    public static void moveMp3(File mp3) throws FileNotFoundException, IOException {
        String name = mp3.getName();
        String fArtist = getMp3Artist(mp3);
        String fAlbum  = getMp3Album(mp3);
        File destino = new File(Library.music_path() + "/" + fArtist + "/" + fAlbum + "/" + name);
        File noCat = new File(Library.music_path() + "/Sin categoría/" + name);
        
        if (!destino.exists()) {
            //if (!noCat.exists()) {
                InputStream in = new FileInputStream(mp3);
                OutputStream out = new FileOutputStream(destino);

                byte[] buf = new byte[16384];
                int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                in.close();
                out.close();
                mp3.delete(); 
            //}
        }
    }
    
    public static String getMp3Artist(File mp3) {
        String mp3Author;
        try {
            MP3 mp3File = new MP3(mp3);
            mp3Author = mp3File.getLeadPerformer();
        } catch (IOException ioex) {
            mp3Author = null;
        }
        return mp3Author;
    }
    
    public static String getMp3Album(File mp3) {
        String mp3Album;
        try {
            MP3 mp3File = new MP3(mp3);
            mp3Album = mp3File.getAlbum();
        } catch (IOException ioex) {
            mp3Album = null;
        }
        return mp3Album;
    }
}

