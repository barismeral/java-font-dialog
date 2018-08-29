/*                  Copyright (C) 2018  Barış Meral

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <https://www.gnu.org/licenses/>.
        */
		
		package dialogs;
		
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import dialogs.*;

/**
 * @author Baris Meral
 * @version 1.1
 * @see java.awt.event.ActionListener
 * @see java.util.EventListener
 * @see javax.swing.event.ListSelectionListener
 * <b>its usage is as follows</b>
 * <pre>
 *  {@code
 *
 *   JTexarea textarea = new JTextarea(); // or JTextField or JLabel
 *
 *       JFontDialog fontDialog = new JfontDialog(textarea);
 *
 *     }
 * </pre>
 *
 *
 */
public class JFontDialog  implements ListSelectionListener,ActionListener {

   private static JFrame dialogFrame;
   private static JPanel fontNamePanel,fontTypePanel,fontSizePanel,buttonPanel,samplePanel;
   private static JList<String> fontNameList,fontTypeList,fontSizeList;
   private static JScrollPane fontPane,sizePane;
   private static JLabel sampleLabel = new JLabel("Sample..."),fontLabel,typeLabel,sizeLabel;
   private static JButton okButton,cancelButton;
   private static JSeparator separator;
   private static String[] graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
   String[] fontTypeArray,fontSizeArray;
   private static Font oldFont = null;
   private static boolean cancel=false;

    /**
     * @param textArea
     *
     */
   public JFontDialog(JTextArea textArea){

       createDialog(textArea);

            oldFont = textArea.getFont();
   }

    /**
     * <b>Created Dialog Window</b>
     * @param textArea
     */
    private void createDialog(JTextArea textArea){

       dialogFrame = new JFrame("Font Dialog");
       dialogFrame.setLocationRelativeTo(null);
       dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       dialogFrame.setSize(430,440);
       dialogFrame.setResizable(false);
       dialogFrame.setLayout(null);

                setLists();
                createButton();
                setPanels();

      dialogFrame.add(fontNamePanel);
      dialogFrame.add(fontTypePanel);
      dialogFrame.add(fontSizePanel);
      dialogFrame.add(samplePanel);
      dialogFrame.add(separator);
      dialogFrame.add(buttonPanel);
       dialogFrame.setVisible(true);

       dialogFrame.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosed(WindowEvent e) {

               if (cancel==true){
                   textArea.setFont(oldFont);
               }
              else if (cancel==false){
                   textArea.setFont(getFont());
               }
           }
       });
   }

    /**
     * <b>Created and Set JList Components</b>
     */
   private void setLists(){

           fontSizeArray = new String[29];
       for (int i = 6,j=0; i<=62;i+=2,j++)
           fontSizeArray[j]=String.valueOf(i);

      fontSizeList = new JList<>(fontSizeArray);
      fontSizeList.setSelectionMode(0);
      fontSizeList.setSelectedIndex(16);
      fontSizeList.addListSelectionListener(this);
      fontSizeList.setFont(new Font("Arial",Font.CENTER_BASELINE,16));


     graphicsEnvironment[0] = "Adobe";
     fontNameList = new JList<>(graphicsEnvironment);
     fontNameList.setSelectionMode(0);
     fontNameList.setSelectedIndex(3);
     fontNameList.addListSelectionListener(this);
     fontNameList.setFont(new Font("Arial",Font.CENTER_BASELINE,16));

     fontTypeArray = new String[]{"Plain", "Bold", "Italic"};
     fontTypeList = new JList<>(fontTypeArray);
     fontTypeList.setBorder(new LineBorder(Color.BLACK,1));
     fontTypeList.setSelectionMode(0);
     fontTypeList.setSelectedIndex(1);
     fontTypeList.addListSelectionListener(this);
     fontTypeList.setFont(new Font("Arial",Font.CENTER_BASELINE,16));

   }

    /**
     * <b>Created and sey Panels for Components</b>
     *
     */
   private void setPanels(){

       fontLabel = new JLabel("Fonts: ");
       fontLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
       fontNamePanel = new JPanel(new BorderLayout());
       fontPane = new JScrollPane(fontNameList);
       fontNamePanel.add(fontLabel,BorderLayout.NORTH);
       fontNamePanel.add(fontPane,BorderLayout.CENTER);
       fontNamePanel.setBounds(30,20,220,200);

       typeLabel = new JLabel("Type: ");
       typeLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
       fontTypePanel = new JPanel(new BorderLayout());
       fontTypePanel.add(fontTypeList,BorderLayout.CENTER);
       fontTypePanel.add(typeLabel,BorderLayout.NORTH);
       fontTypePanel.setBounds(270,20,50,100);

       sizeLabel = new JLabel("Sizes: ");
       sizeLabel.setFont(new Font("Arial",Font.CENTER_BASELINE,14));
       fontSizePanel = new JPanel(new BorderLayout());
       sizePane = new JScrollPane(fontSizeList);
       fontSizePanel.add(sizePane,BorderLayout.CENTER);
       fontSizePanel.add(sizeLabel,BorderLayout.NORTH);
       fontSizePanel.setBounds(340,20,50,200);

       samplePanel = new JPanel();
       samplePanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Sample"));
       samplePanel.add(sampleLabel);
       samplePanel.setBounds(20,240,375,100);

       separator = new JSeparator(JSeparator.HORIZONTAL);
       separator.setForeground(Color.gray);
       separator.setBounds(0,350,430,2);

       buttonPanel = new JPanel(new BorderLayout());
       buttonPanel.add(okButton,BorderLayout.EAST);
       buttonPanel.add(cancelButton,BorderLayout.WEST);
       buttonPanel.setBounds(200,360,200,25);


   }

    /**
     * <b>Created Buttons </b>
     */
   private void createButton(){

      okButton = new JButton("ok");
      okButton.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
      okButton.addActionListener(this::actionPerformed);

      cancelButton = new JButton("cancel");
      cancelButton.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
      cancelButton.addActionListener(this::actionPerformed);

   }

    /**
     *
     * @return selected Font
     */
   public static Font getFont(){
       return sampleLabel.getFont();
   }

    @Override
    public void valueChanged(ListSelectionEvent e) {

       sampleLabel.setFont(new Font(fontNameList.getSelectedValue(),fontTypeList.getSelectedIndex(),Integer.valueOf(fontSizeList.getSelectedValue())));
    }

    @Override
    public void actionPerformed(ActionEvent e) {


       if (e.getSource()==okButton) {
           cancel=false;
           dialogFrame.dispose();
       }

       else if (e.getSource()==cancelButton){
                   cancel=true;
                   dialogFrame.dispose();
       }


    }


}