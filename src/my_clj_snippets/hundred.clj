(ns my-clj-snippets.hundred
  (require [clojure.string :refer [replace split]]))

(def worse [1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9])
(def worse-n [1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9])

(defmacro infix ""
  [operand1 operator operand2]
  (list operator operand1 operand2))

#_"
(letfn [one ([v] (let [[a op b] (take 3 v)]
                     (op a b)
                     ))]
    (reduce one v))
"

(defn pprint-equation
  [eq]
  nil
  )

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

(def eq-v->res parse)

#_[123 + 4 - 5 + 67 - 89]

(defn chars->str
  "Given a collection of characters, return a concat string"
  [char-coll]
  (apply str char-coll))

(defn one-char->int
  [i]
  (Integer/parseInt (str i))
  ;; (Character/digit i 10)
  )

(defn parse-str
  "parses string into a valid number or symbol
we only need to parse numbers.. could also use Integer/parseInt .. needs string input.
  "
  [i]
  (clojure.edn/read-string i))

(defn str->num [num]
  (Integer/parseInt num))

;; (defn fn-pub
;;   [num-string
;;    desired-result]
;;   (fun )
;;   )

(defn concat-numbers
  [a b]
  (Integer/parseInt (str a b)))

(defn split-seq [num-seq-string]
  ;; first char, as int: (one-char->int (first num-seq-string))
  (if (= (count num-seq-string) 0)
    [nil ""]
    (let [[first-char & rest-chars] num-seq-string
          first-num (one-char->int first-char)
          rest-str (chars->str rest-chars)]
      [first-num rest-str])))

(defn extend-equation
  "given a vector with an equation and a number,
  merged number into last if not an eq, else adds to eq...

  examples:

  [1] 2 => 12
  [1 + 2] 3 => [1 + 23]
  [1 +] 2 => [1 + 2]
  "
  [v item]
  (let [last-item (peek v)]
    (if (number? last-item)
      (conj (pop v) (concat-numbers last-item item))
      (conj v item)
      )))

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
  "Insert the mathematical operators
   + or   -  before any of the digits in the
  decimal numeric string
  123456789
  such that the resulting mathematical expression adds up to 100
  example: 123 + 4 - 5 + 67 - 89 = 100
  "
  ([num-string ;; ^java.lang.String
    desired-result] ;; ^java.lang.Long
   (fun num-string desired-result []))

  ([num-string
    desired-result
    acc]

   (if (empty? num-string)  ;; works for both empty coll and empty str
     (let [result (parse acc)]
       (when (= result desired-result)
         (apply print acc)
         (print "\n")))
     (do
       (let [[next-num queue] (split-seq num-string) ;; "2345" or "" -> 1 "2345"
             last-item (peek acc)]
         (if (empty? acc)
           (fun queue desired-result [next-num])
           (do
             (fun queue desired-result (merge-last-num acc next-num))
             (fun queue desired-result (conj acc '+ next-num))
             (fun queue desired-result (conj acc '- next-num))))
         )))))
