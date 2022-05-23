package view;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */

public class Mythread extends Thread {

    @Override
    public void run() {
        super.run();
        playMusic();
    }

    public void playMusic() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("横山菁児 - 英雄的黎明.wav"));
            AudioFormat aif = ais.getFormat();
            final SourceDataLine sdl;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
            sdl = (SourceDataLine) AudioSystem.getLine(info);
            sdl.open(aif);
            sdl.start();
            FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
            // value可以用来设置音量，从0-2.0
            double value = 2;
            float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
            fc.setValue(dB);
            int nByte = 0;
            int writeByte = 0;
            final int SIZE = 1024 * 64;
            byte[] buffer = new byte[SIZE];
            while (nByte != -1) {
                nByte = ais.read(buffer, 0, SIZE);
                sdl.write(buffer, 0, nByte);
            }
            sdl.stop();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setPlay(JFrame frame) {
        JButton playMusic = new JButton("播放音乐");
        playMusic.setLocation(615, 410);
        playMusic.setSize(120, 60);
        playMusic.setFont(new Font("新宋体", Font.BOLD, 20));
///        playMusic.addActionListener(ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e)
        //       });
        //       frame.add(playMusic);
    }


}
