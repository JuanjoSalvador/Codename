/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmusiclib;

import java.io.File;
import java.sql.*;
import java.util.Arrays;

/**
 *
 * @author Juanjo Salvador
 */
public class Database {
    
    static String nombreBD = "jmusiclibDB";
    static String usuario = "root";
    static String password = "ironforge";
    static String url = "jdbc:mysql://localhost/" + nombreBD;
    static Connection con;
    
    // OPEN AND CLOSE DATABASE
    public static void connectDB() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            con = DriverManager.getConnection(url,usuario,password);
            if (con != null) {
                System.out.println("Conexión con la BD " + nombreBD + " realizada con éxito");
            }
        }
        catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Ocurrió un error.");
        }
    }
    
    public static void closeDB() {
        try {
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // INSERT DATA ON DATABASE
    public static void artistInsertDB(String nombreGrupo) {
        try {           
            Statement st = con.createStatement(); 
            String insertGrupo = "INSERT INTO grupo(nombreGrupo) VALUES ('" + nombreGrupo + "')";
            //System.out.println("Voy a ejecutar esta instrucción SQL:\n" + sql);
            int resultado = st.executeUpdate(insertGrupo);
            if (resultado == 1) {
                //System.out.println("Registro insertado con éxito");
            }
            st.close();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public static void albumInsertDB(String tituloDisco, int fechaLanza, String nombreGrupo) {
        try {           
            
            Statement st = con.createStatement(); 
            String insertAlbum = "INSERT INTO disco(tituloDisco, fechaLanza, nombreGrupo) VALUES ('" + tituloDisco + "', '" + fechaLanza + "', '" + nombreGrupo + "')";
            //System.out.println("Voy a ejecutar esta instrucción SQL:\n" + sql);
            int resultado = st.executeUpdate(insertAlbum);
            if (resultado == 1) {
                //System.out.println("Registro insertado con éxito");
            }
            st.close();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public static void trackInsertDB(String tituloCancion, int track, String ruta, String tituloDisco) {
        try {           
            Statement st = con.createStatement(); 
            String insertTrack = "INSERT INTO cancion(tituloCancion, track, ruta, tituloDisco) VALUES ('" + tituloCancion + "', '" + track + "', '" + ruta + "', '" + tituloDisco + "')";
            //System.out.println("Voy a ejecutar esta instrucción SQL:\n" + sql);
            int resultado = st.executeUpdate(insertTrack);
            if (resultado == 1) {
                //System.out.println("Registro insertado con éxito");
            }
            st.close();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    // CLEAN DATABASE
    public static void cleanDB() {
        /* Lee el campo cancion.ruta de la base de datos
           Comprueba que la ruta existe
           Si no existe, borra el registro de la base de datos */
        try {           
            Statement st = con.createStatement(); 
            String read = "SELECT ruta FROM cancion";
            ResultSet readRs = st.executeQuery(read);
                        
            while (readRs.next()) {
                File file = new File(readRs.getString("ruta"));
                if (!file.exists()) {
                    String toDelete = readRs.getString("ruta");
                    System.out.println(toDelete);
                    //st.executeQuery("DELETE FROM cancion WHERE ruta = '" + toDelete + "'");
                }    
            }
            st.close();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        
        // Lee el campo grupo.nombreGrupo de la base de datos
        // Comprueba que el directorio nombreGrupo existe
        // Si no, borra el registro y todos los asociados a el
        
        // Lee el campo disco.tituloDisco y disco.nombreGrupo de la base de datos
        // Comprueba que el directorio nombreGrupo/tituloDisco existe
        // Si no, borra el registro y todos los asociados a el        
    }
    
    // SEARCH ON DATABASE
    public static String searchParam(String input) {
        String output = null;
        
        char[] param = input.toCharArray();
        for (int i = 0; i < param.length; i++) {
            if (param[i] == ' ') {
                param[i] = '%';
            }
        }
        return output = "%" + String.valueOf(param) + "%";
    }
    
    public static void searchAlbum(String sInput) {
        String titulo = searchParam(sInput);
        
        try {           
            Statement st = con.createStatement(); 
            String searchAlbum = "select cancion.track, cancion.tituloCancion, disco.tituloDisco, grupo.nombreGrupo " +
                                 "from grupo, disco, cancion " +
                                 "where grupo.nombreGrupo = disco.nombreGrupo and disco.tituloDisco = cancion.tituloDisco"
                               + " and disco.tituloDisco like '" + titulo + "' " +
                                 "ORDER BY `cancion`.`track`";
            ResultSet rs = st.executeQuery(searchAlbum);
            
            while (rs.next()) {
                System.out.println("Track...: " + rs.getString("cancion.track"));
                System.out.println("Titulo..: " + rs.getString("cancion.tituloCancion"));
                System.out.println("Album...: " + rs.getString("disco.tituloDisco"));
                System.out.println("Autor...: " + rs.getString("grupo.nombreGrupo") + "\n");
            }
            
            st.close();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public static void searchTrack(String sInput) {
        String titulo = searchParam(sInput);
        
        try {           
            Statement st = con.createStatement(); 
            String searchTrack = "SELECT cancion.idCancion, cancion.tituloCancion, cancion.tituloDisco,"
                               + " cancion.ruta FROM cancion WHERE tituloCancion like '" + titulo + "'";
            ResultSet rs = st.executeQuery(searchTrack);
            
            while (rs.next()) {
                System.out.println("ID.......: " + rs.getString("cancion.idCancion"));
                System.out.println("Titulo...: " + rs.getString("cancion.tituloCancion"));
                System.out.println("Disco....: " + rs.getString("cancion.tituloDisco") + "\n");
            }
            
            st.close();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    
    public static String selectTrack(String id) {   
        String ruta = null;
        try {
            Statement st = con.createStatement(); 
            String searchTrack = "SELECT ruta FROM cancion WHERE idCancion = '" + id + "'";
            ResultSet rs = st.executeQuery(searchTrack);
            while (rs.next()) {
                ruta = rs.getString("ruta");
            }
            st.close();
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        return ruta;
    }
    
    public static Object[][] showAll() throws SQLException { 
        String[][] element = null;
        try {
            Statement st = con.createStatement(); 
            String searchTrack = "select cancion.track, cancion.tituloCancion, disco.nombreGrupo, cancion.tituloDisco\n" +
                                 "from cancion, disco\n" +
                                 "where cancion.tituloDisco = disco.tituloDisco";
            
            ResultSet rs = st.executeQuery(searchTrack);
            while(rs.next()) {
                for (int i = 1; rs.next(); i++) {
                    for (int j = 1; rs.next(); j++) {
                        element[i][j] = rs.getString("cancion.track");
                    }
                }
                st.close();
            }
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        return element;
    }
}
