package Codename;

import com.beaglebuddy.mp3.MP3;
import java.io.*;
import java.util.Arrays;

/**
 * Organize the music by author, album and track, read music's metadata and insert it into a database.
 * External libraries used here: Beablebuddy.
 * @author Juanjo Salvador
 */
public class Library {
    /* NEW STUFF 
        Needs doc (txt available now)
    */
    MusicDB db = new MusicDB();
    /**
     * Returns the default music folder path (C:\%Username%\Música for Windows y /home/user/Música for Linux)
     * @return path
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
     * Returns the extension of a file (3 last chars)
     * @param file
     * @return extensión del fichero
     */
    public String extension(String file) {
        int last_char = file.length();
        String exten = ""  + file.charAt(last_char - 3) + file.charAt(last_char - 2) + file.charAt(last_char - 1);
        return exten;
    }
    
    /**
     * Moves a file into a default folder if there is no metadata or isn't a Mp3
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
    /**
     * This is the first main method. 
     * Open the param directory and read it, looking for MP3 files. 
     * If there is a subdirectory inside, recursively read it.
     * If a MP3 is found, calls ReadMp3().
     * @param dir
     * @throws IOException 
     */
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
    /** 
     * This is the second main method.
     * Open the param directory and read it looking for MP3 files.
     * If there is a subdirectory inside, recursively read it.
     * If there is a MP3 file inside, read the metadata. If null, calls noData().
     * If not null, calls createMp3Folder() and moveMp3().
     * If there is a non-MP3 file inside, calls noData().
     * @param dir
     * @throws IOException 
     */
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
    /** 
     * Reads MP3's artist and album, and creates
     * a path following the model /artist/album/.
     * 
     * ONLY IF FOLDER DOESN'T EXISTS.
     * 
     * @param mp3
     * @throws IOException 
     */
    public void createMp3Folder(File mp3) throws IOException {
        String fPath   = music_path();
        String fArtist = getMp3Artist(mp3);
        String fAlbum  = getMp3Album(mp3);
        // Crea la carpeta Artista/Album
        File folder = new File(fPath + "/" + fArtist + "/" + fAlbum);
        if (!folder.exists()) { // Si el directorio "autores" NO existe, lo crea.
            folder.mkdirs();
        }
    }
    /**
     * Read mp3 artist and album and moves it to the path
     * music_folder/artist/album.
     * @param mp3
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
    /**
     * Return the MP3 artist data using Beaglebuddy
     * @param mp3
     * @return mp3Author
     */
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
    /**
     * Returns the MP3 album data using Beaglebuddy
     * @param mp3
     * @return mp3Album
     */
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
    /**
     * Read MP3 metadata and insert it into the database
     * @param dir
     * @throws IOException 
     */
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

        
