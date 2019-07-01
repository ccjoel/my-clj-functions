(ns a4clojure.core)

(defn groupy
  "Implements group-by. See (doc group-by)"
  [fn v]
  (loop [item (first v)
         acc {}]
    (if item
      (let [result (fn item)]
        (recur (rest v)
               (assoc acc result
                      (conj (or (get acc result)
                                [])
                            item))))
      acc)))
