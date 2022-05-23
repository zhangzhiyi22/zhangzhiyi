package view;


import model.ChessColor;
import model.ChessComponent;
import model.EmptySlotComponent;
import model.RookChessComponent;
import model.BishopChessComponent;
import model.KingChessComponent;
import model.QueenChessComponent;
import model.PawnChessComponent;
import controller.ClickController;
import model.KnightChessComponent;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor = ChessColor.WHITE;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    public ChessGameFrame chessGameFrame;
    public int counter=2;


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.

        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);


        initiateEmptyChessboard();

        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        //骑士;
        initKnightOnBoard(0, 1, ChessColor.BLACK);
        initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
        //象
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE);
        //国王
        initKingOnBoard(0, 3, ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE);
        //王后
        initQueenOnBoard(0, 4, ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE);
        //小兵
        initPawnOnBoard(1, 0, ChessColor.BLACK);
        initPawnOnBoard(1, 1, ChessColor.BLACK);
        initPawnOnBoard(1, 2, ChessColor.BLACK);
        initPawnOnBoard(1, 3, ChessColor.BLACK);
        initPawnOnBoard(1, 4, ChessColor.BLACK);
        initPawnOnBoard(1, 5, ChessColor.BLACK);
        initPawnOnBoard(1, 6, ChessColor.BLACK);
        initPawnOnBoard(1, 7, ChessColor.BLACK);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 0, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 1, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 2, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 3, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 4, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 5, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 6, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 7, ChessColor.WHITE);


    }
    public void reset(){
        removeAll();
        initiateEmptyChessboard();

        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        //骑士;
        initKnightOnBoard(0, 1, ChessColor.BLACK);
        initKnightOnBoard(0, CHESSBOARD_SIZE - 2, ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, 1, ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 2, ChessColor.WHITE);
        //象
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(0, CHESSBOARD_SIZE - 3, ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, 2, ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 3, ChessColor.WHITE);
        //国王
        initQueenOnBoard(0, 3, ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE - 1, 3, ChessColor.WHITE);
        //王后
        initKingOnBoard(0, 4, ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE - 1, 4, ChessColor.WHITE);
        //小兵
        initPawnOnBoard(1, 0, ChessColor.BLACK);
        initPawnOnBoard(1, 1, ChessColor.BLACK);
        initPawnOnBoard(1, 2, ChessColor.BLACK);
        initPawnOnBoard(1, 3, ChessColor.BLACK);
        initPawnOnBoard(1, 4, ChessColor.BLACK);
        initPawnOnBoard(1, 5, ChessColor.BLACK);
        initPawnOnBoard(1, 6, ChessColor.BLACK);
        initPawnOnBoard(1, 7, ChessColor.BLACK);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 0, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 1, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 2, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 3, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 4, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 5, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 6, ChessColor.WHITE);
        initPawnOnBoard(CHESSBOARD_SIZE - 2, 7, ChessColor.WHITE);

    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if(chess2 instanceof KingChessComponent &&chess2.getChessColor()==ChessColor.BLACK){
            chessGameFrame.attention("Black win");
            chessGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
        }
        if(chess2 instanceof KingChessComponent &&chess2.getChessColor()==ChessColor.WHITE){
            chessGameFrame.attention("White win");
            chessGameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
        }
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;

        if(kill(chess1)){
            chessGameFrame.attention("The king in danger");
        }

        chess1.repaint();
        chess2.repaint();
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
        chessGameFrame.addWhite();
        counter++;
    }
  //车
    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    //骑士
    private void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    //象
    private void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    //国王
    private void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    //皇后
    private void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    //小兵
    private void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
        chessData.forEach(System.out::println);
    }
    public ChessColor getOppositeColor(){
        return currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
    }
    public void setCurrentColor(ChessColor chessColor){
        currentColor = chessColor;
    }

    public void putChess(int row,String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'G') {
                initKingOnBoard(row, i, ChessColor.BLACK);
            } else if (line.charAt(i) == 'C') {
                initKnightOnBoard(row, i, ChessColor.BLACK);
            } else if (line.charAt(i) == 'A') {
                initBishopOnBoard(row, i, ChessColor.BLACK);
            } else if (line.charAt(i) == 'H') {
                initRookOnBoard(row, i, ChessColor.BLACK);
            } else if (line.charAt(i) == 'E') {
                initQueenOnBoard(row, i, ChessColor.BLACK);
            } else if (line.charAt(i) == 'N') {
                initPawnOnBoard(row, i, ChessColor.BLACK);
            } else if (line.charAt(i) == 'g') {
                initKingOnBoard(row, i, ChessColor.WHITE);
            } else if (line.charAt(i) == 'c') {
                initKnightOnBoard(row, i, ChessColor.WHITE);
            } else if (line.charAt(i) == 'a') {
                initBishopOnBoard(row, i, ChessColor.WHITE);
            } else if (line.charAt(i) == 'h') {
                initRookOnBoard(row, i, ChessColor.WHITE);
            } else if (line.charAt(i) == 'e') {
                initQueenOnBoard(row, i, ChessColor.WHITE);
            } else if (line.charAt(i) == 'n') {
                initPawnOnBoard(row, i, ChessColor.WHITE);
            } else if (line.charAt(i) == '-') {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(row, i), calculatePoint(row, i), clickController, CHESS_SIZE));
            } else {
            }

        }
    }
    public boolean kill(ChessComponent chess){
        for(int i=0;i<chess.getCanMoveTo(chessComponents).size();i++){
            ChessboardPoint temp = chess.getCanMoveTo(chessComponents).get(i);
            if(chessComponents[temp.getX()][temp.getY()] instanceof KingChessComponent&&chessComponents[temp.getX()][temp.getY()].getChessColor()!=chess.getChessColor()){
                return true;
            }
        }
        return false;
    }
}
