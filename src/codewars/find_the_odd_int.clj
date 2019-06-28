(ns codewars.find-the-odd-int)

(defn find-odd [numbers]
  (->
   (->> numbers
        (group-by identity)
        (group-by #(->
                    %
                    val
                    count
                    odd?)))
   (get true)
   ffirst))

;; (def find-odd (partial reduce bit-xor))
;; (def find-odd (partial apply bit-xor 0))
;; (defn find-odd [v] (ffirst (filter #(odd? (second %)) (frequencies v))))

;; (->> xs
;;      frequencies
;;      (filter (comp odd? last))
;;      ffirst)

(defn count-num
  [num num-str]
  (count (re-seq (re-pattern (str " " num " ")) num-str)))

(defn vec->str
  [numbers]
  (str "  " (apply str (interleave numbers (repeat "  ")))))

(defn find-odd-str
  [numbers]
  (let [num-str (vec->str numbers)]
    (some #(when (odd? (count-num % num-str)) %)
          numbers)))
