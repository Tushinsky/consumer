/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.prefs.Preferences;
import javax.swing.JFrame;

/**
 *
 * @author Сергей
 */
public class PreferenceProperties {
    final Preferences node;

    /**
     * 
     * @param nodeName имя узла хранилища
     * 
     */
    public PreferenceProperties(String nodeName) {
        // считываем данные из узла дерева
        Preferences root = Preferences.userRoot();
        node = root.node(nodeName);
    }
    
    public void setFrameBound(JFrame frame) {
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        int screenHeight=screenSize.height;
        int screenWidth=screenSize.width;
        
        //задаём размеры и местоположение формы
        int frameHeight = node.getInt("height", 300);
        int frameWidth = node.getInt("width", 300);
        int top = node.getInt("top", (screenHeight-frameHeight)/2);
        int left = node.getInt("left", (screenWidth-frameWidth)/2);
        
        //устанавливаем размер и расположение формы
        frame.setBounds(left, top, frameWidth, frameHeight);
    }
    
    /**
     * @return получает размеры и положение формы на экране и записывает
     * их в хранилище
     */
    public void getFrameBound (JFrame frame) throws FileNotFoundException {
        // записываем значения свойств в хранилище
        node.putInt("height", frame.getHeight());
        node.putInt("width", frame.getWidth());
        node.putInt("top", frame.getLocation().y);
        node.putInt("left", frame.getLocation().x);
    }
    
    public void resizeComponent(JFrame frame) throws FileNotFoundException {
        node.putInt("height", frame.getHeight());
        node.putInt("width", frame.getWidth());
    }
    
    public void moveComponent(JFrame frame) throws FileNotFoundException {
        node.putInt("top", frame.getLocation().y);
        node.putInt("left", frame.getLocation().x);
    }
    
    /**
     * @return the String property
     */
    public String getProperty(String keyName, String defValue) {
        return node.get(keyName, defValue);
    }
    
    public int getProperty(String keyName, int defValue) {
        return node.getInt(keyName, defValue);
    }
    
    public long getProperty(String keyName, long defValue) {
        return node.getLong(keyName, defValue);
    }
    
    public double getProperty(String keyName, double defValue) {
        return node.getDouble(keyName, defValue);
    }
    
    public float getProperty(String keyName, float defValue) {
        return node.getFloat(keyName, defValue);
    }
    
    public boolean getProperty(String keyName, boolean defValue) {
        return node.getBoolean(keyName, defValue);
    }
    

    /**
     * @param property the property to set
     */
    public void setProperty(String keyName, String value) {
        node.put(keyName, value);
    }
    
    public void setProperty(String keyName, int value) {
        node.putInt(keyName, value);
    }
    
    public void setProperty(String keyName, long value) {
        node.putLong(keyName, value);
    }
    
    public void setProperty(String keyName, float value) {
        node.putFloat(keyName, value);
    }
    
    public void setProperty(String keyName, boolean value) {
        node.putBoolean(keyName, value);
    }
    
    public void setProperty(String keyName, double value) {
        node.putDouble(keyName, value);
    }
    
}
