(ns a4clojure.core)

(defn groupy
  "Implements group-by. See (doc group-by)"
  [f v]
  (loop [in v
         acc {}]
    (let [item (first in)]
      (if item
        (let [result (f item)]
          (recur (rest in)
                 (assoc acc result
                        (conj (or (get acc result)
                                  [])
                              item))))
        acc))))

;; other's solution
(fn [f d]
  (loop [data d accum {}]
    (if (empty? data) accum
        (recur (rest data)
               (merge-with concat accum
                           {(f (first data)) (vector (first data))} )))))
