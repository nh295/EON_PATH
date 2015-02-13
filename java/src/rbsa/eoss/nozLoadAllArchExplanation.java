/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rbsa.eoss;

import java.util.ArrayList;
import java.util.Stack;
import rbsa.eoss.local.Params; 

/**
 *
 * @author Nozomi
 */
public class nozLoadAllArchExplanation {
    public static void main(String[] args){
//        String path  = "C:\\Users\\SEAK1\\Dropbox\\Nozomi - Dani\\EON_PATH";//RBES SMAP for IEEEAero14 code
        String path  = "C:\\Users\\Nozomi\\Dropbox\\Nozomi - Dani\\EON_PATH";//RBES SMAP for IEEEAero14 code
        ArchitectureEvaluator AE = ArchitectureEvaluator.getInstance();
        ArchTradespaceExplorer ATE = ArchTradespaceExplorer.getInstance();
        ResultManager RM = ResultManager.getInstance();
        Params params = new Params( path, "CRISP-ATTRIBUTES", "test","normal","search_heuristic_rules_smap_2");
        String filename = "2014-10-12_17-53-53_test.rs";
        ResultCollection resCol = RM.loadResultCollectionFromFile(path+"\\results\\"+filename);
        
        AE.init(3);
        Stack<Result> res = resCol.getResults();
        ArrayList<Architecture> arrRes = new ArrayList();
        for(Result r:res){
            arrRes.add(r.getArch());
        }
        AE.setPopulation(arrRes);
        AE.evaluatePopulation();
        
        ResultCollection loadedResCol = new ResultCollection(AE.getResults());
        RM.saveResultCollection(loadedResCol);
        AE.clear();
    }
}
