import javax.swing.*;
public class main {
    public static void main(String[] args){
        JFrame obj = new JFrame();
        gameplay g = new gameplay();
        obj.setBounds(10,10,600,700);
        obj.setTitle("Breakout ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(g);
        obj.setVisible(true);
    }
    
}
