global results archs params

narch = 1000;%length(archs);
xvals = zeros(narch,1);
yvals = zeros(narch,1);
    for i = 1:narch
        xvals(i) = results.get(i-1).getScience;
        yvals(i) = results.get(i-1).getCost;
    end 
[x_pareto, y_pareto, inds, ~ ] = pareto_front([xvals yvals] , {'LIB', 'SIB'});

pf_archs = archs(inds);
[a,~]=size(pf_archs);

%% num orbits
%first num is for sats with 0 orbits
%percent arch with n orbits
arch_n_orbit = zeros(a,1);
archs_with_n_orbit = zeros(9,1);
for i=1:narch
    norb = 1;
    for j = 1:length(params.orbit_list)
        tmp = archs{i}.getPayloadInOrbit(params.orbit_list(j));
        if tmp.length>0 == 1
            norb = norb + 1;
        end
    end
    arch_n_orbit(i)=norb;
    archs_with_n_orbit(norb) = archs_with_n_orbit(norb)+1;
end
percent_with_n_orbits = archs_with_n_orbit/narch;

%percent arch with n orbit on pf
n_orbits_on_pf = zeros(9,1);
for i=1:a
    norb = arch_n_orbit(inds(i));
    n_orbits_on_pf(norb) = n_orbits_on_pf(norb)+1;
end
percent_with_n_orbits_pf = n_orbits_on_pf./archs_with_n_orbit;

%percent arch on pf with n orbit 
pf_num_orbits = zeros(9,1);
for i=1:a
    norb = 1;
    for j = 1:length(params.orbit_list)
        tmp = pf_archs{i}.getPayloadInOrbit(params.orbit_list(j));
        if tmp.length>0 == 1
            norb = norb + 1;
        end
    end
    pf_num_orbits(norb) = pf_num_orbits(norb)+1;
end
percent_pf_with_n_orbits = pf_num_orbits./a;

%% num instrument
%first num is for sats with 0 instruments
%percent arch with n instruments
arch_n_inst = zeros(a,1);
archs_with_n_inst = zeros(9,1);
for i=1:narch
    ninst = 1;
    for j = 1:length(params.orbit_list)
        tmp = archs{i}.getPayloadInOrbit(params.orbit_list(j));
        ninst = ninst + tmp.length;
    end
    arch_n_inst(i)=ninst;
    archs_with_n_inst(ninst) = archs_with_n_inst(ninst)+1;
end
percent_with_n_inst = archs_with_n_inst/narch;

%percent arch with n inst on pf
n_inst_on_pf = zeros(9,1);
for i=1:a
    ninst = arch_n_inst(inds(i));
    n_inst_on_pf(ninst) = n_inst_on_pf(ninst)+1;
end
percent_with_n_inst_pf = n_inst_on_pf./archs_with_n_inst;

%percent arch on pf with n inst 
pf_num_inst = zeros(9,1);
for i=1:a
    ninst = 1;
    for j = 1:length(params.orbit_list)
        tmp = pf_archs{i}.getPayloadInOrbit(params.orbit_list(j));
        ninst = ninst + tmp.length;
    end
    pf_num_inst(ninst) = pf_num_inst(ninst)+1;
end
percent_pf_with_n_inst = pf_num_inst./a;

%% GEO vs no GEO

%percent arch with GEO
arch_with_GEO = zeros(a,1);
archs_with_GEO = 0;
for i=1:narch
    tmp = archs{i}.getPayloadInOrbit(params.orbit_list(1));
    if tmp.length>0
        arch_with_GEO(i)=1;
        archs_with_GEO = archs_with_GEO+1;
    end
end
percent_with_GEO = archs_with_GEO/narch;

%percent arch with GEO on pf
with_GEO_on_pf = 0;
for i=1:a
    if arch_with_GEO(inds(i))
        with_GEO_on_pf = with_GEO_on_pf+1;
    end
end
percent_with_GEO_pf = with_GEO_on_pf./archs_with_GEO;

%percent arch on pf with GEO  
pf_with_GEO = 0;
for i=1:a
    tmp = pf_archs{i}.getPayloadInOrbit(params.orbit_list(1));
    if tmp.length>0
        arch_with_GEO(i)=1;
        pf_with_GEO = pf_with_GEO+1;
    end
end
percent_pf_with_GEO = pf_with_GEO./a;

%% with combos of 50 + 118 + 183

%percent arch with 118 and 183
arch_with_50_183 = zeros(a,1);
arch_with_118_183 = zeros(a,1);
arch_with_50_118_183 = zeros(a,1);
arch_with_ATMS = zeros(a,1);
archs_with_50_183 = 0;
archs_with_118_183 = 0;
archs_with_50_118_183 = 0;
archs_with_ATMS = 0;

for i=1:narch
    has_50 = false;
    has_118 = false;
    has_183 = false;
    has_ATMS = false;
    for j = 1:length(params.orbit_list)
        tmp = archs{i}.getPayloadInOrbit(params.orbit_list(j));
        for k=1:tmp.length
            if strcmp(char(tmp(k)),'EON_50_1');
                has_50=1;
            end
            if strcmp(char(tmp(k)),'EON_118_1');
                has_118=1;
            end
            if strcmp(char(tmp(k)),'EON_183_1');
                has_183=1;
            end
            if strcmp(char(tmp(k)),'EON_ATMS_1');
                has_ATMS=1;
            end
        end
    end
    if has_50 && has_183
        arch_with_50_183(i)=1;
        archs_with_50_183 = archs_with_50_183 +1;
    end
    if has_118 && has_183
        arch_with_118_183(i)=1;
        archs_with_118_183 = archs_with_118_183 +1;
    end
    if has_ATMS
        arch_with_ATMS(i)=1;
        archs_with_ATMS = archs_with_ATMS +1;
    end
end
percent_with_50_183 = archs_with_50_183/narch;
percent_with_118_183 = archs_with_118_183/narch;
percent_with_ATMS = archs_with_ATMS/narch;

%percent arch with GEO on pf
with_50_183_on_pf = 0;
with_118_183_on_pf = 0;
with_ATMS_on_pf = 0;
for i=1:a
    if arch_with_50_183(inds(i))
        with_50_183_on_pf = with_50_183_on_pf+1;
    elseif arch_with_118_183(inds(i))
        with_118_183_on_pf = with_118_183_on_pf+1;
    elseif arch_with_ATMS(inds(i))
        with_ATMS_on_pf = with_ATMS_on_pf+1;
    end
end
percent_with_50_183_pf = with_50_183_on_pf/archs_with_50_183;
percent_with_118_183_pf = with_118_183_on_pf/archs_with_118_183;
percent_with_ATMS_pf = with_ATMS_on_pf/archs_with_ATMS;

%percent arch on pf with GEO  
pf_with_50_183 = 0;
pf_with_118_183 = 0;
pf_with_ATMS = 0;
for i=1:a
    tmp = pf_archs{i}.getPayloadInOrbit(params.orbit_list(1));
    has_50 = false;
    has_118 = false;
    has_183 = false;
    has_ATMS = false;
    for j = 1:length(params.orbit_list)
        tmp = pf_archs{i}.getPayloadInOrbit(params.orbit_list(j));
        for k=1:tmp.length
            if strcmp(char(tmp(k)),'EON_50_1');
                has_50=true;
            end
            if strcmp(char(tmp(k)),'EON_118_1');
                has_118=true;
            end
            if strcmp(char(tmp(k)),'EON_183_1');
                has_183=true;
            end
            if strcmp(char(tmp(k)),'EON_ATMS_1');
                has_ATMS=true;
            end
        end
    end
    if has_50 && has_183
        pf_with_50_183 = pf_with_50_183 +1;
    end
    if has_118 && has_183
        pf_with_118_183 = pf_with_118_183 +1;
    end
    if has_ATMS
        pf_with_ATMS = pf_with_ATMS +1;
    end
end

percent_pf_with_50_183 = pf_with_50_183/a;
percent_pf_with_118_183 = pf_with_118_183/a;
percent_pf_with_ATMS = pf_with_ATMS/a;