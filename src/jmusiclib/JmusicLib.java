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
        String sSistemaOperativo = System.getProperty("os.name");
        String so = "" + sSistemaOperativo.charAt(0) + sSistemaOperativo.charAt(1) + sSistemaOperativo.charAt(2);
        if (so.equals("Win")) {
            testing.music_path = "audios";
        }
      testing.ReadTest();
      System.out.println(" ");
      testing.OrganizeTestWin();
    }
    
}
