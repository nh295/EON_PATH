/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paralleljess;

/**
 *
 * @author dani
 */
import jess.*;
import matlabcontrol.*;
 
public class Resource {
   
    private int id;
    private boolean free;
    private Rete r;
    private MatlabProxyFactory mf;
    private MatlabProxy mp;
   
    public Resource( int id ) {
        this.id = id;
        free = true;
        r = new Rete();
        mf = new MatlabProxyFactory();
        try {
            mp = mf.getProxy();   
            mp.eval("load params;");
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }       
    }
   
    public int getId() {
        return id;
    }
   
    public void setId( int id ) {
        this.id = id;
    }
   
    public boolean getFree() {
        return free;
    }
   
    public void setFree( boolean free ) {
        this.free = free;
        
    }
   
    public Rete getRete() {
        return r;
    }
   
    public MatlabProxyFactory getMatlabProxyFactory() {
        return mf;
    }
   
    public MatlabProxy getMatlabProxy() {
        return mp;
    }
    
    public void linkJessToMatlab() {
        try {
            r.removeDefglobal("?*matlab*");
            r.addDefglobal(new Defglobal("?*matlab*",new Value(mp)));
        }catch(Exception e) {}    
    }
}