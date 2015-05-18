/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jmusiclib;


import java.io.*;
import java.util.Map;
import javazoom.jlgui.basicplayer.*;

/**
 *
 * @author Juanjo Salvador
 */
public class MusicPlayer implements BasicPlayerListener {

    /* NEW STUFF (testing, need to be updated)
       WORKS: Play button
              Stop button
    */ 
 
 
  private BasicPlayer player;
  private BasicController control;
  public float bytesLength;
  public static String status;
  private double volume;
  
  public MusicPlayer() {
    player = new BasicPlayer();
    control = (BasicPlayer) player;
//    player.addBasicPlayerListener(this);
  }
  
  public void loadFile(File audio) throws BasicPlayerException {      
      control.open(audio);
  }
  
  public void play() throws Exception {
        try {
            control.play();
            SoundVolume(volume);
            status = "PLAYING";
        } catch (BasicPlayerException bpe) {
            //System.out.println("Error al reproducir");
            System.out.println(bpe);
        }
  }

  public void pause() throws Exception {
      try {
        control.pause();
        status = "PAUSED";
      } catch (BasicPlayerException bpe) {
          bpe.printStackTrace();
      }
  }

  public void resume() throws Exception {
      try {
        control.resume();
      } catch (BasicPlayerException bpe) {
          System.out.println("Error al reproducir");
          bpe.printStackTrace();
      }
  }

  public void stop() throws Exception {
      try {
        control.stop();
        status = "STOPPED";
      } catch (BasicPlayerException bpe) {
          System.out.println("Error al reproducir");
          bpe.printStackTrace();
      }
  }
  
  public void SoundVolume(double volume) throws BasicPlayerException {
      control.setGain(volume);
  }
  
  public void SavePlaylist(String[] songs, String PlName) throws IOException {
      // Get all elements on JList and put it in the Array "songs"
      Playlist pl = new Playlist(songs);
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PlName + ".pzs"));
      oos.writeObject(pl);
  } 

    @Override
    public void opened(Object o, Map map) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
        int progressNow = (int) (bytesLength * progressUpdate);
        System.out.println("progress : "+properties.toString());
    }

    @Override
    public void stateUpdated(BasicPlayerEvent bpe) {
        System.out.println(bpe);
    }

    @Override
    public void setController(BasicController control) {
        System.out.println("controller" + control);
    }
}
