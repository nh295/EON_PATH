function data = create_cost_table

    global zeResult;
    global r
    c = r.getGlobalContext;
    
    data = cell(100,10);

    % Cost model
    % [num,txt,raw] = xlsread(params.master_xls,'Output');
    % headers = txt(:,1);
    % Cost model
    % Ground segment cost
    data(1,1:8) = {'Mission', 'Replenishment', 'Bus','Num sat','Payload cost','Bus cost','Launch cost','Total'};

    missions = zeResult.getOther_facts;
    n = 2;
    

    for i = 1:missions.size
        mission = missions.get(i-1);
        data(n,1) = {char(mission.getSlotValue('orbit-string').stringValue(c))};
        data(n,2) = {mission.getSlotValue('replenishment-factor#').floatValue(c)};
        data(n,3) = {char(mission.getSlotValue('bus').stringValue(c))};
        data(n,4) = {mission.getSlotValue('num-of-sats-per-plane#').floatValue(c)};
        data(n,5) = {mission.getSlotValue('payload-cost#').floatValue(c)};
        data(n,6) = {mission.getSlotValue('bus-cost#').floatValue(c)};
        data(n,7) = {mission.getSlotValue('launch-cost#').floatValue(c)};
        data(n,8) = {mission.getSlotValue('mission-cost#').floatValue(c)};
        n=n+1;
    end
    
    data(n,1) = {'Total Cost'};
    data(n,8)={sum(cell2mat(data(2:end,8)))};
    
end