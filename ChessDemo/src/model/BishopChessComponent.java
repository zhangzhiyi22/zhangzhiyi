package model;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class BishopChessComponent extends ChessComponent{
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;
    private Image bishopImage;

    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }
    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
    }
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int r1=Math.min(source.getX(), destination.getX());
        int r2=Math.max(source.getX(), destination.getX());
        int c1=Math.min(source.getY(), destination.getY());
        int c2=Math.max(source.getY(), destination.getY());
        int a=source.getX()-destination.getX();  int b=source.getY()-destination.getY();
        if(Math.abs(a)==Math.abs(b) &&a*b>0){
            for(int i=r1+1;i<r2;i++) {
                for(int j=c1+1;j<c2;j++) {
                    int m=i-source.getX(); int n=j-source.getY();
                    if ((!(chessComponents[i][j] instanceof EmptySlotComponent) &&((double)m/n)==1 )) {
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

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

    @Override
    public String getRepresentative() {
        if (chessColor==ChessColor.BLACK){
            return "A";
        }else {
            return "a";
        }
    }

}
