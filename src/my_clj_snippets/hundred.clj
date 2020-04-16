(ns my-clj-snippets.hundred
  (:require [clojure.string :refer [replace split join]]))

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
          result (@(resolve op) a b)] ;; receives + and - operators as symbols to resolve, easier to print
      (if (nil? rest)
        result
        (recur (conj rest result))))))

(defn chars->str
  "Given a coll of characters, returns a concatenated string"
  [char-coll]
  (apply str char-coll))

(defn char->int
  [i]
  (Integer/parseInt (str i)))

(defn concat-numbers
  [a b]
  (Integer/parseInt (str a b)))

(defn split-seq [num-seq-string]
  (let [[first-char & rest-chars] num-seq-string
        first-num (char->int first-char)
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

(defn gen-equation

  "Given a number string and a result, inserts + or - anywhere to generate an equation for result.
   Prints output of all equations that equal desired result.

   Example inputs: \"123456789\" and 100
   Sample printed output: 123 + 4 - 5 + 67 - 89
   (which equals to 100)"

  ([^java.lang.String num-string
    ^java.lang.Long desired-result]
   (gen-equation num-string desired-result []))

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
         (gen-equation queue desired-result branch))))))

#_(join " " [123 '+ 4 '- 5 '+ 67 '- 89])

(defn sum-to-100
  "Programming exercise solution:
  Insert the mathematical operators + or - before any of the digits in the
  decimal numeric string 123456789
  such that the resulting mathematical expression adds up to 100
  example: 123 + 4 - 5 + 67 - 89 = 100

  Yields all possible combinations."
  []
  (gen-equation "123456789" 100))

(comment "For the curious, calling (sum-to-100 yields:

123 + 45 - 67 + 8 - 9
123 + 4 - 5 + 67 - 89
123 - 45 - 67 + 89
123 - 4 - 5 - 6 - 7 + 8 - 9
12 + 3 + 4 + 5 - 6 - 7 + 89
12 + 3 - 4 + 5 + 67 + 8 + 9
12 - 3 - 4 + 5 - 6 + 7 + 89
1 + 23 - 4 + 56 + 7 + 8 + 9
1 + 23 - 4 + 5 + 6 + 78 - 9
1 + 2 + 34 - 5 + 67 - 8 + 9
1 + 2 + 3 - 4 + 5 + 6 + 78 + 9
-1 + 2 - 3 + 4 + 5 + 6 + 78 + 9

")
