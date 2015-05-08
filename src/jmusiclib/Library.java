package jmusiclib;

import com.beaglebuddy.id3.enums.PictureType;
import com.beaglebuddy.id3.pojo.AttachedPicture;
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
    
    /**
     * Devuelve la ruta por defecto para la música basándose en el SO (C:\%Username%\Música para Windows y /home/user/Música para Linux)
     * @return 
     */
    public static String music_path() {
    // Comprueba el SO para establecer la ruta de música por defecto
        String path;
        String SisOp = System.getProperty("os.name");
        int last_char = SisOp.length();
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
    public static String extension(String file) {
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
    
    public static void readDir(File dir) throws IOException {      
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
    
    public static void organizeDir(File dir) throws IOException {
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
    
    public static void createMp3Folder(File mp3) throws IOException {
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
    
    public static void moveMp3(File mp3) throws FileNotFoundException, IOException {
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
    
    /**
     * ReadLibrary(). Lee recursivamente el fichero de música obtenido mediante music_path() y muestra 
     * los metadatos contenidos en los MP3 de dichos directorios y subdirectorios.
     * @throws IOException 
     */
    public static void ReadMp3(File dir) throws IOException {
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
                    Database.artistInsertDB(fileArtist);
                    Database.albumInsertDB(fileAlbum, fileYear, fileArtist);
                    Database.trackInsertDB(fileTitle, fileTrack, filePath, fileAlbum);
                }   
            
        } catch (IOException ioex) {
            //System.out.println("No se pueden leer los metadatos de [" + dir + "].");
        }
    }
    
    /* OLD STUFF
        Delete? Uhm.
    */
    public static void OrganizeLibrary() throws IOException {
//        System.out.println("ORGANIZAR");
//             
//        File dir = new File(music_path()); // Abre el directorio "audios"
//        File[] dirArray = dir.listFiles(); // Lista el directorio "audios" en un array
//        Arrays.sort(dirArray); // Ordena el array
//        for (int i = 0; i < dirArray.length; i++) {
//            // Recorre el array de ficheros
//            String name = dirArray[i].getName();
//            File noData = dirArray[i];
//            try {
//                
//                // MP3
//                if (!dirArray[i].isDirectory() && extension(name).equals("mp3")) { // Comprueba si el elemento ES UN MP3
//                    MP3 music = new MP3(dirArray[i]); // Crea un objeto MP3
//                    String dirAutor = music.getLeadPerformer(); // Nombre del autor
//                    String dirAlbum = music.getAlbum(); // Nombre del album
//                                            
//                    File folder = new File(dirAutor); // Crea un objeto File con el valor de "autores"
//                    if (!folder.exists()) { // Si el directorio "autores" NO existe, lo crea.
//                        File directorio = new File(music_path() + "/" + dirAutor + "/" + dirAlbum);
//                        directorio.mkdirs();
//                    }
//                    
//                    // Mueve los archivos MP3 en la carpeta de su autor
//                    InputStream in = new FileInputStream(dirArray[i]);
//                    OutputStream out = new FileOutputStream(music_path() + "/" + dirAutor + "/" + dirAlbum + "/" + name);
//
//                    byte[] buf = new byte[8192];
//                    int len;
//                        while ((len = in.read(buf)) > 0) {
//                            out.write(buf, 0, len);
//                        }
//                    in.close();
//                    out.close();
//                    dirArray[i].delete();
//                } else { 
//                    // Si no es un MP3 ni un directorio lo manda a audios/Sin categoria
//                        if (extension(name).equals("lac") || extension(name).equals("ogg") && !dirArray[i].isDirectory()) {
//                            System.out.println("El fichero [" + dirArray[i] + "] no es un MP3");
//                            noData(dirArray[i]);
//                        }
//                }        
//            } catch (Exception ex) {
//                //System.out.println("No se pueden leer los metadatos de [" + dirArray[i] + "], omitiendo fichero.");
//                noData(dirArray[i]);
//            }
//        } 
//        System.out.println("Operación finalizada.");
    }
}

        