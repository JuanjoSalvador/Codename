package jmusiclib;

import com.beaglebuddy.mp3.MP3;
import java.io.*;
import java.util.Arrays;

/**
 * Organiza la música según su autor, album y track en directorios, extrae sus metadatos y los exporta a una base de datos.
 * @author Juanjo Salvador
 */
public class Library {
    /* NEW STUFF 
        Needs doc (txt available now)
    */
    MusicDB db = new MusicDB();
    /**
     * Devuelve la ruta por defecto para la música basándose en el SO (C:\%Username%\Música para Windows y /home/user/Música para Linux)
     * @return 
     */
    public String music_path() {
    // Comprueba el SO para establecer la ruta de música por defecto
        String path;
        String SisOp = System.getProperty("os.name");
        String so = ""  + SisOp.charAt(0) + SisOp.charAt(1) + SisOp.charAt(2);
        String user = System.getProperty("user.name");
        if (so.equals("Win")) {
           // Windows 
           path = "C:/Users/" + user + "/Music/";
        } else {
           // GNU/Linux
           path = "/home/" + user + "/Música";
        }
        return path;
    }
    
    /**
     * Método que comprueba si un archivo es MP3
     * @param file
     * @return extensión del fichero
     */
    public String extension(String file) {
        int last_char = file.length();
        String exten = ""  + file.charAt(last_char - 3) + file.charAt(last_char - 2) + file.charAt(last_char - 1);
        return exten;
    }
    
    /**
     * En caso de que no pueda obtener los datos de un MP3 (o este no sea un MP3), mueve el archivo
     * a una carpeta aparte (Sin categoría).
     * @param noData 
     */
    public void noData (File noData) {
        String name = noData.getName();
        File milascea = new File(music_path() + "/Sin categoría/");
        File noCat = new File(music_path() + "/Sin categoría/" + name);
        milascea.mkdirs();
        try {
            if (!noCat.exists()) {
                InputStream in = new FileInputStream(noData);
                OutputStream out = new FileOutputStream(music_path() + "/Sin categoría/" + name);

                byte[] buf = new byte[16384];
                int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                in.close();
                out.close();
                noData.delete();
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
        }
    }
    //readDir & organizeDir are main methods here. They call all the others methods.
    
    public void readDir(File dir) throws IOException {      
        File[] dirArray = dir.listFiles();
        Arrays.sort(dirArray); // Array de File ordenado con el contenido del directorio de música
        for (int i = 0; i < dirArray.length; i++) {
            String name = dirArray[i].getName();

            if (dirArray[i].isDirectory()) {
                File subdir = dirArray[i];
                readDir(subdir);
            }
            if (extension(name).equals("mp3")) {
                ReadMp3(dirArray[i]);
            }
        }
    }
    
    public void organizeDir(File dir) throws IOException {
        File[] dirArray = dir.listFiles();
        Arrays.sort(dirArray); // Array de File ordenado con el contenido del directorio de música
        for (int i = 0; i < dirArray.length; i++) {
            String name = dirArray[i].getName();
            
            if (dirArray[i].isDirectory()) {
                File subdir = dirArray[i];
                organizeDir(subdir);
            } else {
                if (!extension(name).equals("mp3")) {
                    noData(dirArray[i]); 
                } else {
                    if (getMp3Artist(dirArray[i]) == null && getMp3Album(dirArray[i]) == null) {
                        noData(dirArray[i]);
                    } else {
                        createMp3Folder(dirArray[i]);
                        moveMp3(dirArray[i]);
                    }
                }   
            } 
        }
    }
    
    public void createMp3Folder(File mp3) throws IOException {
        String fPath   = music_path();
        String fArtist = getMp3Artist(mp3);
        String fAlbum  = getMp3Album(mp3);
        // Crea la carpeta Artista/Album
        File folder = new File(fPath + "/" + fArtist + "/" + fAlbum);
        if (!folder.exists()) { // Si el directorio "autores" NO existe, lo crea.
            //File directorio = new File(fPath + "/" + fArtist + "/" + fAlbum);
            folder.mkdirs();
        }
    }
    
    public void moveMp3(File mp3) throws FileNotFoundException, IOException {
        String name = mp3.getName();
        String fArtist = getMp3Artist(mp3);
        String fAlbum  = getMp3Album(mp3);
        File destino = new File(music_path() + "/" + fArtist + "/" + fAlbum + "/" + name);
        
        if (!destino.exists()) {
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
        }
    }
    
    public String getMp3Artist(File mp3) {
        String mp3Author;
        try {
            MP3 mp3File = new MP3(mp3);
            mp3Author = mp3File.getLeadPerformer();
        } catch (IOException ioex) {
            mp3Author = null;
        }
        return mp3Author;
    }
    
    public String getMp3Album(File mp3) {
        String mp3Album;
        try {
            MP3 mp3File = new MP3(mp3);
            mp3Album = mp3File.getAlbum();
        } catch (IOException ioex) {
            mp3Album = null;
        }
        return mp3Album;
    }
    
    public void ReadMp3(File dir) throws IOException {
        String name = dir.getName();
        //System.out.println(name);
        try {
            MP3 mp3 = new MP3(dir);
                // Strings
                String fileAlbum    = mp3.getAlbum();
                String fileArtist   = mp3.getLeadPerformer();
                String fileTitle    = mp3.getTitle();
                String filePath     = mp3.getPath();
                // Ints
                int fileTrack = mp3.getTrack();
                int fileYear  = mp3.getYear();
                
                if (fileAlbum == null || fileArtist == null) {
                    fileArtist = "Sin categoría";
                    fileAlbum  = "Sin categoría";
                } else {
                    db.artistInsertDB(fileArtist);
                    db.albumInsertDB(fileAlbum, fileYear, fileArtist);
                    db.trackInsertDB(fileTitle, fileTrack, filePath, fileAlbum);
                    //System.out.println("[" + dir + "] añadido correctamente");
                }   
            
        } catch (IOException ioex) {
            System.out.println("No se pueden leer los metadatos de [" + dir + "].");
        }
    }
}

        
