/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codename;

import java.io.*;
import java.sql.SQLException;

/**
 * Main method
 * @author juanjo
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, SQLException {
      Library lib = new Library();  
      File mFolder = new File(lib.music_path());
      System.out.println("Directorio por defecto: " + mFolder);
      GUI gui = new GUI();
      gui.main();
    }
    
}
