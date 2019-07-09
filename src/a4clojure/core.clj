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

(defn valid-leaves? [leaves]
  (and (every? #(or (coll? %) (nil? %)) leaves)
       (= (count leaves) 2)))

(def invalid-leaves? (complement valid-leaves?))

(defn tree?
  [t]
  (try
    (let [[value & leaves] t]
      (cond
        (or (nil? value) (invalid-leaves? leaves)) false
        (every? nil? leaves) true
        :else  (every? true? (map tree? (filter coll? leaves)))))
    (catch IllegalArgumentException ile false)))

;; (defn print-tree [t]
;;   (let)
;;   )

;; --- implement partition

(defn my-partition [amt sequ]
  (loop [se sequ
         acc []]
    (let [[taken cdr] (split-at amt se)]
      (if (< (count taken) amt)
        acc
        (recur cdr (conj acc taken))))))

(defn my-partition-2
  ([amt sequ]
   (my-partition-2 amt sequ []))
  ([amt sequ acc]
   (let [[taken cdr] (split-at amt sequ)]
     (if (< (count taken) amt)
       acc
       (recur amt cdr (conj acc taken))))))

(defn my-partition-3 [amt sequ]
  (loop [[taken cdr] (split-at amt sequ)
         acc []]
    (if (< (count taken) amt)
      acc
      (recur (split-at amt cdr) (conj acc taken)))))

(defn my-partition-4 [amt sequ]
  (if (> amt (count sequ))
    '()
    (cons (take amt sequ)
          (my-partition-4 amt (drop amt sequ)))))

(defn my-partition-5 [n sequ]
  (loop [xs sequ acc []]
    (if (< (count xs) n) ;; runs count on this list. Is this potentially inefficient?
      acc
      (recur (drop n xs) (conj acc (take n xs))))))
