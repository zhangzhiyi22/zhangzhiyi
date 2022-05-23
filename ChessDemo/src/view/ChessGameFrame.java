package view;

import controller.GameController;
import model.ChessColor;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    JLabel statusLabel1 = new JLabel("WHITE");
    JLabel statusLabel = new JLabel("1回合");
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE = 608;
    private GameController gameController;
    public static int savetimes = 0;
     private ImageIcon background;
     private int counter;
    JLabel label = new JLabel();
    public void setBackground(ImageIcon background) {
        this.background = background;
    }

    //    Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;

        //  this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;


        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();
        addLabel();
        addplayer();
       addHelloButton();
       addLoadButton();
       addResetButton();
       addStoreButton();
        skin();
        addback();


           }


        /**
         * 在游戏面板中添加棋盘
         */
          private void addChessboard() {
         Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.chessGameFrame = this;
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);
         }

   private void addback() {
       String path1 = "images/background.png";
        background = new ImageIcon(path1);
        label.setIcon(background);
       label.setBounds(0, 0, this.getWidth(), this.getHeight());
       JPanel imagePanel = (JPanel) this.getContentPane();
       imagePanel.setOpaque(false);
       this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
       add(label);
   }
   private void skin(){
       JButton button1 = new JButton("skin");
       button1.setLocation(HEIGTH, HEIGTH / 10 + 300);
       button1.setSize(200, 60);
       button1.setFont(new Font("Rockwell", Font.BOLD, 20));
       add(button1);
       button1.addActionListener(e -> {
           counter++;
           String[] temp = {"images/background.png",".\\images/back1.png"};

            label.setIcon(new ImageIcon(temp[counter%2]));

           label.setBounds(0, 0, this.getWidth(), this.getHeight());
           JPanel imagePanel = (JPanel) this.getContentPane();
           imagePanel.setOpaque(false);
           this.getLayeredPane().add(label,Integer.valueOf(Integer.MIN_VALUE));
           add(label);


       });

   }
        /**
         * 在游戏面板中添加标签
         */
          private void addLabel() {

        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setForeground(Color.cyan);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
              statusLabel.setOpaque(true);
              statusLabel.setBackground(Color.darkGray);
        add(statusLabel);
          }
         private void addplayer(){


        statusLabel1.setLocation(HEIGTH, HEIGTH / 10 - 60);
        statusLabel1.setSize(200, 60);
        statusLabel1.setForeground(Color.RED);
        statusLabel1.setFont(new Font("Rockwell", Font.BOLD, 20));
        statusLabel1.setOpaque(true);
        statusLabel1.setBackground(Color.darkGray);
        add(statusLabel1);
          }

           public void addWhite(){
              if(gameController.getChessboard().getCurrentColor()==ChessColor.WHITE){
              statusLabel1.setText("WHITE");}
              else {
                  statusLabel1.setText("BLACK");
              }
              statusLabel.setText(String.valueOf((gameController.getChessboard().counter+1)/2)+"回合");
           }

        /**
         * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
         */

         private void addHelloButton() {
        JButton button = new JButton("Show Hello Here");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
         }

        public void  change(){
            Label statusLabel = null;
            assert false;
            statusLabel.setText(gameController.getChessboard().getCurrentColor()==ChessColor.WHITE?"WHITE":"BLACK");
             statusLabel.repaint();
        }




          private void addResetButton(){
//          Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);


//        gameController = new GameController(chessboard);
//        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);

        JButton button1 = new JButton("Reset");
        button1.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button1.setSize(200, 60);
        button1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button1);
        button1.addActionListener(e -> {

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
//            chessboard.reset();
//            add(chessboard);
        });

         }


//         private void addLoadButton() {
//        JButton button2 = new JButton("Load");
//        button2.setLocation(HEIGTH, HEIGTH / 10 + 240);
//        button2.setSize(200, 60);
//        button2.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(button2);
//
//        button2.addActionListener(e -> {
//            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this, "Input Path here");
//            gameController.loadGameFromFile(path);
//        });
//         }
    private  void addStoreButton() {
        JButton button2 = new JButton("Store");
        button2.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button2.setSize(200, 60);
        button2.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button2);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = "save";
                String path = "saves";
                String line1 = "@LAST_MOVER=" + gameController.getChessboard().getOppositeColor();
                String line2 = "@@";
                String line3 = "";
                String line4 = "", line5 = "", line6 = "";

                String line7 = "", line8 = "", line9 = "", line10 = "",line11="",line12="";


                for (int i = 0; i < 8; i++) {
                    line4 += gameController.getChessboard().getChessComponents()[0][i].getRepresentative();

                }
                for (int i = 0; i < 8; i++) {
                    line5 += gameController.getChessboard().getChessComponents()[1][i].getRepresentative();
                }
                for (int i = 0; i < 8; i++) {
                    line6 += gameController.getChessboard().getChessComponents()[2][i].getRepresentative();
                }
                for (int i = 0; i < 8; i++) {
                    line7 += gameController.getChessboard().getChessComponents()[3][i].getRepresentative();
                }
                for (int i = 0; i < 8; i++) {
                    line8 += gameController.getChessboard().getChessComponents()[4][i].getRepresentative();
                }
                for (int i = 0; i < 8; i++) {
                    line9 += gameController.getChessboard().getChessComponents()[5][i].getRepresentative();
                }
                for (int i = 0; i < 8; i++) {
                    line10 += gameController.getChessboard().getChessComponents()[6][i].getRepresentative();
                }
                for (int i = 0; i < 8; i++) {
                    line11 += gameController.getChessboard().getChessComponents()[7][i].getRepresentative();
                }



                System.out.println("Click load");
                /**JFileChooser chooser = new JFileChooser();
                 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                 int result = chooser.showOpenDialog(null);
                 File parent = chooser.getSelectedFile();
                 //create such a file
                 //String path = parent.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".txt";*/


                try {
                    File file = new File("Files\\"+title+savetimes+".txt");


                    BufferedWriter out = new BufferedWriter(new FileWriter(title+savetimes+".txt"));
                    out.write(line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6 + "\n" + line7 + "\n" + line8 + "\n" +
                            line9 + "\n" + line10 + "\n" + line11 + "\n" + line12  ); // \r\n即为换行
                    out.flush();
                    out.close();
                    System.out.println(line10);
                    System.out.println(line8);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "Game has been Stored successfully");
            }
        });
    }
    private void addLoadButton () {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
                    System.out.println("Click load");

                    int x = savetimes;
                    File file = new File("save" + x + ".txt");
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("save" + x + ".txt"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    String[] lines = new String[11];
                    for (int i = 0; i < 11; i++) {
                        try {
                            lines[i] = reader.readLine();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 11; i++) {
                        System.out.println(lines[i]);
                    }
                    if (lines.length == 11) {
                        if (lines[0].equals("@LAST_MOVER=BLACK")) {
                            gameController.getChessboard().setCurrentColor(ChessColor.WHITE);

                        } else if (lines[0].equals("@LAST_MOVER=WHITE")) {
                            gameController.getChessboard().setCurrentColor(ChessColor.BLACK);

                        } else {
                            gameController.getChessboard().setCurrentColor(ChessColor.NONE);
                        }
                        if (lines[1].equals("@@")) {
                            if (lines[2].equals("")) {
                                if (lines[3].length() == 8) {

                                    if (lines[4].length() == 8) {

                                        if (lines[5].length() == 8) {

                                            if (lines[6].length() == 8) {

                                                if (lines[7].length() == 8) {

                                                    if (lines[8].length() == 8) {

                                                        if (lines[9].length() == 8) {

                                                            if (lines[10].length() == 8) {
                                                                String WholeLine = lines[3] + lines[4] + lines[5] + lines[6] + lines[7] + lines[8] + lines[9] + lines[10];
                                                                List<String> tempList = Arrays.asList(WholeLine.split(""));
                                                                System.out.println(tempList);
                                                                int G = Collections.frequency(tempList, "G");
                                                                int A = Collections.frequency(tempList, "A");
                                                                int E = Collections.frequency(tempList, "E");
                                                                int H = Collections.frequency(tempList, "H");
                                                                int C = Collections.frequency(tempList, "C");
                                                                int N = Collections.frequency(tempList, "N");

                                                                int g = Collections.frequency(tempList, "g");
                                                                int a = Collections.frequency(tempList, "a");
                                                                int ee = Collections.frequency(tempList, "e");
                                                                int h = Collections.frequency(tempList, "h");
                                                                int c = Collections.frequency(tempList, "c");
                                                                int n = Collections.frequency(tempList, "n");


                                                                System.out.println(G);
                                                                System.out.println(A);
                                                                System.out.println(E);
                                                                System.out.println(H);
                                                                System.out.println(C);
                                                                System.out.println(N);

                                                                System.out.println(g);
                                                                System.out.println(a);
                                                                System.out.println(ee);
                                                                System.out.println(h);
                                                                System.out.println(c);
                                                                System.out.println(n);


                                                                if (G == 1 && A <= 2 && E <= 1 && H <= 2 && C <= 2 && N <= 8 && g == 1 && a <= 2 && ee <= 1 && h <= 2 && c <= 2 && n <= 8) {
                                                                    gameController.getChessboard().putChess(0, lines[3]);
                                                                    gameController.getChessboard().putChess(1, lines[4]);
                                                                    gameController.getChessboard().putChess(2, lines[5]);
                                                                    gameController.getChessboard().putChess(3, lines[6]);
                                                                    gameController.getChessboard().putChess(4, lines[7]);
                                                                    gameController.getChessboard().putChess(5, lines[8]);
                                                                    gameController.getChessboard().putChess(6, lines[9]);
                                                                    gameController.getChessboard().putChess(7, lines[10]);


                                                                    gameController.getChessboard().repaint();
                                                                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入棋局成功！");

                                                                } else {
                                                                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！棋子数量错误");
                                                                }

                                                            } else {
                                                                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第十行长度不为8");
                                                            }
                                                        } else {
                                                            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第九行长度不为8");
                                                        }
                                                    } else {
                                                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第八行长度不为8");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第七行长度不为8");
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第六行长度不为8");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第五行长度不为8");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第四行长度不为8");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第三行不为空行");
                                }
                            } else {
                                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！第二行不为@@");
                            }
                        } else {
                            JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入" + file.getName() + "棋局失败！存档文件行数不规范！（规范行数：12）");
                        }
                    } else {
                        JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(gameController.getChessboard()), "载入棋局失败！没有选择文件");
                    }


                    //gameController.loadGameFromFile(path);

                }
        );
    }

    public void attention(String string){
             JOptionPane jOptionPane =new JOptionPane();
             jOptionPane.showMessageDialog(this,string);
    }
}
