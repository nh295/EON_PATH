function ret1 = create_satisfaction_table(result)
 
    global zeResult;
    global params
    global AE
    exp = result.getExplanations;
    subobj_scores = result.getSubobjective_scores2;
    it = exp.keySet.iterator;
    ret1 = {'Subobjective' 'Parameter' 'Score' 'Taken by'};
    while it.hasNext
        subobj = it.next;
        score = subobj_scores.get(subobj);
        [ret,ret2] = capa_vs_req_from_explanation_field(result,subobj,AE,params);
        ret1 = [ret1;ret2];
    end
end