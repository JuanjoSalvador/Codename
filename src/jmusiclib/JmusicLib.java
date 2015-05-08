/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmusiclib;

import java.io.*;

/**
 *
 * @author juanjo
 */
public class JmusicLib {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
      File mFolder = new File(Library.music_path());
      System.out.println("Directorio por defecto: " + mFolder);
      GUI gui = new GUI();
      gui.main();
//      // TESTING
//      System.out.println("Conectando con la base de datos...");
//       Database.connectDB();
//      System.out.println("Leyendo e insertando datos...");
//       //Library.readDir(mFolder);
//      System.out.println("Organizando ficheros...");
//       //Library.organizeDir(mFolder);
//      System.out.println("Sistema completamente operativo.");
//      // Limpieza
//        Database.cleanDB();
//      // Búsqueda
//      System.out.print("Introduce un parámetro de búsqueda: ");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = br.readLine();
//      Database.searchTrack(input);
//      MusicPlayer.initPlay();
//      // Cierre
//      System.out.println("Finalizado");
//        Database.closeDB();   
    }
    
}
