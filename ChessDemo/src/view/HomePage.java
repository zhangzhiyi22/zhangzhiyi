package view;


import javax.swing.*;
import java.awt.*;
public class HomePage {
  JFrame jj=new JFrame();
  public HomePage(){
      jj.setTitle("ChessGame");
      jj.setSize(900,700);
      jj.setLocationRelativeTo(null);
      jj.getContentPane().setLayout(null);
      JPanel kk=(JPanel) jj.getContentPane();
      kk.setOpaque(false);
      ImageIcon ll=new ImageIcon("images/background.png");
      JLabel label=new JLabel(ll);
      label.setBounds(0,0,jj.getWidth(),jj.getHeight());
      ll.setImage(ll.getImage().getScaledInstance(ll.getIconWidth(),ll.getIconHeight(),Image.SCALE_DEFAULT));
      jj.getLayeredPane().add(label,Integer.valueOf(Integer.MIN_VALUE));

      //标签
      JLabel ii = new JLabel("国际象棋");
     // statusLabel.setLocation(jj.getWidth()/3, jj.getHeight() / 3);
    //  statusLabel.setSize(200, 60);
      ii.setFont(new Font("楷书", Font.BOLD, 60));
      ii.setForeground(Color.green);
      ii.setBounds(jj.getWidth()/3,jj.getHeight()/3,jj.getWidth()/3,40);
      ii.setHorizontalAlignment(JLabel.CENTER);






      JButton button=new JButton("Start");
      button.setForeground(Color.BLACK);
      button.setFont(new Font("Impact",Font.PLAIN,60));
      button.setBounds(100,400,200,70);
      button.setHorizontalAlignment(JButton.CENTER);
      button.addActionListener(e -> {
                  ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
                  mainFrame.setVisible(true);
          Mythread mythread = new Mythread();
          mythread.start();
          mythread.setPlay(mainFrame);
              });
          JButton button1=new JButton("exit");
          button1.setForeground(Color.BLACK);
          button1.setFont(new Font("Impact",Font.PLAIN,60));
          button1.setBounds(350,400,200,70);
          button1.setHorizontalAlignment(JButton.CENTER);
          button1.addActionListener(e ->{
              jj.dispose();

      });
      jj.setResizable(false);
     jj.getContentPane().add(ii);
      jj.getContentPane().add(button);
      jj.getContentPane().add(button1);
     // jj.getContentPane().add(button3);
      jj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      jj.setVisible(true);
  }
}
