/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rbsa.eoss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import rbsa.eoss.local.Params;

/**
 *
 * @author Nozomi
 */
public class nozHashMapConversion {
    
    public static void main(String[] args) throws ClassNotFoundException{
        HashMap map = null;
        
        FileInputStream file = null;
        try {
            String filePath = "C:\\Users\\Nozomi\\Dropbox\\Nozomi - Dani\\RBES SMAP for IEEEAero14\\results\\revtimes.dat";
            file = new FileInputStream( filePath );
            ObjectInputStream is = new ObjectInputStream( file );
            map = (HashMap)is.readObject();
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(nozHashMapConversion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(nozHashMapConversion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(nozHashMapConversion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        HashMap<String,Object> newMap = new HashMap();
         Set<Map.Entry<String,Object>> entries = map.entrySet();
         Iterator<Map.Entry<String,Object>> iter = entries.iterator();
         String oldKey;
         while(iter.hasNext()){
            oldKey = iter.next().getKey();
            String newKey = new String();
            newKey = Character.toString(oldKey.charAt(0))+Character.toString(oldKey.charAt(2))+Character.toString(oldKey.charAt(4));
            
            for(int i=5;i<oldKey.length();i++){
                if(Character.toString(oldKey.charAt(i)).equals(" ")&&!Character.toString(oldKey.charAt(i-1)).equals(" "))
                    newKey = newKey + ",";
                else if(!Character.toString(oldKey.charAt(i)).equals(" ")) 
                    newKey = newKey + Character.toString(oldKey.charAt(i));
                    
            }
            
            newMap.put(newKey, map.get(oldKey));
         }
         
         try {
            FileOutputStream saveFile = new FileOutputStream("C:\\Users\\Nozomi\\Dropbox\\Nozomi - Dani\\RBES SMAP for IEEEAero14\\results\\revtimesNew.dat" );
            ObjectOutputStream os = new ObjectOutputStream( saveFile );
            os.writeObject( newMap );
            os.close();
            file.close();
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }
}
