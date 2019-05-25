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

(def ordered-coins [P N D Q])
(def inv-ordered-coins (reverse ordered-coins))

(def coin-set #{P N D Q})

(defn next-coin
  [curr-coin]
  (some #(when (> % curr-coin) %) ordered-coins))

(defn prev-coin
  [curr-coin]
  (some #(when (< % curr-coin) %) inv-ordered-coins))

(defn amt->change "Returns optimal coin change from specific amount.
  Can return self coin."
  ([amount]
   (amt->change amount []))

  ([amount acc]
   (cond
     (>= (- amount Q) 0) (amt->change (- amount Q) (conj acc Q))
     (>= (- amount D) 0) (amt->change (- amount D) (conj acc D))
     (>= (- amount N) 0) (amt->change (- amount N) (conj acc N))
     (>= (- amount P) 0) (amt->change (- amount P) (conj acc P))
     :else acc)))

(defn amt->change-except [amount except-coin]
  )

(defn unexact-change? [amount coin]
  (and

   (not (some nil? [amount coin]))

   (not= amount coin)
   (>= (- amount coin) 0)))

(defn is-penny? [amt]
  (= amt P))

(defn all-pennies? [purse]
  (every? is-penny? purse))


;; [25 25 10 5 1 1 1]
;; for each, call fn
;; if 1, we're done: return that
;; if self, go to next amount for cond
;; next amount, call conversion:
;; generates a vector for example for 25: [10 10 5]
;; map call again this vector.

(defn change-but
  ([purse]
   (println "first default call with missing args")
   (change-but purse (first purse) []))
  ([purse curr-coin acc]
   (println "change-but args" purse curr-coin acc)
   (if (all-pennies? purse)
     purse
     (for [coin purse]
       (do
         (println "coin" coin)
         (cond
           (is-penny? coin) P

           (unexact-change? curr-coin coin) (do
                                              (println "unexact change")
                                              (change-but (amt->change (- curr-coin coin)) coin (conj acc coin))) ;; here call change-but on res from amt->change

           (coin-set coin) (change-but purse (prev-coin coin) acc)

           ;; other conditions here.. revise conditions above...

           ;; (>= (- amount P) 0) (amt->change (- amount P) (conj acc P))
           :else acc))))))

(defn decide
  [purse curr-coin acc]
  (for [coin purse]
    (cond
      (= coin P) P
      true 3
      ;; (= coin curr-coin) (change-but purse (prev-coin coin) acc)
      (unexact-change? curr-coin coin) (amt->change (- curr-coin coin) (conj acc coin)) ;; here call change-but on res from amt->change

      ;; (>= (- amount P) 0) (amt->change (- amount P) (conj acc P))
      :else acc)))

;; I have coins in my [] purse
;; [25 5 1] ;; a Quarter, a Nickel, and a Penny
;; if not a P, but a coin, can be deconstructed into more coins
;; for each coin, convert into coin array w amt->change
