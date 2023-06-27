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
     * ������������� ������������ ������ �� ��������� ������
     * � ��������� ����� �������
     * @return 
     */
    public Point setFrameLocation(){
        Point p = new Point();
        p.x = left;
        p.y = top;
        return p;
    }
    
    /**
     * ���������� ������������ ������ � ��������� ���� �������
     * @param frame
     */
    public void getFrameLocation(JFrame frame){
        // ������������� �������� �������
        props.setProperty("top", 
                String.valueOf(frame.getLocation().y));
        props.setProperty("left", 
                String.valueOf(frame.getLocation().x));
        try {
            writeFileProperties();// ���������� �������� � ���� �������
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ����� ������� ���������� ������
     * @return 
     */
    public Dimension setFrameSize(){
        Dimension d = new Dimension();
        d.height = height;
        d.width = width;
        return d;
    }
    
    /**
     * ���������� ������� ������ � ��������� ���� �������
     * @param frame
     */
    public void getFrameSize(JFrame frame){
        // ������������� �������� �������
        props.setProperty("height", 
                String.valueOf(frame.getSize().height));
        props.setProperty("width", 
                String.valueOf(frame.getSize().width));
        try {
            writeFileProperties();// ���������� �������� � ���� �������
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
        // ������������� �������� �������
        props.setProperty("width",String.valueOf(width));
        try {
            writeFileProperties();// ���������� �������� � ���� �������
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
        // ������������� �������� �������
        props.setProperty("height",String.valueOf(height));
        try {
            writeFileProperties();// ���������� �������� � ���� �������
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
        // ������������� �������� �������
        props.setProperty("top", 
                String.valueOf(top));
        try {
            writeFileProperties();// ���������� �������� � ���� �������
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
        // ������������� �������� �������
        props.setProperty("left", 
                String.valueOf(left));
        try {
            writeFileProperties();// ���������� �������� � ���� �������
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrameLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * ������ ��������� ���� �������
     */
    private void readFileProperties() throws FileNotFoundException, IOException {
        try (FileInputStream in = new FileInputStream(filename)) {
            props.load(in);
            in.close();// ��������� ������� �����
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
        //создаём поток для вывода
            FileOutputStream out=new FileOutputStream(filename);
            try {
                props.store(out, filename);
                out.close();//закрываем поток вывода
            } catch (IOException ex) {
                Logger.getLogger(UserProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
