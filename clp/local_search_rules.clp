(defrule LOCAL-SEARCH::remove-duplicate-architectures
(MANIFEST::ARCHITECTURE (id ?id) (num-sats-per-plane ?nsats) (bitString ?bits))
?dupl <- (MANIFEST::ARCHITECTURE (num-sats-per-plane ?nsats) (bitString ?bits) (id ~?id))
=>
(retract ?dupl))


