(defrule DATABASE::create-list-of-improve-heuristics
	?ret <- (SEARCH-HEURISTICS::improve-heuristic (id ?id))
	?f <- (SEARCH-HEURISTICS::list-improve-heuristics (list $?list) (num-heuristics ?n))
	=>
	(bind ?new-list (append$ $?list ?id))
	(modify ?f (list ?new-list) (num-heuristics (+ ?n 1)))
	(retract ?ret)
)
(defrule SEARCH-HEURISTICS::mutation-swap-one-bit 
    "This mutation function swaps the value of a single bit" 
	(declare (salience 100))
    ?arch0 <- (MANIFEST::ARCHITECTURE (bitString ?orig) (num-sats-per-plane ?ns) (mutate yes))
    =>
	;(printout t mutation-swap-one-bit crlf)
    (bind ?N 1)
    (for (bind ?i 0) (< ?i ?N) (++ ?i)
	    
		(bind ?arch ((new rbsa.eoss.Architecture ?arch0 ?ns) mutate1bit))
    	(assert-string (?arch toFactString)))
	(retract ?arch0)
    )
(deffacts DATABASE::assert-empty-list-of-improve-heuristics
(SEARCH-HEURISTICS::list-improve-heuristics (list (create$ )) (num-heuristics 0))
)


(defrule SEARCH-HEURISTICS::improve-orbit
    "This heuristic moves a random instrument to a better orbit" 
    ?arch0 <- (MANIFEST::ARCHITECTURE (bitString ?orig) (num-sats-per-plane ?ns) (improve improveOrbit))
    =>
	;(printout t improve-orbit crlf)
    (bind ?N 1)
    (for (bind ?i 0) (< ?i ?N) (++ ?i) 
		(bind ?arch ((new rbsa.eoss.Architecture ?orig ?ns) improveOrbit))
    	(assert-string (?arch toFactString)))
	(modify ?arch0 (improve no))
    )
	 
(deffacts DATABASE::add-improve-orbit-list-of-improve-heuristics
(SEARCH-HEURISTICS::improve-heuristic (id improveOrbit))
)

(defrule SEARCH-HEURISTICS::remove-existing-interference
    "This heuristic finds an existing interference between instruments and removes the necessary instrument" 
    ?arch0 <- (MANIFEST::ARCHITECTURE (bitString ?orig) (num-sats-per-plane ?ns) (improve removeInterference))
    =>
	;(printout t remove-existing-interference crlf)
    (bind ?N 1)
    (for (bind ?i 0) (< ?i ?N) (++ ?i) 
		(bind ?arch ((new rbsa.eoss.Architecture ?orig ?ns) removeInterference))
    	(assert-string (?arch toFactString)))
	(modify ?arch0 (improve no))
    )
	 
(deffacts DATABASE::add-remove-interf-list-of-improve-heuristics
(SEARCH-HEURISTICS::improve-heuristic (id removeInterference))
)

(defrule SEARCH-HEURISTICS::complete-missing-synergies
    "This heuristic finds a missing synergy and adds the necessary instrument" 
    ?arch0 <- (MANIFEST::ARCHITECTURE (bitString ?orig) (num-sats-per-plane ?ns) (improve addSynergy))
    =>
	;(printout t complete-missing-synergies crlf)
    (bind ?N 1)
    (for (bind ?i 0) (< ?i ?N) (++ ?i) 
		(bind ?arch ((new rbsa.eoss.Architecture ?orig ?ns) addSynergy))
    	(assert-string (?arch toFactString)))
	(modify ?arch0 (improve no))
    )
	
	(deffacts DATABASE::add-missing-synergies-list-of-improve-heuristics
(SEARCH-HEURISTICS::improve-heuristic (id addSynergy))
)

(defrule SEARCH-HEURISTICS::remove-superfluous-instrument
    "This heuristic finds an existing superfluous instruments and removes it" 
    ?arch0 <- (MANIFEST::ARCHITECTURE (bitString ?orig) (num-sats-per-plane ?ns) (improve removeSuperfluous))
    =>
	;(printout t remove-existing-interference crlf)
    (bind ?N 1)
    (for (bind ?i 0) (< ?i ?N) (++ ?i) 
		(bind ?arch ((new rbsa.eoss.Architecture ?orig ?ns) removeSuperfluous))
    	(assert-string (?arch toFactString)))
	(modify ?arch0 (improve no))
    )
	 
(deffacts DATABASE::add-remove-superfluous-list-of-improve-heuristics
(SEARCH-HEURISTICS::improve-heuristic (id removeSuperfluous))
)

(defrule SEARCH-HEURISTICS::add-random-instrument-to-small-sat
    "This heuristic finds an existing small satellite and adds a random instrument" 
    ?arch0 <- (MANIFEST::ARCHITECTURE (bitString ?orig) (num-sats-per-plane ?ns) (improve addRandomToSmallSat))
    =>
	;(printout t remove-existing-interference crlf)
    (bind ?N 1)
    (for (bind ?i 0) (< ?i ?N) (++ ?i) 
		(bind ?arch ((new rbsa.eoss.Architecture ?orig ?ns) addRandomToSmallSat))
    	(assert-string (?arch toFactString)))
	(modify ?arch0 (improve no))
    )
	 
(deffacts DATABASE::add-random-instrument-to-small-sat-list-of-improve-heuristics
(SEARCH-HEURISTICS::improve-heuristic (id addRandomToSmallSat))
)

(defrule SEARCH-HEURISTICS::remove-random-instrument-from-loaded-sat
    "This heuristic removes a random instrument from an existing loaded satellite" 
    ?arch0 <- (MANIFEST::ARCHITECTURE (bitString ?orig) (num-sats-per-plane ?ns) (improve removeRandomFromLoadedSat))
    =>
	;(printout t remove-existing-interference crlf)
    (bind ?N 1)
    (for (bind ?i 0) (< ?i ?N) (++ ?i) 
		(bind ?arch ((new rbsa.eoss.Architecture ?orig ?ns) removeRandomFromLoadedSat))
    	(assert-string (?arch toFactString)))
	(modify ?arch0 (improve no))
    )
	 
(deffacts DATABASE::add-remove-random-instrument-from-loaded-sat-list-of-improve-heuristics
(SEARCH-HEURISTICS::improve-heuristic (id removeRandomFromLoadedSat))
)

