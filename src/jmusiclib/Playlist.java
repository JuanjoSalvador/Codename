/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmusiclib;

import java.io.File;

/**
 *
 * @author Juanjo Salvador
 */
public class Playlist {
    public static void playlist(String[] mp3, String listName) {
        String[] playlist = null;        
        for (int i = 0; i > mp3.length; i++) {
            playlist[i] = mp3[i];
        }
        File list = new File("playlists/" + listName + ".pzs");
    }
}
