package model;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class QueenChessComponent extends ChessComponent{
    private static Image QUEEN_WHITE;
    private static Image QUEEN_BLACK;
    private Image queenImage;
    public void loadResource() throws IOException {
        if (QUEEN_WHITE == null) {
            QUEEN_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (QUEEN_BLACK == null) {
            QUEEN_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }
    }
    private void initiateQueenImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                queenImage = QUEEN_WHITE;
            } else if (color == ChessColor.BLACK) {
                queenImage = QUEEN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateQueenImage(color);
    }
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int r1=Math.min(source.getX(), destination.getX());
        int r2=Math.max(source.getX(), destination.getX());
        int c1=Math.min(source.getY(), destination.getY());
        int c2=Math.max(source.getY(), destination.getY());
        int a=source.getX()-destination.getX();  int b=source.getY()-destination.getY();
        if (source.getX() == destination.getX()) {
            int row = source.getX();
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getY() == destination.getY()) {
            int col = source.getY();
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if(Math.abs(a)==Math.abs(b) &&a*b>0){
            for(int i=r1+1;i<r2;i++) {
                for(int j=c1+1;j<c2;j++) {
                    int m=i-source.getX(); int n=j-source.getY();
                    if ((!(chessComponents[i][j] instanceof EmptySlotComponent) && ((double)m/n)==1 )) {
                        return false;
                    }

                }
            }
            return true;
        }else if(Math.abs(a)==Math.abs(b) &&a*b<0){
            for(int i=r1+1;i<r2;i++) {
                for(int j=c2-1;j>c1;j--) {
                    int m=i-source.getX(); int n=j-source.getY();
                    if ((!(chessComponents[i][j] instanceof EmptySlotComponent) && ((double)m/n)==-1 )) {
                        return false;
                    }

                }
            }
            return true;
        }else {
            return false;
        }
        return true;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(queenImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

    @Override
    public String getRepresentative() {
        if (chessColor==ChessColor.BLACK){
            return "E";
        }else {
            return "e";
        }
    }
}
