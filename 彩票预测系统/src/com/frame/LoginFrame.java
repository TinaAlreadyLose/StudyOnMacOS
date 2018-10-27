package com.frame;
import javax.swing.*;
import java.awt.*;
public class LoginMain extends JFrame{
    private JPanel contentPane;
    public static  void main(String[] args) {
        try {
            String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        try{
            String lookAndFeel =UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(lookAndFeel);
        }catch (Throwable e){
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LoginMain frame = new LoginMain();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public LoginMain(){
        setTitle("彩票预测系统");
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginMain.class.getResource("/imgs/log.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200,100,1000 ,620);
        contentPane =new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0,0));
        JButton bitnNewButon=new JButton("");
        bitnNewButon.setIcon(new ImageIcon(LoginMain.class.getResource("/imgs/login1.jpg")));
        contentPane.add(bitnNewButon,BorderLayout.CENTER);
    }
}
