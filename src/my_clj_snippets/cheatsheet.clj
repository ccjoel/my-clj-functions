(ns my-clj-snippets.cheatsheet)

(comment "
cider-interrupt	C-c C-b	Interrupt any pending evaluations. only when not in repl buffer
or when in repl buffer: C-c C-c

java:

(new String)
; => ""

(String.)
; => ""

(String. 'To Davey Jones's Locker with ye hardies')
; => 'To Davey Jones's Locker with ye hardies'

(java.util.Stack.)
; => []

(let [stack (java.util.Stack.)]
  (.push stack 'Latest episode of Game of Thrones, ho!')
  stack)
; => ["Latest episode of Game of Thrones, ho!"]

(System/getenv)
{'USER' 'the-incredible-bulk'
 'JAVA_ARCH' 'x86_64'}

(System/getProperty "user.dir")
; => '/Users/dabulk/projects/dabook'

(System/getProperty "java.version")
; => '1.7.0_17'

java.util.Date class because the online API documentation (available at http://docs.oracle.com/javase/7/docs/api/java/util/Date.html) is thorough. As a Clojure developer, you should know three features of this date class. First, Clojure allows you to represent dates as literals using a form like this:

#inst '2016-09-19T20:40:02.733-00:00'
Second, you need to use the java.util.DateFormat class if you want to customize how you convert dates to strings or if you want to convert strings to dates. Third, if you’re doing tasks like comparing dates or trying to add minutes, hours, or other units of time to a date, you should use the immensely useful clj-time library

(let [file (java.io.File. "/")]
   (println (.exists file))
   (println (.canWrite file))
   (println (.getPath file)))
; => true
; => false
; => /

(spit '/tmp/hercules-todo-list'
'- kill dat lion brov
         - chop up what nasty multi-headed snake thing")

(comment "
(slurp '/tmp/hercules-todo-list')

; => '- kill dat lion brov
         - chop up what nasty multi-headed snake thing'

The with-open macro is another convenience: it implicitly closes a resource at the end of its body, ensuring that you don’t accidentally tie up resources by forgetting to manually close the resource. The reader function is a handy utility that, according to the clojure.java.io API docs, “attempts to coerce its argument to an open java.io.Reader.” This is convenient when you don’t want to use slurp, because you don’t want to try to read a resource in its entirety and you don’t want to figure out which Java class you need to use. You could use reader along with with-open and the line-seq function if you’re trying to read a file one line at a time. Here’s how you could print just the first item of the Hercules to-do list:

(with-open [todo-list-rdr (clojure.java.io/reader '/tmp/hercules-todo-list')]
  (println (first (line-seq todo-list-rdr))))
; => - kill dat lion brov

:dependencies [[org.clojure/clojure '1.7.0']
                 [clj-time '0.9.0']
                 [org.apache.commons/commons-email '1.3.3']]
The main Clojure repository is Clojars (https://clojars.org/), and the main Java repository is The Central Repository (http://search.maven.org/)


")

(comment "

(namespace 'bar) ;;=> nil
(name 'bar) ;;=> "bar"

(namespace 'foo/bar) ;;=> "foo"
(name 'foo/bar) ;;=> "bar"

java -jar target/uberjar/clojure-noob-0.1.0-SNAPSHOT-standalone.jar

Accumulator!

(replace strin #"\s" "") ;; removes spaces...

;; ' is the shortcut for quote
user> (= 'a (quote a))
true

(doc quote)

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

(comment "Namespace Tools"
         "
(keys (ns-publics 'clojure.core))
clojure.repl/doc
clojure.repl/apropos: returns seq of all *loaded* namespaces that match
(use 'clojure.test)
;; create and change to user namespace, where we load repl tools
(ns user)

;; changes to already existing namespace
(in-ns 'clojure.test)

; rename 'clojure.repl/doc' to 'd'
user=> (require '[clojure.repl :as r :refer [doc] :rename {doc d}])

(use 'foo.bar :reload-all) ;; reloads all changes :)

;; recommend to create a unknown namespace with ns first, then use in-ns
;; for the whole bootstrap to occur

;; display current used namespace
*ns*

clojure.core/find-var
([sym])
  Returns the global var named by the namespace-qualified symbol, or
  nil if no var with that name.

(ns-name *ns*)
;; => user

(ns-imports *ns*)
(ns-imports 'clojure.test)

(ns-interns 'user)
;; => {->WereWolf #'user/->WereWolf,
 clojuredocs #'user/clojuredocs,
 help #'user/help,
 jacob #'user/jacob,
 map->Foo #'user/map->Foo,
 a #'user/a,
 are-all-answers? #'user/are-all-answers?,
 find-name #'user/find-name,
 map->WereWolf #'user/map->WereWolf,
 ->Hand #'user/->Hand,
 map->Hand #'user/map->Hand,
 ->Foo #'user/->Foo,
 map->point #'user/map->point,
 user.proxy$java.lang.Object$SignalHandler$d8c00ec7
 #'user/user.proxy$java.lang.Object$SignalHandler$d8c00ec7,
 ->point #'user/->point,
 c #'user/c,
 purses-values #'user/purses-values,
 cdoc #'user/cdoc,
 my-hand #'user/my-hand,
 foo #'user/foo,
 all-Q-combinations #'user/all-Q-combinations,
 b #'user/b,
 apropos-better #'user/apropos-better}
")

(comment"
12+4 match numbers and symbols?
(read-string "(+ 1 2)") would work

(clojure.pprint/cl-format nil "~r" 12345)
=> "twelve thousand, three hundred forty-five"

clojure.core/symbol?
clojure.core/number?

(show java.awt.Graphics)


#'+
#'clojure.core/+
test> '+

#'doc
;; => #'clojure.repl/doc
;; same as:
(resolve 'doc)
;; => #'clojure.repl/doc

(javadoc java.lang.Integer)
(doc name)
(find-doc part-of-name-or-actual-doc) ;; fuzzy search, really
(source function-name)
(dir clojure.test) ;; => sorted directory of public vars for this namespace

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

(def memo-sleepy-identity (memoize sleepy-identity))

")

(comment "
Clojure is built on the JVM.

The JVM doesn't support first-class functions, or lambdas, out of the box. Every Clojure function, once it is compiled, becomes its own anonymous class from the perspective of the JVM. Each function is, technically, it's own type.

The class it becomes implements IFn, but when you retrieve it's type, it gives you the name of the anonymous class which is different every time.

(doc ifn?)
-------------------------
clojure.core/ifn?
([x])
  Returns true if x implements IFn. Note that many data structures
  (e.g. sets and maps) implement IFn

(-> "a" symbol resolve deref ifn?)   ;; => false
(-> "map" symbol resolve deref ifn?) ;; => true

(fn? a)
;; => false
(fn? map)
;; => true

(ifn? a)
;; => false

(ifn? map)
;; => true
")

(comment
  "
fibo (0) = 0
fibo (1) = 1
fibo (2) = 1
fibo (3) = 2
fibo (4) = 3
fibo (5) = 5
fibo (6) = 8
0 + 1 + 1 + 2 + 3 + 5 + 8
")

(defn fibo
  [n]
  (if (< n 2)
    n
    (+ (fibo (- n 1)) (fibo (- n 2)))))

(defn !
  "Factorial"
  [k]
  (if (= k 1)
    1
    (* k (! (- k 1)))))

(defn multiple? [n divisor]
  (zero? (mod n divisor)))

(defn fizzbuzzize! [n]
  (cond-> nil
    (multiple? n 3) (str "Fizz")
    (multiple? n 5) (str "Buzz")
    :always (or (str n))))

(defn fizzbuzz
  "print numbers 1 to 100 inclusive
   if multiple of 3 print fizz instead
   if multiple of 5 print buzz instead
   if multiple of both print fizzbuzz instead"
  [start end]
  (dorun (map #(println (fizzbuzzize! %))
              (range start (inc end)))))

(defn fb
  [start end]
  (doseq [k (range start (inc end))]
    (println (fizzbuzzize! k))))

(comment "
use: (fizzbuzz 1 100)
or:  (fb 1 100)
")
