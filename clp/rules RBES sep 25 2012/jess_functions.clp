;; **************************************************
;; FUNCTIONS TO HANDLE LISTS
;; **************************************************

(deffunction last$ (?list)
    (return (nth$ (length$ ?list) ?list))
    )

(deffunction del-element$ (?list ?elem)
    (bind ?index (member$ ?elem ?list))
    (return (delete$ ?list ?index ?index))
    )

(deffunction add-element$ (?list ?elem)
    (return (insert$ ?list (+ 1 (length$ ?list)) ?elem))
    )

(deffunction find$ (?elem ?list)
    (bind ?indexes (create$ )) (bind ?n 1)
    (for (bind ?i 1) (<= ?i (length$ ?list)) (++ ?i) 
        (if (eq ?elem (nth$ ?i ?list)) then 
            (bind ?indexes (insert$ ?indexes ?n ?i)) (bind ?n (+ ?n 1)))
        )
    (return ?indexes)
    )

(deffunction .*$ (?x ?y)
    ;(printout t " call .*$ x = " ?x " y = " ?y crlf) 
    (if (not (and (listp ?x) (listp ?y))) then 
        (if (and (numberp ?x) (numberp ?y)) then (return (* ?x ?y)) else (return 0))) 
    (bind ?z (create$ )) 
    (for (bind ?i 1) (<= ?i (length$ ?x)) (++ ?i) 
        (bind ?tmp (* (nth$ ?i ?x) (nth$ ?i ?y))) (bind ?z (insert$ ?z ?i ?tmp))) 
    (return ?z))

(deffunction .+$ (?x ?y)
    ;(printout t " call .+$ x = " ?x " y = " ?y crlf)
    ;(printout t " x is list = " (listp ?x) " y is list = " (listp ?y) crlf)  
    (if (not (and (listp ?x) (listp ?y))) then 
        (if (numberp ?x) then (return (+ ?x ?y)) else (return 0)))
    ;(printout t "went past " crlf) 
    (bind ?z (create$ )) 
    (for (bind ?i 1) (<= ?i (length$ ?x)) (++ ?i) 
        (bind ?tmp (+ (nth$ ?i ?x) (nth$ ?i ?y))) (bind ?z (insert$ ?z ?i ?tmp)))
    ;(printout t " result = " ?z crlf) 
    (return ?z))

(deffunction sum$ (?list)
    (if (not (listp ?list)) then (if (numberp ?list) then (return ?list) else (return 0))) 
    (if (eq (length$ ?list) 1) then (return (nth$ 1 ?list))
        else (return (+ (nth$ 1 ?list) (sum$ (rest$ ?list))))) 
    )

(deffunction *$ (?list)
    (if (not (listp ?list)) then (if (numberp ?list) then (return ?list) else (return 0))) 
    (if (eq (length$ ?list) 1) then (return (nth$ 1 ?list))
        else (return (* (nth$ 1 ?list) (*$ (rest$ ?list))))) 
    )

; this other implementation is simpler and also works, but perhaps less efficient
;(deffunction max$ (?list)
;    (return (eval (str-cat "max " (implode$ ?list))))
;    )

(deffunction max$ (?list)
    ;(printout t "max$ " ?list " is list " crlf)
    (if (not (listp ?list)) then (if (numberp ?list) then (return ?list) else (return 0))) 
    (if (eq (length$ ?list) 0) then (return 0))
    (bind ?car (nth$ 1 ?list))
    (if (eq (length$ ?list) 1) then (return ?car)
        else (return (max ?car (max$ (rest$ ?list)))))  
    )

(deffunction dot-product$ (?x ?y) 
    (return (sum$ (.*$ ?x ?y))))

(deffunction isempty$ (?list)
    (return (eq (length$ ?list) 0)))

(deffunction create-list-n$ (?n)
    (bind ?list (create$ ))
    (for (bind ?i 1) (<= ?i ?n) (++ ?i)
        (bind ?list (insert$ ?list ?i 0)))
    (return ?list))

(deffunction find-bin (?op ?val ?thr)
    "This function finds the interval within a set of intervals specified by their thresholds
    ?thr (ordered from best to worst) to which a number ?val belongs"
    
    (bind ?n 1)
    (while (<= ?n (length$ ?thr)  )
        ;(printout t "n = "?n " op = "?op " val = " ?val " thr = " (nth$ ?n ?thr) crlf)
        (if (apply ?op ?val (nth$ ?n ?thr)) then (return ?n))
        (++ ?n)
        )
    ;(if (> ?n (length$ ?thr)) then (printout 
     ;       t "find-bind did not find any matching bin for val = " ?val " thr = " ?thr crlf))
	(return ?n)
    )

(deffunction find-bin-txt (?val ?thr)
    "This function finds the interval within a set of intervals specified by their thresholds
    ?thr (ordered from best to worst) to which a number ?val belongs. It works for text values
    and thresholds"

	(return (find-bin "eq" ?val ?thr))
    )

(deffunction find-bin-num (?val ?thr)
    "This function finds the interval within a set of intervals specified by their thresholds
    ?thr (ordered from best to worst) to which a number ?val belongs"
    
    (if (>= (nth$ 1 ?thr) (nth$ (length$ ?thr) ?thr)) then 
        (bind ?op ">=") else (bind ?op "<="))
    (return (find-bin ?op ?val ?thr))
    )

(deffunction no-nils (?list)
    (foreach ?el ?list
        (if (< ?el 0) then (return FALSE)))
    ;(printout t " no-nils authorized for " ?list crlf)
    (return TRUE)
    )
;; **************************************************
;; MISCELLANEOUS ENGINEERING FUNCTIONS
;; **************************************************

(deffunction dB-to-lin (?dB)
    (return (** 10 (/ ?dB 10)))
    )

(deffunction lin-to-dB (?lin)
    (return (* 10 (log10 ?lin)))
    )

(deffunction sin (?x)
    (return (matlabf sin (* ?x (pi) (/ 1 180))))
    )