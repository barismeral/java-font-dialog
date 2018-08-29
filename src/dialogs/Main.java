package dialogs;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import dialogs.*;


  
  
public class Main {

    public static void main(String[] args) {

        /*-------------  JFrame Create and Set -------------*/
        JFrame frame = new JFrame();
        frame.setSize(960,640);
        frame.setDefaultCloseOperation(3);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Demo");


        /*-------------  JMenuBar Create and Set -------------*/
        JMenuBar menuBar = new JMenuBar();
        JMenu fontEditMenu = new JMenu("Font Select");
        menuBar.add(fontEditMenu);
        frame.setJMenuBar(menuBar);

         /*-------------  JTextArea Create and Set -------------*/
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setBorder(new LineBorder(Color.BLACK,5));
        area.setCursor(Cursor.getDefaultCursor());
        Font defFont = new Font("Arial",Font.BOLD,30);
        area.setFont(defFont);


        frame.getContentPane().add(area,BorderLayout.CENTER);

        java.awt.EventQueue.invokeLater(() -> frame.setVisible(true));





         fontEditMenu.addMenuListener(new MenuListener() {
             @Override
             public void menuSelected(MenuEvent e) {

                JFontDialog dialog = new JFontDialog(area);

             }
                 @Override
                 public void menuDeselected (MenuEvent e){

                 }

                 @Override
                 public void menuCanceled (MenuEvent e){

                 }

         });







    }
}
