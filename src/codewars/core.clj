(ns codewars.core)

(defn multiply-digits [n]
  (apply *
         (map
          #(Character/digit % 10)
          (seq (str n)))))

(defn persistence [n]
  (loop [num n
         runs 0]
    (if (< num 10)
      runs
      (recur (multiply-digits num) (inc runs)))))

;; -------------

;; try1, doesnt work as sets don't allow duplicates
#_(defn scramble-try1 [s1 s2]
  (let [s2->set (set s2)]
    (= (clojure.set/intersection s2->set (set s1))
       s2->set)))

;; try2 doesn't work as s1 can have repeated "noise" characters
#_(defn scramble-try2 [s1 s2]
  (let [len (count s2)
        s1-seq (sort s1)
        s2-seq (sort s2)]
    (= (take len s1-seq) (take len s2-seq))))


(defn m->* [in m]
  (clojure.string/replace-first in (first m) "*"))

(defn char-count [r in]
  (count (re-seq (re-pattern (str "\\" r)) in)))

(defn scan [s1 s2 len1 len2]
  (loop [acc s1
         counter 1
         target s2]
    (if (or (> counter len1) (> counter len2))
      acc
      (recur (m->* acc target)
             (inc counter)
             (rest target)))))

;; works, but complicated
(defn scramble-try3 [s1 s2]
  (let [len2 (count s2)
        transformed (scan s1 s2 (count s1) len2)]
    (= len2
       (char-count "*" transformed))))

;; --- reducing with clojure.string/replace-first...
;; replaces with "" instead of asterisks ;)

(defn scramble [s1 s2]
  (empty? (reduce #(clojure.string/replace-first %1 %2 "") s2 s1)))

;; ------

;; (defn n->num-seq [n]
;;   (map-indexed #(Math/pow (Character/digit %2 10) (inc %1)) (str n)))

(defn n->num-seq [n]
  (map-indexed #(->> %1 inc
                     (Math/pow (Character/digit %2 10))
                     int)
               (str n)))

(defn eureka? [n]
  (=
   (apply + (n->num-seq n))
   n))

(defn sum-dig-pow [start end]
  (filter eureka? (range start (inc end))))

;; --------

(comment "
    ;; someday solve min/max nested tree data

{:id "cluster0"
 :value 10
 :children
 [{:id "keyspace1"
  :value 9
  :children
  [{:id "table1"
   :value 8
   :children
   [{:id "datacenter1"
    :value 7
    :children
    [{:id "node1"
     :value 5
     }]}]}]}]}


")
