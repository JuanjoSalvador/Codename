/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmusiclib;

import java.io.File;
import java.sql.*;

/**
 *
 * @author Juanjo Salvador
 */
public class MusicDB {
    
    String nombreBD = "jmusiclibDB";
    String usuario = "root";
    String password = "ironforge";
    String url = "jdbc:mysql://localhost/" + nombreBD;
    Connection con;
    Statement st;
    
    // OPEN AND CLOSE DATABASE
    /**
     * Open a database connection.
     */
    public void connectDB() {
        
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
    /**
     * Close the database connection (if opened).
     */
    public void closeDB() {
        try {
            con.close();
        }
        catch (Exception e) {
//            e.printStackTrace();
        }
    }
    
    // INSERT DATA ON DATABASE
    /**
     * Insert artist name on database
     * @param nombreGrupo 
     */
    public void artistInsertDB(String nombreGrupo) {
        try {           
            st = con.createStatement(); 
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
    /**
     * Insert album data on database
     * @param tituloDisco
     * @param fechaLanza
     * @param nombreGrupo 
     */
    public void albumInsertDB(String tituloDisco, int fechaLanza, String nombreGrupo) {
        try {           
            st = con.createStatement();
            String insertAlbum = "INSERT INTO disco(tituloDisco, fechaLanza, nombreGrupo) VALUES ('" + tituloDisco + "', '" + fechaLanza + "', '" + nombreGrupo + "')";
            //System.out.println("Voy a ejecutar esta instrucción SQL:\n" + sql);
            int resultado = st.executeUpdate(insertAlbum);
            if (resultado == 1) {
                //System.out.println("Registro insertado con éxito");
            }
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    /**
     * Insert track data on database
     * @param tituloCancion
     * @param track
     * @param ruta
     * @param tituloDisco 
     */
    public void trackInsertDB(String tituloCancion, int track, String ruta, String tituloDisco) {
        try {           
            st = con.createStatement(); 
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
    /**
     * Delete entries on database what do not exists on filesystem
     * @param toDelete 
     */
    public void delete(String toDelete) {
        try {           
            st = con.createStatement();
            String del = ("DELETE FROM cancion WHERE ruta = '" + toDelete + "'");
            st.executeQuery(del);
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Check all entry on database and check if it exists, delete if not.
     */
    public void cleanDB() {
        /* Lee el campo cancion.ruta de la base de datos
           Comprueba que la ruta existe
           Si no existe, borra el registro de la base de datos */
        try {           
            st = con.createStatement();
            String read = "SELECT ruta FROM cancion";
            ResultSet readRs = st.executeQuery(read);
                        
            while (readRs.next()) {
                File file = new File(readRs.getString("ruta"));
                String toDelete = readRs.getString("ruta");
                if (!file.exists()) { 
                    System.out.println(toDelete);
                    delete(toDelete);
                } 
            }
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Lee el campo grupo.nombreGrupo de la base de datos
        // Comprueba que el directorio nombreGrupo existe
        // Si no, borra el registro y todos los asociados a el
        
        // Lee el campo disco.tituloDisco y disco.nombreGrupo de la base de datos
        // Comprueba que el directorio nombreGrupo/tituloDisco existe
        // Si no, borra el registro y todos los asociados a el        
    }
    
    // SEARCH ON DATABASE
    
    /**
     * Convert a word into a search parameter for SQL
     * @param input
     * @return all entries with the parameter in it
     */
    public String searchParam(String input) {
        String output = null;
        
        char[] param = input.toCharArray();
        for (int i = 0; i < param.length; i++) {
            if (param[i] == ' ') {
                param[i] = '%';
            }
        }
        return output = "%" + String.valueOf(param) + "%";
    }
    /**
     * Gets a parameter provided by searchParam() and uses it for search an album into database 
     * @param sInput 
     */
    public void searchAlbum(String sInput) {
        String titulo = searchParam(sInput);
        
        try {           
            st = con.createStatement(); 
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
    /**
     * Gets a parameter provided by searchParam() and uses it for search an track into database 
     * @param sInput 
     */
    public void searchTrack(String sInput) {
        String titulo = searchParam(sInput);
        
        try {           
            st = con.createStatement(); 
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
    /**
     * Select a track by ID in database
     * @param id
     * @return track path
     */
    public String selectTrack(String id) {   
        String ruta = null;
        try {
            st = con.createStatement(); 
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
    /**
     * Show all entries in database
     * @return all entries in database
     * @throws SQLException 
     */
    public Object[][] showAll() throws SQLException { 
        String[][] element = new String[15][4];
        try {
            st = con.createStatement(); 
            String searchTrack = "select cancion.track, cancion.tituloCancion, disco.nombreGrupo, cancion.tituloDisco\n" +
                                 "from cancion, disco\n" +
                                 "where cancion.tituloDisco = disco.tituloDisco";
            
            ResultSet rs = st.executeQuery(searchTrack);
            while(rs.next()) {
                for (int i = 0; rs.next(); i++) {
                        element[i][0] = rs.getString("cancion.track");
                        element[i][1] = rs.getString("cancion.tituloCancion");
                        element[i][2] = rs.getString("disco.nombreGrupo");
                        element[i][3] = rs.getString("cancion.tituloDisco");
                }
            }
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }
}
