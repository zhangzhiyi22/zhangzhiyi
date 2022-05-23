package model;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public  class KnightChessComponent extends ChessComponent{
    private static Image HORSE_WHITE;
    private static Image HORSE_BLACK;
    private Image knightImage;
    public void loadResource() throws IOException {
        if (HORSE_WHITE == null) {
            HORSE_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (HORSE_BLACK == null) {
            HORSE_BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }
    }
    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                knightImage = HORSE_WHITE;
            } else if (color == ChessColor.BLACK) {
                knightImage = HORSE_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKnightImage(color);
    }
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if((Math.abs(source.getX()-destination.getX())==2 &&Math.abs(source.getY()-destination.getY())==1)||
                (Math.abs(source.getY()-destination.getY())==2 &&Math.abs(source.getX()-destination.getX())==1)){
            if (!(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)) {
                return true;
            }

             return true;
        }else {
            return false;
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(knightImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

    @Override
    public String getRepresentative() {
        if (chessColor==ChessColor.BLACK){
            return "C";
        }else {
            return "c";
        }
    }
}
