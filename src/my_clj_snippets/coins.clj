(ns my-clj-snippets.coins)

(def P 1)
(def N 5)
(def D 10)
(def Q 25)
(def U 100)

(def change {
             :P [P]
             :N [[P P P P P] [N]]
             :D [[D] [N N]]
             :Q [[Q] [D D N]]
             :U [[Q Q Q Q]] ;; maybe?
             })

(defn amt->change ""
  ([amount]
   (amt->change amount []))

  ([amount acc]
   (cond
     (>= (- amount Q) 0) (amt->change (- amount Q) (conj acc Q))
     (>= (- amount D) 0) (amt->change (- amount D) (conj acc D))
     (>= (- amount N) 0) (amt->change (- amount N) (conj acc N))
     (>= (- amount P) 0) (amt->change (- amount P) (conj acc P))
     :else acc)))

(defn change-but
  [amt]
  ;; (loop [amount amt
  ;;        acc []]
  ;;   )
  nil
  )

(defn are-all-pennies? [in]
  (and (= (first in) P) (apply = in)))

(defn all-combinations [in-v]
    ;; else
    ;; for each vector entry...
    ;;   if typeof children  is vector
    ;;     map through all-combinations again
    ;;   if its a number, we need to convert to coins,

    ;; (map amt->change-2 in-v
  nil)

