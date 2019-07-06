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

;; ----------------

;; (defn has-two-leaves? [st] nil)

;; nil or coll
(defn valid-leaves? [leaves]
  (and (every? #(or (coll? %) (nil? %)) leaves)
       (= (count leaves) 2)))

(def invalid-leaves? (complement valid-leaves?))

(defn tree?
  [t]
  (let [[value & leaves] t]
    (cond
      (or (nil? value) (invalid-leaves? leaves)) false
      (every? nil? leaves) true
      :else  (every? true? (map tree? (filter coll? leaves))))))
