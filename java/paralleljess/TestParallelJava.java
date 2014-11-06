/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paralleljess;

/**
 *
 * @author dani
 */
import matlabcontrol.*;
import jess.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

public class TestParallelJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            MatlabProxyFactory mf = new MatlabProxyFactory();
            MatlabProxy mp = mf.getProxy();
            
            mp.eval("RBES_f('C:\\Users\\dani\\Documents\\My Dropbox\\EOCubesats\\RBES_Cubesats7');");
            /*mp.eval("r = global_jess_engine;");
            mp.eval("os = java.io.ByteArrayOutputStream;");
            mp.eval("r.bsave(os);");
            Rete r = (Rete)mp.getVariable("r");*/
            Object rete_object = mp.returningEval( "global_jess_engine", 1 );
            Rete r = (Rete)rete_object;
            
            mp.eval("[string_cell] = init_test_parallel_jess");
            java.lang.String[] o = (java.lang.String[])mp.getVariable("string_cell");
            
            ParallelJess pj = new ParallelJess(r,o.length);
            pj.evaluatePopulation(o);
            
        } catch(Exception e) {
        System.out.printf(e.getMessage());}
    }
}
