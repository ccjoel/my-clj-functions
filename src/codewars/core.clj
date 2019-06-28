(ns codewars.core)

(defn next-num [n]
  (apply * (map #(-> % str Integer/parseInt) (seq (str n)))))

(defn persistence [n]
  (loop [num n
         runs 1]
    (if (< n 10)
      runs
      (recur (next-num n) (inc runs)))))
