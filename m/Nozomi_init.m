function [AE, params] = Nozomi_init(folder)
    cd(folder);
    javaaddpath('.\java\jess.jar');
    javaaddpath('.\java\jxl.jar');
    javaaddpath('./java/combinatoricslib-2.0.jar');
    javaaddpath('./java/commons-lang3-3.1.jar');
    javaaddpath('./java/matlabcontrol-4.0.0.jar');
    javaaddpath( '.\java\EON_PATH.jar' );
    import rbsa.eoss.*
    import rbsa.eoss.local.*
    import java.io.*;
    params = Params(folder,'CRISP-ATTRIBUTES','test','normal','');
    AE = ArchitectureEvaluator.getInstance;
end
