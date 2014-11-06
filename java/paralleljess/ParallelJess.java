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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelJess {
    
    private ByteArrayInputStream is;
    private ResourcePool rp;
    private ThreadPoolExecutor tpe;
    private int capacity;
    
    public ParallelJess( Rete r0, int capacity )
    {   
        //System.out.println("ParallelJess INIT");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            r0.removeDefglobal("?*matlab*");
            r0.bsave(os);
            is = new ByteArrayInputStream(os.toByteArray());
            int nCPU = Runtime.getRuntime().availableProcessors();
            System.out.println("NCPU = " + nCPU);
            // Create the resource pool
            rp = new ResourcePool( is, nCPU );
            
            // Create the task manager
            this.capacity = capacity;
            BlockingQueue<Runnable> taskList = new ArrayBlockingQueue(capacity);
            tpe = new ThreadPoolExecutor(capacity, capacity, 0, TimeUnit.MILLISECONDS, taskList);
            
        } catch (Exception e) {System.out.println("Except: " + e.getMessage());};
       
    }
    
    public void evaluatePopulation( String[] population ) {
        
        // Add the tasks to the exectute
        for( int i = 0; i < capacity; i++ ) {
            Task t = new Task( population[i] );
            tpe.execute(t);
        }
        
    }
            
}
