package controller;


import model.ChessComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ClickController  {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {

        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {

        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                for(int i=0;i<first.getCanMoveTo(chessboard.getChessComponents()).size();i++){
                    ChessboardPoint temp = first.getCanMoveTo(chessboard.getChessComponents()).get(i);
                    if(first.getChessColor()!=chessboard.getChessComponents()[temp.getX()][temp.getY()].getChessColor()){
                    chessboard.getChessComponents()[temp.getX()][temp.getY()].setChosen(true);
                    chessboard.getChessComponents()[temp.getX()][temp.getY()].repaint();}
                }
                zhang(new File(".\\啪啪啪_Freesound.wav"));
                first.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                for(int i=0;i<first.getCanMoveTo(chessboard.getChessComponents()).size();i++){
                    ChessboardPoint temp = first.getCanMoveTo(chessboard.getChessComponents()).get(i);
                    chessboard.getChessComponents()[temp.getX()][temp.getY()].setChosen(false);
                    chessboard.getChessComponents()[temp.getX()][temp.getY()].repaint();
                    zhang(new File(".\\啪啪啪_Freesound.wav"));

                }
                first.repaint();
                ChessComponent recordFirst = first;
                first = null;
//                for(int i=0;i<first)
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {
                for(int i=0;i<first.getCanMoveTo(chessboard.getChessComponents()).size();i++){
                    ChessboardPoint temp = first.getCanMoveTo(chessboard.getChessComponents()).get(i);
                    chessboard.getChessComponents()[temp.getX()][temp.getY()].setChosen(false);
                    chessboard.getChessComponents()[temp.getX()][temp.getY()].repaint();
                    zhang(new File(".\\啪啪啪_Freesound.wav"));

                }
                first.repaint();
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();

                first.setSelected(false);
                first = null;
            }
        }


    }


    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }

    public static void zhang(File file){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
