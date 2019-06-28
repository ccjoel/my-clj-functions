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
