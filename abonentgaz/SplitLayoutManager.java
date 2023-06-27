/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abonentgaz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lera
 */
public class SplitLayoutManager {
    private Properties props;
    private int location;// местоположение разделителя на форме
    private String fileName;// имя файла свойств
    private String keyName;// имя ключа в файле свойств
    
    public SplitLayoutManager(String filename, String keyname){
        fileName = filename;
        keyName = keyname;
        props = new Properties();
        if (props == null) props = new Properties();
        try {
            readFileProperties();
            String value = props.getProperty(keyname);
//            System.out.println("value=" + value);
            // положение резделителя на форме
            location = !value.equals("") ? Integer.parseInt(value) : 0;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SplitLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SplitLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public SplitLayoutManager(String filename){
        fileName = filename;
        props = new Properties();
        if (props == null) props = new Properties();
        try {
            readFileProperties();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SplitLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SplitLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the location
     */
    public int getLocation() {
        
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(int location) {
        this.location = location;
        // устанавливаем значение свойства
        props.setProperty(keyName, String.valueOf(location));
        try {
            // записываем его в файл свойств
            writeFileProperties();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SplitLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readFileProperties() throws FileNotFoundException, IOException {
        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
            in.close();// закрываем входной поток
        }
    }
    
    private void writeFileProperties() throws FileNotFoundException {
        // записываем значения в файл свойств
            FileOutputStream out=new FileOutputStream(fileName);
            try {
                props.store(out, fileName);
//                System.out.println(props.getProperty(keyName));
                out.close();// закрываем выходной поток
            } catch (IOException ex) {
                Logger.getLogger(SplitLayoutManager.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    /**
     * @param keyName the keyName to set
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
        String value = props.getProperty(keyName);
//            System.out.println("value=" + value);
            // положение резделителя на форме
            if(value != null){
                location = !value.equals("") ? Integer.parseInt(value) : 0;
            } else {
                location = 0;
            }
    }
}
