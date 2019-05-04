(ns my-clj-snippets.hundred
  (require [clojure.string :refer [replace split]]))

(def worse [1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9])
(def worse-n [1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9])

#_(defmacro infix ""
  [operand1 operator operand2]
  (list operator operand1 operand2))

#_"
(letfn [one ([v] (let [[a op b] (take 3 v)]
                     (op a b)
                     ))]
    (reduce one v))
"

(defn parse
  "parses a vector equation to number
    this could be an internal fn, while the external one
   also has the precondition of being multiples of three..?
  "
  [v]

  ;; {:pre [(odd? (count v))]}

  (if (not (odd? (count v)))
    0 ;; TODO hack to return something valid in the meantime...

    (if (= (count v) 1)
      (first v)

      (let [[a op b & rest] v
            result (@(resolve op) a b)]
        (if (nil? rest)
          result
          (recur (conj rest result)))))))

#_[123 + 4 - 5 + 67 - 89]

(defn chars->str
  "Given a collection of characters, return a concat string"
  [char-coll]
  (apply str char-coll))

(defn one-char->int
  [i]
  (Integer/parseInt (str i)))

(defn concat-numbers
  [a b]
  (Integer/parseInt (str a b)))

(defn split-seq [num-seq-string]
  (if (= (count num-seq-string) 0)
    [nil ""]
    (let [[first-char & rest-chars] num-seq-string
          first-num (one-char->int first-char)
          rest-str (chars->str rest-chars)]
      [first-num rest-str])))

(defn merge-last-num
  "merged last num only

  [1] 2 => 12
  [1 + 2] 3 => [1 + 23]
  "
  [v num]
  (if (empty? v)
    [num]
    (conj (pop v) (concat-numbers (peek v) num))))

(defn fun
  "Insert the mathematical operators + or   -  before any of the digits in the
  decimal numeric string 123456789
  such that the resulting mathematical expression adds up to 100
  example: 123 + 4 - 5 + 67 - 89 = 100"
  ([^java.lang.String num-string
    ^java.lang.Long desired-result]
   (fun num-string desired-result []))

  ([num-string desired-result acc]
   (if (empty? num-string) ;; works for both empty coll and empty str
     (let [result (parse acc)]
       (when (= result desired-result)
         (apply print acc)
         (print "\n")))
     (do
       (let [[next-num queue] (split-seq num-string)]
         (if (empty? acc)
           (do
             (fun queue desired-result [next-num])
             (fun queue desired-result [(* -1 next-num)]))
           (do
             (fun queue desired-result (merge-last-num acc next-num))
             (fun queue desired-result (conj acc '+ next-num))
             (fun queue desired-result (conj acc '- next-num)))))))))
