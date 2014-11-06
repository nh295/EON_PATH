/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rbsa.eoss.local;

/**
 *
 * @author dani
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.TreeMap;
public class Params {
    //public static String master_xls;
    //public static boolean recompute_scores;
    
    public static String path;// used
    public static String req_mode;//used
    public static String name;//used
    public static String run_mode;//used
    public static String initial_pop;
    public static String template_definition_xls;// used
    public static String element_database_xls;
    public static String instrument_capability_xls;// used
    public static String attribute_inheritance_rules_xls;
    public static String mission_analysis_database_xls;// used
    public static String value_aggregation_xls;
    public static String requirement_satisfaction_xls;
    public static String aggregation_xls;
    public static String capability_rules_xls;//used
    
    public static String module_definition_clp;// used
    public static String template_definition_clp;// used
    public static String[] functions_clp = new String[2];// used
    public static String assimilation_rules_clp;// used
    public static String aggregation_rules_clp;// used
    public static String fuzzy_aggregation_rules_clp;//used
    public static String jess_queries_clp;
    public static String enumeration_rules_clp;
    public static String manifest_rules_clp;
    public static String database_completeness_clp;
    public static String attribute_inheritance_clp;
    public static String orbit_rules_clp;
    public static String eps_design_rules_clp;
    public static String adcs_design_rules_clp;
    public static String propulsion_design_rules_clp;
    public static String capability_rules_clp;
    public static String satisfaction_rules_clp;
    public static String demand_rules_clp;
    public static String synergy_rules_clp;//Used
    public static String explanation_rules_clp;//Used
    public static String fuzzy_attribute_clp;
    public static String value_aggregation_clp;
    public static String requirement_satisfaction_clp;
    public static String cost_estimation_rules_clp; // used
    public static String fuzzy_cost_estimation_rules_clp; //used
    public static String mass_budget_rules_clp;
    public static String subsystem_mass_budget_rules_clp;
    public static String deltaV_budget_rules_clp;
    public static String sat_configuration_rules_clp;
    public static String launch_vehicle_selection_rules_clp;
    public static String standard_bus_selection_rules_clp;
    public static String links_rules_clp;
    public static String RF_spectrum_rules_clp;
    public static String scheduler_rules_clp;
    public static String paths_rules_clp;
    public static String search_heuristic_rules_clp;
    public static String down_selection_rules_clp;
    public static String adhoc_rules_clp;
    
    // Metrics for utility and pareto calculations
    public static ArrayList<String> pareto_metrics;
    public static ArrayList<String> pareto_metric_types;
    public static int pareto_ranking_depth;
    public static int pareto_ranking_threshold;
    public static ArrayList<String> util_metrics;
    public static ArrayList<String> util_metric_types;
    public static ArrayList<Double> util_metric_weights;
    public static double min_science;
    public static double max_science;
    public static double min_cost;
    public static double max_cost;
    public static int num_improve_heuristics;
    public static double prob_accept;
    // Instruments
    public static String[] instrument_list = {"PATH_GEOSTAR","EON_50_1","EON_118_1","EON_183_1","EON_ATMS_1"};
    public static int ninstr;
    public static String[] orbit_list = {"GEO-35788-equat-NA","SSO-600-SSO-DD","LEO-600-ISS-NA","SSO-800-SSO-AM","SSO-800-SSO-PM"};
    public static int norb;
    public static HashMap instrument_indexes;
    public static HashMap orbit_indexes;
    public static int[] nsats = {1,2,3,4,5,6,7,8};
    
    // Results
    public static String path_save_results;
    
    
    // Intermediate results
    public static HashMap requirement_rules;
    public static HashMap measurements_to_subobjectives;
    public static HashMap measurements_to_objectives;
    public static HashMap measurements_to_panels;
    public static ArrayList parameter_list;
    public static ArrayList objectives;
    public static ArrayList subobjectives;
    public static HashMap instruments_to_measurements;
    public static HashMap instruments_to_subobjectives;
    public static HashMap instruments_to_objectives;
    public static HashMap instruments_to_panels;
    public static HashMap measurements_to_instruments;
    public static HashMap subobjectives_to_instruments;
    public static HashMap objectives_to_instruments;
    public static HashMap panels_to_instruments;
    public static HashMap subobjectives_to_measurements;
    public static HashMap objectives_to_measurements;
    public static HashMap panels_to_measurements;
    
    public static int npanels;
    public static ArrayList<Double> panel_weights;
    public static ArrayList<String> panel_names;
    public static ArrayList obj_weights;
    public static ArrayList<Integer> num_objectives_per_panel;
    public static ArrayList subobj_weights;
    public static HashMap subobj_weights_map;
    public static HashMap revtimes;
    public static HashMap scores;
    public static HashMap subobj_scores;
    public static HashMap capabilities;
    public static HashMap all_dsms;
    //Cubesat costs model
    public static HashMap instrument_masses;
    public static String capability_dat_file;
    public static String revtimes_dat_file;
    public static String dsm_dat_file;
    public static String scores_dat_file;
    public static HashMap<String,Double> instrument_costs;
    public static HashMap<String,Double> instrument_sizes;
    public static TreeMap<Double,Double> bus_costs;
    public static TreeMap<Double,Double> bus_lifetimes;
    public static HashMap<String,Double> launch_costs;
    public static double time_horizon;
    public static HashMap<String,Double> lifetimes;
    
    public Params( String p , String mode, String name, String run_mode, String search_clp)
    {
        //this.master_xls = master_xls;
        //this.recompute_scores = recompute_scores;
        this.path = p;
        this.req_mode = mode;
        this.name = name;
        this.run_mode = run_mode;
        path_save_results = path + "\\results";
        capability_dat_file = path_save_results + "\\capabilities-best.dat";
        revtimes_dat_file = path_save_results + "\\revtimes.dat";
        dsm_dat_file = path_save_results + "\\all_dsms2014-02-05-22-00-15.dat";//all_dsms2013-09-26-16-31-57
        scores_dat_file = path_save_results  + "\\scores2014-02-05-21-53-09.dat";
        initial_pop = path_save_results + "\\2014-07-07_04-49-24_test.rs";//path_save_results + "\\2014-04-08_10-19-15_test.rs"
        //initial_pop = "";
        // Paths for common xls files
        template_definition_xls = path + "\\xls\\AttributeSet.xls";//used
        mission_analysis_database_xls = path + "\\xls\\Mission Analysis Database.xls";//used
        capability_rules_xls = path + "\\xls\\eon instrument capability definition.xls";//used
        requirement_satisfaction_xls = path + "\\xls\\EON Requirement Rules.xls";//used
        aggregation_xls = path + "\\xls\\eon aggregation rules.xls";//used
        element_database_xls = path + "\\xls\\database.xls";
        attribute_inheritance_rules_xls = path + "\\xls\\attribute_inheritance_rules_xls.xls";
        
        value_aggregation_xls = path + "\\xls\\EON Aggregation Rules.xls";
        
        
        // Paths for common clp files
        module_definition_clp        = path + "\\clp\\modules.clp";//used
        template_definition_clp      = path + "\\clp\\templates.clp";//used
        functions_clp[0]           = path + "\\clp\\jess_functions.clp";//used
        functions_clp[1]           = path + "\\clp\\functions.clp";//used
        assimilation_rules_clp       = path + "\\clp\\assimilation_rules.clp";//used
        aggregation_rules_clp       = path + "\\clp\\aggregation_rules.clp";//used
        fuzzy_aggregation_rules_clp = path + "\\clp\\fuzzy_aggregation_rules.clp";//used
        jess_queries_clp             = path + "\\clp\\queries.clp";//Absent in SMAP
        enumeration_rules_clp        = path + "\\clp\\enumeration_rules.clp";
        manifest_rules_clp           = path + "\\clp\\manifest_rules.clp";
        database_completeness_clp    = path + "\\clp\\database_completeness_rules.clp";
        attribute_inheritance_clp    = path + "\\clp\\attribute_inheritance_rules.clp";
        orbit_rules_clp              = path + "\\clp\\orbit_rules.clp";
        capability_rules_clp         = path + "\\clp\\capability_rules.clp";
        satisfaction_rules_clp       = path + "\\clp\\satisfaction_rules.clp";
        demand_rules_clp             = path + "\\clp\\demand_rules.clp";
        synergy_rules_clp            = path + "\\clp\\synergy_rules.clp";//Used
        explanation_rules_clp        = path + "\\clp\\explanation_rules.clp";//Used
        fuzzy_attribute_clp          = path + "\\clp\\fuzzy_attribute_rules.clp";
        value_aggregation_clp        = path + "\\clp\\requirement_rules.clp";
        requirement_satisfaction_clp = path + "\\clp\\aggregation_rules.clp";
        cost_estimation_rules_clp    = path + "\\clp\\cubesat_cost_rules.clp"; //Used
        fuzzy_cost_estimation_rules_clp = path + "\\clp\\fuzzy_cubesat_cost_rules.clp"; //Used
        mass_budget_rules_clp        = path + "\\clp\\mass_budget_rules.clp";
        subsystem_mass_budget_rules_clp = path + "\\clp\\subsystem_mass_budget_rules.clp";
        deltaV_budget_rules_clp = path + "\\clp\\deltaV_budget_rules.clp";
        adcs_design_rules_clp        = path + "\\clp\\adcs_design_rules.clp";
        propulsion_design_rules_clp        = path + "\\clp\\propulsion_design_rules.clp";
        eps_design_rules_clp        = path + "\\clp\\eps_design_rules.clp";
        sat_configuration_rules_clp  = path + "\\clp\\sat_configuration_rules.clp";
        launch_vehicle_selection_rules_clp  = path + "\\clp\\launch_cost_estimation_rules.clp";
        standard_bus_selection_rules_clp    = path + "\\clp\\standard_bus_selection_rules.clp";
        if(search_clp.isEmpty())
            search_heuristic_rules_clp   = path + "\\clp\\search_heuristic_rules_smap_improveOrbit.clp";
        else
            search_heuristic_rules_clp   = path + "\\clp\\" + search_clp + ".clp";
        
        down_selection_rules_clp     = path + "\\clp\\down_selection_rules_smap.clp";
        adhoc_rules_clp              = path + "\\clp\\smap_rules_sensitivity.clp";
      
        // Metrics for utility and pareto calculations
        pareto_metrics = new ArrayList<String>();
        pareto_metrics.add( "lifecycle_cost" );
        pareto_metrics.add( "benefit" );
        pareto_metric_types = new ArrayList<String>();
        pareto_metric_types.add( "SIB" );
        pareto_metric_types.add( "LIB" );
        util_metrics = new ArrayList<String>();
        util_metrics.add( "lifecycle_cost" );
        util_metrics.add( "benefit" );
        util_metric_types = new ArrayList<String>();
        util_metric_types.add( "SIB" );
        util_metric_types.add( "LIB" );
        util_metric_weights = new ArrayList<Double>();
        util_metric_weights.add( 0.5 );
        util_metric_weights.add( 0.5 );
        prob_accept = 0.8;
        // Instruments  & Orbits

        //instrument_list[0] = "SMAP_ANT";
        ninstr = instrument_list.length;
        norb = orbit_list.length;
        instrument_indexes = new HashMap<String,Integer>();
        orbit_indexes= new HashMap<String,Integer>();
        for (int j = 0;j<ninstr;j++)
            instrument_indexes.put(instrument_list[j], j);
        for (int j = 0;j<norb;j++)
            orbit_indexes.put(orbit_list[j], j);
        // Intermediate results
        measurements_to_subobjectives = new HashMap();
        measurements_to_objectives = new HashMap();
        measurements_to_panels = new HashMap();
        objectives = new ArrayList();
        subobjectives = new ArrayList();
        instruments_to_measurements = new HashMap();
        instruments_to_subobjectives = new HashMap();
        instruments_to_objectives = new HashMap();
        instruments_to_panels = new HashMap();
        
        measurements_to_instruments = new HashMap();
        subobjectives_to_instruments = new HashMap();
        objectives_to_instruments = new HashMap();
        panels_to_instruments = new HashMap();
    
        subobjectives_to_measurements = new HashMap();
        objectives_to_measurements = new HashMap();
        panels_to_measurements = new HashMap();
        
        instrument_sizes = new HashMap<String,Double>();//{"PATH_GEOSTAR","EON_50_1","EON_118_1","EON_183_1"};
        instrument_sizes.put("PATH_GEOSTAR",100.0);
        instrument_sizes.put("EON_183_1",0.5);
        instrument_sizes.put("EON_118_1",0.5);
        instrument_sizes.put("EON_50_1",0.5);
        instrument_sizes.put("EON_23_31",0.5);
        instrument_sizes.put("EON_90_165_225",0.5);
        instrument_sizes.put("EON_ATMS_1",2.0);
        
        instrument_costs = new HashMap<String,Double>();//{"PATH_GEOSTAR","EON_50_1","EON_118_1","EON_183_1"};
        instrument_costs.put("PATH_GEOSTAR",200.0);
        instrument_costs.put("EON_183_1",1.0);
        instrument_costs.put("EON_118_1",1.0);
        instrument_costs.put("EON_50_1",1.0);
        instrument_costs.put("EON_23_31",1.0);
        instrument_costs.put("EON_90_165_225",1.0);
        instrument_costs.put("EON_ATMS_1",4.0);
        
        bus_costs = new TreeMap<Double,Double>();//Number of Cubesats units - $M
        bus_costs.put(1.0,0.3);
        bus_costs.put(2.0,0.7);
        bus_costs.put(3.0,1.0);
        bus_costs.put(4.0,2.0);
        bus_costs.put(6.0,3.0);
        bus_costs.put(12.0,4.0);
        bus_costs.put(100.0,100.0);
        
        bus_lifetimes = new TreeMap<Double,Double>();//lifetime wrt Cubesats units - yrs
        bus_lifetimes.put(0.5,1.0);
        bus_lifetimes.put(1.0,1.0);
        bus_lifetimes.put(1.5,1.0);
        bus_lifetimes.put(2.0,1.0);
        bus_lifetimes.put(2.5,1.0);
        bus_lifetimes.put(3.0,1.0);
        bus_lifetimes.put(3.5,1.0);
        bus_lifetimes.put(4.0,1.0);
        bus_lifetimes.put(4.5,1.0);
        bus_lifetimes.put(6.0,1.0);
        bus_lifetimes.put(12.0,3.0);
        bus_lifetimes.put(100.0,3.0);
        
        launch_costs = new HashMap<String,Double>();
        launch_costs.put("GEO-35788-equat-NA",100.0);
        launch_costs.put("LEO-400-ISS-NA",5.0);
        launch_costs.put("SSO-600-SSO-DD",5.0);
        launch_costs.put("SSO-800-SSO-PM",5.0);
        launch_costs.put("SSO-800-SSO-AM",5.0);
        launch_costs.put("LEO-600-ISS-NA",5.0);
        
        time_horizon = 3.0;
        lifetimes = new HashMap<String,Double>();
        lifetimes.put("GEO-35788-equat-NA",15.0);
        lifetimes.put("LEO-400-ISS-NA",0.5);
        lifetimes.put("SSO-600-SSO-DD",15.0);
        lifetimes.put("SSO-800-SSO-PM",15.0);
        lifetimes.put("SSO-800-SSO-AM",15.0);
        lifetimes.put("LEO-600-ISS-NA",15.0);
        
        try {
            if(!run_mode.equalsIgnoreCase("update_revtimes")) {
                FileInputStream fis = new FileInputStream(revtimes_dat_file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                revtimes = (HashMap) ois.readObject();
                ois.close();
            }
            if(!run_mode.equalsIgnoreCase("update_capabilities")) {

                FileInputStream fis2 = new FileInputStream(capability_dat_file);
                ObjectInputStream ois2 = new ObjectInputStream(fis2);
                capabilities = (HashMap) ois2.readObject();
                ois2.close();
            }
            if(!run_mode.equalsIgnoreCase("update_dsms")) {
                FileInputStream fis3 = new FileInputStream(dsm_dat_file);
                ObjectInputStream ois3 = new ObjectInputStream(fis3);
                all_dsms = (HashMap) ois3.readObject();
                ois3.close();
            }
            if(!run_mode.equalsIgnoreCase("update_scores")) {
                FileInputStream fis3 = new FileInputStream(scores_dat_file);
                ObjectInputStream ois3 = new ObjectInputStream(fis3);
                scores = (HashMap) ois3.readObject();
                subobj_scores = (HashMap) ois3.readObject();
                ois3.close();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       }
    public static String getConfiguration() {
        return "requirement_satisfaction_xls = " + requirement_satisfaction_xls + ";capability_rules_xls = " + capability_rules_xls;
    }

    public static String getName() {
        return name;
    }
    
}