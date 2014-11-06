package paralleljess;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dani
 */
import jess.*;
import matlabcontrol.*;
public class Task implements java.lang.Runnable {
    private String arch;
    public Task() {
        
    } 

    public Task(String a) {
        arch = a;
    }
    
    @Override
    public void run() {
    
        try {
            System.out.println("Task " + arch + " executing...");
            Resource res = ResourcePool.getResource();
            Rete r = res.getRete();
            r.bload(ResourcePool.rete0);
            res.linkJessToMatlab();
            MatlabProxy m = res.getMatlabProxy();
            System.out.println("hola");
            String call = "tmp = RBES_Evaluate_Cubesat_Manifest_str(" + arch + " ,params);";
            m.eval(call);
            ResourcePool.freeResource(res.getId()); 
        } catch (Exception e){System.out.println(e.getMessage());}
    }
}
