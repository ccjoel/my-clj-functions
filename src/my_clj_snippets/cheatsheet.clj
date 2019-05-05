(ns my-clj-snippets.cheatsheet)

(comment "

Accumulator!

(replace strin #"\s" "") ;; removes spaces...

")

(comment"
12+4 match numbers and symbols?
(read-string "(+ 1 2)") would work

clojure.core/symbol?
clojure.core/number?

(show java.awt.Graphics)
(keys (ns-publics 'clojure.core))
clojure.repl/doc, clojure.repl/apropos: returns seq of all *loaded* namespaces that match
*ns*

(javadoc java.lang.Integer)

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
