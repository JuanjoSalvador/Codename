/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmusiclib;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * Creates the Swing GUI
 * @author Juanjo
 */
public class GUI implements ActionListener, ChangeListener {
    private JFrame main_window, update_window;
    private JProgressBar updateProgress;
    private JButton play, pause, stop, next, last, openPlaylist, savePlaylist;
    private JLabel volumeLevel, musicPosition, nplaying;
    private JTextField search_input;
    private JSlider volume, posBar;
    private JPanel library, mPlayer, mPlaylist;
    private JMenuBar menu;
    private JMenu menu_player, about;
    private JMenuItem updateList, openAudio, aboutProject, license, shoutcast;
    private ImageIcon icon;
    
    private final Border blackline = BorderFactory.createLineBorder(Color.gray);
    
    MusicPlayer player = new MusicPlayer();
    MusicDB db = new MusicDB();
    Library lib = new Library();
    Object[][] data;
    
    public void main() throws SQLException {
        main_window = new JFrame("Codename Alpha Dev 0.5");
        Color bg = new Color(238, 238, 238);
        
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        
        /* MENU BAR */
        
        menu = new JMenuBar();
        menu_player = new JMenu("Player");
        about = new JMenu("About");
        // Player
        updateList = new JMenuItem("Update library");
        updateList.addActionListener(this);
        updateList.setName("update");
        openAudio = new JMenuItem("Open audio file...");
        openAudio.addActionListener(this);
        openAudio.setName("openAudio");
        shoutcast = new JMenuItem("Open audio streaming...");
        shoutcast.addActionListener(this);
        shoutcast.setName("shoutcast");
        // About
        aboutProject = new JMenuItem("About the project");
        aboutProject.addActionListener(this);
        aboutProject.setName("aboutProject");
        license = new JMenuItem("License");
        license.addActionListener(this);
        license.setName("license");
        
        menu.add(menu_player);
        menu.add(about);
        menu_player.add(openAudio);
        menu_player.add(updateList);
        menu_player.add(shoutcast);
        about.add(aboutProject);
        about.add(license);
        
        /* AUDIO CONTROLS*/
        
        mPlayer = new JPanel(new FlowLayout());
        Dimension audioControl = new Dimension();
        audioControl.width = 810;
        audioControl.height = 90;
        mPlayer.setBackground(bg);
        
        mPlayer.setPreferredSize(audioControl);
        
            //Play
        play = new JButton(new ImageIcon("img/play-button.png"));
        play.setName("play");
        play.addActionListener(this);
        
            //Pause
        pause = new JButton(new ImageIcon("img/pause-button.png"));
        pause.addActionListener(this);
        pause.setName("pause");
        
            //Stop
        stop = new JButton(new ImageIcon("img/stop-button.png"));
        stop.addActionListener(this);
        stop.setName("stop");
        
            //Next song
        next = new JButton(new ImageIcon("img/next-button.png"));
        next.addActionListener(this);
        next.setName("next");
        
            //Last song
        last = new JButton(new ImageIcon("img/last-button.png"));
        last.addActionListener(this);
        last.setName("last");
      
            //Set volumen
        int VOL_MIN  = 0;
        int VOL_MAX  = 100;
        int VOL_INIT = 60;
        Dimension volDim = new Dimension();
        volDim.width = 100;
        volDim.height = 17;
        volume = new JSlider(JSlider.HORIZONTAL, VOL_MIN, VOL_MAX, VOL_INIT);
        volume.setPreferredSize(volDim);
        volume.addChangeListener(this);
        volumeLevel = new JLabel(String.valueOf(volume.getValue()));
        
            //Now Playing
        nplaying = new JLabel();
        
            //Music position
        Dimension posBarDim = new Dimension();
        posBarDim.width = 800;
        posBarDim.height = 17;
        int POS_MIN = 0;
        float POS_MAX = player.bytesLength;
        int POS_INIT = 0;
        posBar = new JSlider(JSlider.HORIZONTAL, POS_MIN, (int) POS_MAX, POS_INIT);
        posBar.setPreferredSize(posBarDim);
        
        
        mPlayer.add(nplaying);
        mPlayer.add(posBar);
                
        mPlayer.add(last);
        mPlayer.add(play);
        mPlayer.add(pause);
        mPlayer.add(stop);
        mPlayer.add(next);
        mPlayer.add(volume);
        mPlayer.add(volumeLevel);
        
            // END OF AUDIO CONTROLS
        
        /* LIBRARY */
        
        library = new JPanel();
        Dimension libraryDim = new Dimension();
        libraryDim.width = 695;
        libraryDim.height = 490;
        library.setPreferredSize(libraryDim);
                
            // Data
        String[] columnNames = {"#", "Titulo", "Artista", "Disco"};
        db.connectDB();
        data = db.showAll();
        db.closeDB();
        
            // Table
        JTable mTable = new JTable(data, columnNames);
        
        
            // Dimensions
        Dimension tableD = mTable.getPreferredSize();
        tableD.width = 665;
        tableD.height = 394;
        mTable.setPreferredSize(tableD);
        
        mTable.setPreferredScrollableViewportSize(tableD);
        mTable.setFillsViewportHeight(true);
        mTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        TableColumnModel columnModel = mTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(220);
        columnModel.getColumn(2).setPreferredWidth(220);
        columnModel.getColumn(3).setPreferredWidth(220);
        
        //mTable.add(header);
        
        search_input = new JTextField(60);
        
        library.add(search_input);
        library.add(mTable);
        library.add(new JScrollPane(mTable));
        library.setBackground(bg);
        
        
            // END OF LIBRARY
        
        /* PLAYLIST */
        
            // pl (arraylist) contains all elements of each playlist
        ArrayList pl = new ArrayList();
        mPlaylist = new JPanel(new FlowLayout());
        
        JList list = new JList(pl.toArray());
        list.setBorder(blackline);
        
        Dimension playlistPanel = new Dimension();
        playlistPanel.width = 220;
        playlistPanel.height = 490;
        
        Dimension playlistD = list.getPreferredSize();
        playlistD.width = 210;
        playlistD.height = 450;
        
        mPlaylist.setPreferredSize(playlistPanel);
        
        savePlaylist = new JButton("Save");
        savePlaylist.addActionListener(this);
        //savePlaylist.setName("save");
        openPlaylist = new JButton("Open");
        openPlaylist.addActionListener(this);
        //openPlaylist.setName("open");
        
        list.setPreferredSize(playlistD);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        
        mPlaylist.add(list);
        mPlaylist.add(savePlaylist);
        mPlaylist.add(openPlaylist);
        mPlaylist.setBackground(bg);
            //END OF PLAYLIST
        
        /* SEARCH FIELD */
        
        //path = new JLabel("Ruta: ");
        
        //main_window.add(path);
        icon = new ImageIcon("img/Logo.png");
        main_window.setIconImage(icon.getImage());
        main_window.setJMenuBar(menu);
        main_window.add(mPlaylist);
        main_window.add(library);
        main_window.add(mPlayer);
        
        main_window.setLayout(new FlowLayout());
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cerrar proceso al cerrar ventana
        main_window.setSize(1000, 650); // Tamaño
        main_window.setVisible(true);  // Visible
    }
    
    public void updateWin() {
        update_window = new JFrame("Updating library...");
        JPanel uPanel = new JPanel(new FlowLayout());
        
        updateProgress = new JProgressBar();
        updateProgress.setValue(0);
        
        uPanel.add(updateProgress);
        update_window.setSize(300, 70);
        update_window.add(uPanel);
        update_window.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == updateList) {
            updateWin();
        //Creamos un Thread para mejorar el ejemplo
        final Thread t;
        //Inicializamos
        t = new Thread(new Runnable() {
            //Implementamos el método run()
            @Override
            public void run() {
                //Permite mostrar el valor del progreso
                updateProgress.setStringPainted(true);
                int x = 1;
                //Utilizamos un while para emular el valor mínimo y máximo
                //En este caso 0 - 100
                    //Asignamos valor a nuestro JProgressBar por cada siclo del bucle
                    File mFolder = new File(lib.music_path());
                    System.out.println("Conectando con la base de datos...");
                    db.connectDB();
                    updateProgress.setValue(20);
                    try {
                        lib.readDir(mFolder);
                        updateProgress.setValue(45);
                        lib.organizeDir(mFolder);
                        updateProgress.setValue(65);
                        lib.ReadMp3(mFolder);
                        updateProgress.setValue(80);
                    } catch (IOException ex) {
                        System.out.println("Error de lectura");
                    }    
                    db.cleanDB();
                    db.closeDB();
                    updateProgress.setValue(100);
                    update_window.setVisible(false);
            }
        });
        //Se ejecuta el Thread
        t.start();
        }
        
        if (e.getSource() == play) {
            if (!MusicPlayer.status.equals("PAUSED")) {
                File mp3 = new File(search_input.getText());
                try {
                    player.loadFile(mp3);
                    nplaying.setText("Now playing:" + mp3.getName());
                    player.play();    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    player.resume();
                } catch (Exception ex) {
                    
                }
            }
        }
        
        if (e.getSource() == pause) {
            try {
                player.pause();
            } catch (Exception ex) {
               
            }
        }
        
        if (e.getSource() == stop) {
            try {
                player.pause();
                player.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        if (e.getSource() == next) {
            JOptionPane.showMessageDialog(null, "Not supported on this version yet.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (e.getSource() == last) {
            JOptionPane.showMessageDialog(null, "Not supported on this version yet.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (e.getSource() == savePlaylist) {
           JOptionPane.showMessageDialog(null, "Not supported on this version yet.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (e.getSource() == openPlaylist) {
           JOptionPane.showMessageDialog(null, "Not supported on this version yet.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (e.getSource() == openAudio) {
            JFileChooser fileChooser = new JFileChooser();
              
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(openAudio);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
              try {
                  player.loadFile(selectedFile);
                  nplaying.setText("Now playing: " + selectedFile.getName());
                  player.play();                     
              } catch (Exception ex) { }
            }
        }
        
        if (e.getSource() == shoutcast) {
            String url = JOptionPane.showInputDialog("Input an audio streaming URL...");
            if (url != null) {
                try {
                    player.loadURL(url);
                    String title = null;
                        if (url.equals("https://listen.radionomy.com/1-otaku-music-radio")) {
                            title = "Otaku Music Radio";
                        }
                    nplaying.setText(title);
                    player.play();    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        
        if (e.getSource() == aboutProject) {
            JOptionPane.showMessageDialog(null, "<html><b>Codename</b> (JmusicLib) is developed by Juan José Salvador.</html> \n"
                                              + "Is available under GPLv3 on GitHub. For more information \n"
                                              + "and support the project, please visit \n"
                                              + "<html><a href='https://github.com/JuanjoSalvador'>https://github.com/JuanjoSalvador</a></html>" , "About the project", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (e.getSource() == license) {
            JOptionPane.showMessageDialog(null, "This program is free software: you can redistribute it and/or modify\n" +
                                                "it under the terms of the GNU General Public License as published by\n" +
                                                "the Free Software Foundation, either version 3 of the License, or\n" +
                                                "(at your option) any later version.\n" +
                                                "\n" +
                                                "This program is distributed in the hope that it will be useful,\n" +
                                                "but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
                                                "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
                                                "GNU General Public License for more details.\n" +
                                                "\n" +
                                                "You should have received a copy of the GNU General Public License\n" +
                                                "along with this program.  If not, see <http://www.gnu.org/licenses/>", "License", JOptionPane.INFORMATION_MESSAGE);
        }
    }
   
    @Override
    public void stateChanged(ChangeEvent e) {
        volume = (JSlider)e.getSource();
        int setVolumen = volume.getValue();
        double volumen = (double) volume.getValue() / 100;
        String volLvl = String.valueOf(setVolumen);
        volumeLevel.setText(volLvl);
        try {
            player.SoundVolume(volumen);
        } catch (BasicPlayerException ex) {
            
        }
    }
}
