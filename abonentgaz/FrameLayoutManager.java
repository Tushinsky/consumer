/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import java.awt.Dimension;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author operator
 */
public class FrameLayoutManager {
    private String filename;// 
    private int width;
    private int height;
    private int top;
    private int left;
    private Properties props;
    
    public FrameLayoutManager(String filename){
        this.filename = filename;
        if (props == null) props = new Properties();
        try {
            readFileProperties();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    /**
     * устанавливает расположение фрейма на основании данных
     * в указанном файле свойств
     * @return 
     */
    public Point setFrameLocation(){
        Point p = new Point();
        p.x = left;
        p.y = top;
        return p;
    }
    
    /**
     * записывает расположение фрейма в указанный файл свойств
     * @param frame
     */
    public void getFrameLocation(JFrame frame){
        // устанавливаем значения свойств
        props.setProperty("top", 
                String.valueOf(frame.getLocation().y));
        props.setProperty("left", 
                String.valueOf(frame.getLocation().x));
        try {
            writeFileProperties();// записываем свойства в файл свойств
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * задаёт размеры указанного фрейма
     * @return 
     */
    public Dimension setFrameSize(){
        Dimension d = new Dimension();
        d.height = height;
        d.width = width;
        return d;
    }
    
    /**
     * записывает размеры фрейма в указанный файл свойств
     * @param frame
     */
    public void getFrameSize(JFrame frame){
        // устанавливаем значения свойств
        props.setProperty("height", 
                String.valueOf(frame.getSize().height));
        props.setProperty("width", 
                String.valueOf(frame.getSize().width));
        try {
            writeFileProperties();// записываем свойства в файл свойств
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
        // устанавливаем значения свойств
        props.setProperty("width",String.valueOf(width));
        try {
            writeFileProperties();// записываем свойства в файл свойств
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
        // устанавливаем значения свойств
        props.setProperty("height",String.valueOf(height));
        try {
            writeFileProperties();// записываем свойства в файл свойств
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the top
     */
    public int getTop() {
        return top;
    }

    /**
     * @param top the top to set
     */
    public void setTop(int top) {
        this.top = top;
        // устанавливаем значения свойств
        props.setProperty("top", 
                String.valueOf(top));
        try {
            writeFileProperties();// записываем свойства в файл свойств
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the left
     */
    public int getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(int left) {
        this.left = left;
        // устанавливаем значения свойств
        props.setProperty("left", 
                String.valueOf(left));
        try {
            writeFileProperties();// записываем свойства в файл свойств
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * читает указанный файл свойств
     */
    private void readFileProperties() throws FileNotFoundException, IOException {
        try (FileInputStream in = new FileInputStream(filename)) {
            props.load(in);
            in.close();// закрываем входной поток
            String index = props.getProperty("height");
            int i = index == null ? 300 : Integer.parseInt(index);
            height = i;
            index = props.getProperty("width");
            i = index == null ? 300 : Integer.parseInt(index);
            width= i;
            index = props.getProperty("top");
            i = index == null ? 
                    Integer.parseInt(String.valueOf(height/2)) :
                    Integer.parseInt(index);
            top = i;
            index = props.getProperty("left");
            i = index == null ?
                    Integer.parseInt(String.valueOf(width/2)) :
                    Integer.parseInt(index);
            left = i;
        }
    }
    
    private void writeFileProperties() throws FileNotFoundException {
        //СЃРѕР·РґР°С‘Рј РїРѕС‚РѕРє РґР»СЏ РІС‹РІРѕРґР°
            FileOutputStream out=new FileOutputStream(filename);
            try {
                props.store(out, filename);
                out.close();//Р·Р°РєСЂС‹РІР°РµРј РїРѕС‚РѕРє РІС‹РІРѕРґР°
            } catch (IOException ex) {
                Logger.getLogger(UserProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
