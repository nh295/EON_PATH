/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paralleljess;

/**
 *
 * @author dani
 */
import java.io.ByteArrayInputStream;
import java.util.HashMap;

public class ResourcePool {
    public static HashMap<Integer,Resource> pool;
    public static ByteArrayInputStream rete0;
    
    public ResourcePool(ByteArrayInputStream is, int nCPU) {
        rete0 = is;
        pool = new HashMap<Integer,Resource>();
        for (int i = 0;i<nCPU;i++) {
            Resource res = new Resource(i);
            
            pool.put(i, res);
        }
    }
    
    public static synchronized void freeResource (int i) {
        Resource res = pool.get(i);
        res.setFree(true);
    }
    
    public static synchronized Resource getResource () {
        Resource theres = null;
        for (int i=0;i<pool.size();i++) {
            Resource res = pool.get(i);
            if (res.getFree()) {
                res.setFree(false);
                theres = res;
                break;
            }
        }
        return theres;
    }
}
