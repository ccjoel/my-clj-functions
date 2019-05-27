(ns my-clj-snippets.cheatsheet)

(comment "

Accumulator!

(replace strin #"\s" "") ;; removes spaces...

(use 'clojure.test)

(clojure.test/function? cons)
; => true

(meta #'+)

(def mm ^:true-bool-meta-prop [1 2 3])

(def var-w-no-meta [1])
(def var-w-meta (with-meta var-w-no-meta {:bye "1"})) ;; creates copy with metadata

;; merging data..
(def m ^:hi [1 2 3])
(meta (vary-meta m merge {:bye "probably"}))
;; {:hi true, :bye true}

defining meta map:
(def better-meta ^{:doc "how it works"} [1 2 3])


")

(comment"
12+4 match numbers and symbols?
(read-string "(+ 1 2)") would work

(clojure.pprint/cl-format nil "~r" 12345)
=> "twelve thousand, three hundred forty-five"

clojure.core/symbol?
clojure.core/number?

(show java.awt.Graphics)
(keys (ns-publics 'clojure.core))
clojure.repl/doc, clojure.repl/apropos: returns seq of all *loaded* namespaces that match
*ns*

(javadoc java.lang.Integer)
(doc name)
(find-doc part-of-name)
(source function-name)

(seq "123")
=> (\1 \2 \3)

(apply str '(\1 \2 \3))
=> "123"

(apply str [1 '+ 2 '- 3])

(name (symbol +))
=> "+"

user> (reverse "123")
(\3 \2 \1)
user> (apply str (reverse "123"))
"321"

(keyword "q")
=> :q

(name :a)
=> "a"

(class "1")
=> java.lang.String

(type 1)
=> java.lang.Long

#_(defmacro infix ""
  [operand1 operator operand2]
  (list operator operand1 operand2))

#_"
        (letfn [one ([v] (let [[a op b] (take 3 v)]
                           (op a b)
                           ))]
          (reduce one v))
"

")

(comment "
shortcuts
,ss => opens a new window or focuses a window repl

,sf => evals current fn
,se => evaluates previous s-expression
,sc => connects to local repl on terminal
,si => jacks in to new repl session.. but slowers to me.
,sb => loads entire buffer

")


(comment "
*get*
get returns the value for the specified key in a map or record, index of a vector or value in a set. If the key is not present, get returns nil or a supplied default value.

;; val of a key in a map
(get {:a 1 :b 2 :c 3} :b)
;; ⇒ 2

;; index of a vector
(get [10 15 20 25] 2)
;; ⇒ 20

;; in a set, returns the value itself if present
(get #{1 10 100 2 20 200} 1)
;; ⇒ 1

;; returns nil if key is not present
(get {:a 1 :b 2} :c)
;; ⇒ nil

;; vector does not have an _index_ of 4. nil is returned
(get [1 2 3 4] 4)
;; ⇒ nil

(defrecord Hand [index middle ring pinky thumb])
(get (Hand. 3 4 3.5 2 2) :index)
;; ⇒ 3
")

(defn fib-seq
  "Returns a lazy sequence of Fibonacci numbers"
  ([]
   (fib-seq 0 1))
  ([a b]
   (lazy-seq
    (cons b (fib-seq b (+ a b))))))

(comment
  "examples on counting realized entries in lazy-seq"
  (def foo (iterate inc 1))

  (take (count-realized foo) foo)
  ;; => (1)

  (dorun (take 5 foo))
  ;; ;=> nil

  (take (count-realized foo) foo)
  ;; => (1 2 3 4 5)
  )

(rest (seq [:one]))
;; ⇒ ()
(next (seq [:one]))
;; ⇒ nil

(list* 0 1 (range 2 5))
;; ⇒ (0 1 2 3 4) ;; cons but with multiple inputs appended

(assoc [1 2 76] 2 3) ; ⇒ [1 2 3]

;; when using dissoc:
(comment
;; note that a map is returned, not a record!
(defrecord Hand [index middle ring pinky ring])
;; always be careful with the bandsaw!
(dissoc (Hand. 3 4 3.5 2 2) :ring)
;; ⇒ {:index 3, :middle 4, :pinky 2, :thumb 2}
)

(comment "
rest returns a seq of items starting with the second element in the collection. rest returns an empty seq if the collection only contains a single item.

next returns nil if the collection only has a single item. This is important when considering \"truthiness\" of values since an empty seq is \"true\" but nil is not.

(contains? #{1 2 5} 5)
true
user> (#{1 2 5} 5)
5
user> (#{1 2 5} 7)
nil

(contains? {:a 1 :b 2 :c 3} :c)
;; ⇒ true

;; true if index 2 exists
(contains? ["John" "Mary" "Paul"] 2)
;; ⇒ true

(some even? [1 2 3 4 5])
;; ⇒ true

;; predicate returns the value rather than simply true
(some #(if (even? %) %) [1 2 3 4 5])
;; ⇒ 2

(keys (Hand. 2 4 3 1 2))
;; ⇒ (:index :middle :ring :pinky :thumb)

(take 3 [1 3 5 7 9])
;; ⇒ (1 3 5)
(type (take 3 (range)))
;; ⇒ clojure.lang.LazySeq

(drop 3 '(0 1 2 3 4 5 6))
;; ⇒ (3 4 5 6)
(drop 2 [1 2])
;; ⇒ ()
(drop 2 nil)
;; ⇒ ()

(take-while #(< % 5) (range))
;; ⇒ (0 1 2 3 4)

(drop-while #(< % 5) (range 10))
;; ⇒ (5 6 7 8 9)

(#(nil? (%2 % %)) :b {:a 1})

(defrecord WereWolf [name title])
(def jacob (->WereWolf "Jacob" "Lead Shirt Discarder"))
 (.name jacob)

(defrecord point [x y])
(def a (->point 10 25))
a
=> {:x 10, :y 25}

;; to allow nil constructor values, used map->recordType constructor, since its not by positional params
(def c (map->point {:x 10}))
c
=> {:x 10, :y nil}


")
