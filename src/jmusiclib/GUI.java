/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmusiclib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author Juanjo
 */
public class GUI implements ActionListener, ChangeListener {
    private JFrame main_window;
    private JButton play, pause, stop, next, last;
    private JLabel volumeLevel, musicPosition;
    private JTextField search_input;
    private JSlider volume;
    private JPanel library, mPlayer, mPlaylist;
    private final Border blackline = BorderFactory.createLineBorder(Color.gray);
    
    MusicPlayer player = new MusicPlayer();
    
    public void main() {
        main_window = new JFrame("JmusicLib Alpha Dev 0.3 - Testing GUI");
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
        
        /* AUDIO CONTROLS*/
        
        mPlayer = new JPanel(new FlowLayout());
        Dimension audioControl = new Dimension();
        audioControl.width = 810;
        audioControl.height = 80;
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
        
            //Music position
        Dimension posBarDim = new Dimension();
        posBarDim.width = 800;
        posBarDim.height = 17;
        int POS_MIN = 0;
        int POS_MAX = 100;
        int POS_INIT = 0;
        JSlider posBar = new JSlider(JSlider.HORIZONTAL, POS_MIN, POS_MAX, POS_INIT);
        posBar.setPreferredSize(posBarDim);
        
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
        String[][] music = Database.showAll();
        Object[][] data = {{"1", "Titulo", "Artista", "Disco"}};
        
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
        
        //library.add(path_input);
        library.add(search_input);
        library.add(mTable);
        library.add(new JScrollPane(mTable));
        library.setBackground(bg);
        
        
            // END OF LIBRARY
        
        /* PLAYLIST */
        String[] dataList = {"List Element", "List Element", "List Element"};
        mPlaylist = new JPanel(new FlowLayout());
        
        JList list = new JList(dataList);
        list.setBorder(blackline);
        
        Dimension playlistPanel = new Dimension();
        playlistPanel.width = 220;
        playlistPanel.height = 490;
        
        Dimension playlistD = list.getPreferredSize();
        playlistD.width = 210;
        playlistD.height = 450;
        
        mPlaylist.setPreferredSize(playlistPanel);
        
        JButton savePlaylist = new JButton("Save");
        savePlaylist.addActionListener(this);
        savePlaylist.setName("save");
        JButton openPlaylist = new JButton("Open");
        openPlaylist.addActionListener(this);
        openPlaylist.setName("open");
        
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
        
        main_window.add(mPlaylist);
        main_window.add(library);
        main_window.add(mPlayer);
        
        main_window.setLayout(new FlowLayout());
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Cerrar proceso al cerrar ventana
        main_window.setSize(1000, 650); // Tamaño
        main_window.setVisible(true);  // Visible
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton o = (JButton)e.getSource();
        String name = o.getName();
        
        if (name == "play") {
            String mp3 = "test.mp3"/*search_input.getText()*/;
            try {
                player.loadFile(Library.music_path() + "/" + mp3);
                player.play();    
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        
        if (name == "pause") {
            try {
                player.pausa();
            } catch (Exception ex) {
               
            }
        }
        
        if (name == "stop") {
            try {
                player.pausa();
                player.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        if (name == "next") {
            JOptionPane.showMessageDialog(null, "Not supported on Alpha Dev.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (name == "last") {
            JOptionPane.showMessageDialog(null, "Not supported on Alpha Dev.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (name == "save") {
            JOptionPane.showMessageDialog(null, "Not supported on Alpha Dev.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if (name == "open") {
            JOptionPane.showMessageDialog(null, "Not supported on Alpha Dev.", "Info", JOptionPane.INFORMATION_MESSAGE);
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
