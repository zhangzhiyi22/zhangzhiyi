package model;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class PawnChessComponent extends ChessComponent {
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;
    private Image pawnImage;

    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }


    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int b = destination.getX() - source.getX();
        int a = destination.getY() - source.getY();
        if (source.getX() == 6 && chessColor.equals(ChessColor.WHITE) && chessComponents[5][source.getY()].chessColor == ChessColor.NONE) {
            return (a == 0 && b == -1) || (a == 0 && b == -2 && chessComponents[4][source.getY()].chessColor == ChessColor.NONE);
        } else if (source.getX() != 6 && chessColor.equals(ChessColor.WHITE)) {
            if (source.getY() != 0 && source.getY() != 7) {
                if (!(chessComponents[source.getX() - 1][source.getY() + 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() - 1][source.getY() ] instanceof EmptySlotComponent)) {
                    return (a == 1 && b == -1) ||(a==0 && b==-1);
                }else if(!(chessComponents[source.getX() - 1][source.getY() - 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() - 1][source.getY() ] instanceof EmptySlotComponent)){
                    return (a == -1 && b == -1) ||(a==0 && b==-1);
                } else if (chessComponents[source.getX() - 1][source.getY()].chessColor == ChessColor.BLACK || chessComponents[source.getX() - 1][source.getY()].chessColor == ChessColor.WHITE) {
                    return false;
                } else {
                    return a == 0 && b == -1;
                }
            } else if (source.getY() == 0) {
                if (!(chessComponents[source.getX() - 1][source.getY() + 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() - 1][source.getY() ] instanceof EmptySlotComponent)) {
                    return (a == 1 && b == -1) ||(a==0 && b==-1);
                } else if (chessComponents[source.getX() - 1][source.getY()].chessColor == ChessColor.BLACK || chessComponents[source.getX() - 1][source.getY()].chessColor == ChessColor.WHITE) {
                    return false;
                } else {
                    return a == 0 && b == -1;
                }
            } else {
                if (!(chessComponents[source.getX() - 1][source.getY() - 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() - 1][source.getY() ] instanceof EmptySlotComponent)) {
                    return (a == -1 && b == -1) ||(a==0 && b==-1);

                } else if (chessComponents[source.getX() - 1][source.getY()].chessColor == ChessColor.BLACK || chessComponents[source.getX() - 1][source.getY()].chessColor == ChessColor.WHITE) {
                    return false;
                } else {
                    return a == 0 && b == -1;
                }
            }


        } else if (source.getX() == 1 && chessColor.equals(ChessColor.BLACK) && chessComponents[2][source.getY()].chessColor == ChessColor.NONE) {
            return (a == 0 && b == 1) || (a == 0 && b == 2 && chessComponents[3][source.getY()].chessColor == ChessColor.NONE);

        } else if (source.getX() != 1 && chessColor.equals(ChessColor.BLACK)) {
            if (source.getY() != 0 && source.getY() != 7) {
                if (!(chessComponents[source.getX() + 1][source.getY() + 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() + 1][source.getY() ] instanceof EmptySlotComponent)) {
                    return (a == 1 && b == 1) || (a==0&& b==1);
                }else if(!(chessComponents[source.getX() + 1][source.getY() - 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() + 1][source.getY() ] instanceof EmptySlotComponent)){
                    return  (a == -1 && b == 1) || (a==0&& b==1);
                } else if (chessComponents[source.getX() + 1][source.getY()].chessColor == ChessColor.BLACK || chessComponents[source.getX() + 1][source.getY()].chessColor == ChessColor.WHITE) {
                    return false;
                } else {
                    return a == 0 && b == 1;
                }
            } else if (source.getY() == 0) {
                if (!(chessComponents[source.getX() + 1][source.getY() + 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() + 1][source.getY() ] instanceof EmptySlotComponent)) {
                    return (a == 1 && b == 1) || (a==0&& b==1);
                } else if (chessComponents[source.getX() + 1][source.getY()].chessColor == ChessColor.BLACK && chessComponents[source.getX() + 1][source.getY()].chessColor == ChessColor.WHITE) {
                    return false;
                } else {
                    return a == 0 && b == 1;
                }
            } else {
                if (!(chessComponents[source.getX() + 1][source.getY() - 1] instanceof EmptySlotComponent) &&
                        (chessComponents[source.getX() + 1][source.getY() ] instanceof EmptySlotComponent)) {
                    return (a == -1 && b == 1) || (a==0&& b==1);

                } else if (chessComponents[source.getX() - +1][source.getY()].chessColor == ChessColor.BLACK && chessComponents[source.getX() + 1][source.getY()].chessColor == ChessColor.WHITE) {
                    return false;
                } else {
                    return a == 0 && b == 1;
                }
            }

        }else {
            return false;
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }

    @Override
    public String getRepresentative() {
        if (chessColor==ChessColor.BLACK){
            return "N";
        }else {
            return "n";
        }
    }
}

