(ns my-clj-snippets.hundred
  (:require [clojure.string :refer [replace split]]))

(def worse [1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9])
(def worse-n [1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9])

(defn parse
  "Parses a vector equation to result number
   Sample input: [123 '+ 4 '- 5 '+ 67 '- 89] ; = 100 ; output"
  [v]
  {:pre [(odd? (count v))]}

  (if (= (count v) 1)
    (first v)

    (let [[a op b & rest] v
          result (@(resolve op) a b)] ;; received + and - operators as symbols to resolve, easier to print
      (if (nil? rest)
        result
        (recur (conj rest result))))))


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
  (let [[first-char & rest-chars] num-seq-string
        first-num (one-char->int first-char)
        rest-str (chars->str rest-chars)]
    [first-num rest-str]))

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

  "Given a number string and a result, returns an equation inserting + or 1 anywhere to equal to result
   Example inputs: 123456789 and 100
   Sample from output: 123 + 4 - 5 + 67 - 89 = 100"

  ([^java.lang.String num-string
    ^java.lang.Long desired-result]
   (fun num-string desired-result []))

  ([num-string desired-result acc]
   ;; end condition
   (if (empty? num-string) ;; works for both empty coll and empty str
     (when (= (parse acc) desired-result)
       (apply print acc)
       (print "\n"))
     ;; else, branch out to 1, -1, next-num, + or -
     (let [[next-num queue] (split-seq num-string)
           branches (if (empty? acc)
                      [[next-num] [(* -1 next-num)]]
                      [(merge-last-num acc next-num) (conj acc '+ next-num) (conj acc '- next-num)])]
       (doseq [branch branches]
         (fun queue desired-result branch))))))

(defn sum-to-100
  "Insert the mathematical operators + or - before any of the digits in the
  decimal numeric string 123456789
  such that the resulting mathematical expression adds up to 100
  example: 123 + 4 - 5 + 67 - 89 = 100"
  []
  (fun "123456789" 100))
