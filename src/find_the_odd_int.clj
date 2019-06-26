(ns find-the-odd-int)

(defn count-num
  [num num-str]
  (count (re-seq (re-pattern (str " " num " ")) num-str)))

(defn vec->str
  [numbers]
  (str "  " (apply str (interleave numbers (repeat "  ")))))

(defn find-odd
  [numbers]
  (let [num-str (vec->str numbers)]
    (some #(when (odd? (count-num % num-str)) %)
          numbers)))
