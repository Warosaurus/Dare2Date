/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gareth
 */
public class MyMouseListener extends MouseAdapter {
    
    public void mouseClicked(MouseEvent e){
                JTextField textfield = (JTextField) e.getComponent();
                textfield.setText("");
            }
}
