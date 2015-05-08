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
//        private Player player;
//        
//        public long pauseLocation;
//        public long songTotalLength;
//        
//        public String fileLocation;
//        public String status = null;
//        
//        public Thread playerThread;
//        
//        private FileInputStream fis;
//        private BufferedInputStream bis;
//    
//    public String getDuration(String filename) {
//        String duration = null;
//            try {
//                MP3 mp3 = new MP3(filename);
//                int audio = mp3.getAudioDuration();
//                int minutes = audio / 60;
//                int seconds = audio % 60;
//                
//                duration = minutes + ":" + seconds;
//            } catch (IOException ex) {
//                
//            }
//        return duration;
//    }
//        
//    public void stop() {
//        if (player != null) {
//            player.close();
//        }
//    }
//    
//    public void pause() {
//            try {
//                status = "paused";
//                player.close();
//                pauseLocation = fis.available();
//            } catch (IOException ex) {
//                
//            }
//    }
//        
//    public void resume() throws JavaLayerException {
//        try {
//            fis = new FileInputStream(fileLocation);
//            bis = new BufferedInputStream(fis);
//            player = new Player(bis);
//            
//            fis.skip(songTotalLength - pauseLocation);
//            
//        } catch (FileNotFoundException | JavaLayerException e) {
//            System.out.println("Problem resuming file " + fileLocation);
//            System.out.println(e);
//        } catch (IOException ex) {
//                
//        }
//
//        new Thread() {
//            @Override
//            public void run() {
//                try { 
//                    player.play(); 
//                } catch (Exception e) { 
//                    System.out.println(e); 
//                }
//            }
//        }.start();
//    }
//    
//    public void play(String filename) {
//        if (status.equals("paused")) {
//            resume();
//        } else {
//            try {
//                fis = new FileInputStream(filename);
//                bis = new BufferedInputStream(fis);
//                player = new Player(bis);
//                System.out.println("Playing file " + filename);
//                songTotalLength = fis.available();
//
//                fileLocation = filename;
//
//            } catch (FileNotFoundException | JavaLayerException e) {
//
//                System.out.println("Problem playing file " + filename);
//                System.out.println(e);
//
//            } catch (IOException ex) {
//
//            }
//
//            playerThread = new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        player.play();
//                    } catch (Exception e) {
//                        System.out.println(e); 
//                    }
//                }
//            };
//            playerThread.start();
//        }
//    }
    
    /* NEW STUFF (testing( */
    
 
 
  private BasicPlayer player;
  private BasicController control;
  private float bytesLength;
  private String status;
  
  public MusicPlayer() {
    player = new BasicPlayer();
    control = (BasicPlayer) player;
//    player.addBasicPlayerListener(this);
  }
  
  public void loadFile(String filename) throws BasicPlayerException {
    control.open(new File(filename));
  }
  
  public void play() throws Exception {
      try {
        control.play();
      } catch (BasicPlayerException bpe) {
          System.out.println("Error al reproducir");
          bpe.printStackTrace();
      }
  }

  public void pausa() throws Exception {
      try {
        control.pause();
      } catch (BasicPlayerException bpe) {
          System.out.println("Error al pausar");
          bpe.printStackTrace();
      }
  }

  public void continuar() throws Exception {
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
      } catch (BasicPlayerException bpe) {
          System.out.println("Error al reproducir");
          bpe.printStackTrace();
      }
  }
  
  public void SoundVolume(double volume) throws BasicPlayerException {
      control.setGain(volume);
      System.out.println("Ajustando volumen a " + volume);
  }

    @Override
    public void opened(Object o, Map map) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
        int progressNow = (int) (bytesLength * progressUpdate);
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
