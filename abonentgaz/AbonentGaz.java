/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import frames.MDIObject;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Сергей
 */
public class AbonentGaz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(AbonentGaz.class.getName()).log(Level.SEVERE, null, ex);
//        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //To change body of generated methods, choose Tools | Templates.
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(AbonentGaz.class.getName()).log(Level.SEVERE, null, ex);
                }
                MDIObject mdiObject = new MDIObject();
                MDIObject.setDefaultLookAndFeelDecorated(true);
                mdiObject.setTitle("Потребитель");// задаём заголовок
                mdiObject.setType(Window.Type.NORMAL);
                mdiObject.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                mdiObject.setVisible(true);// отображаем форму
            }
        });
//        MDIObject mdiObject = new MDIObject();
//        MDIObject.setDefaultLookAndFeelDecorated(true);
//        mdiObject.setTitle("Потребитель");// задаём заголовок
//        mdiObject.setType(Window.Type.NORMAL);
//        mdiObject.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        mdiObject.setVisible(true);// отображаем форму
    }
}
