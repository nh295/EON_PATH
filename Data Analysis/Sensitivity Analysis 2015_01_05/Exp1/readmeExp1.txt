EON_PATH Sensitivity Experiment 1

Matrix: A1 B1 C1 D1

Panel Weights:
WEA:	10/15
CLI:	 2/15
AERO:	 1/15
AGRO:	 1/15
DIS:	 1/15

(deffunction get-bus-cost(?nunits)
(if(eq ?nunits 1U) then (return 0.6))
(if(eq ?nunits 3U) then (return 2.0))
(if(eq ?nunits 6U) then (return 4.0))
(if(eq ?nunits 12U) then (return 5.0))
(return 100.0)
)

(deffunction get-bus-lifetime (?nunits)
(if(eq ?nunits 1U) then (return 2.0))
(if(eq ?nunits 3U) then (return 2.0))
(if(eq ?nunits 6U) then (return 4.0))
(if(eq ?nunits 12U) then (return 4.0))
(if(eq ?nunits 100U) then (return 10.0))
(throw new Exception "CUBESAT-COST: unknown bus size in get-bus-lifetime")
)