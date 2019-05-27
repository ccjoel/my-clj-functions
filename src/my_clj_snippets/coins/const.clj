(ns my-clj-snippets.coins.const)

(def ^:const P 1)
(def ^:const N 5)
(def ^:const D 10)
(def ^:const Q 25)
(def ^:const U 100)

(def ^:const ordered-coins [P N D Q])
(def ^:const inv-ordered-coins (reverse ordered-coins))

(def ^:const coin-set #{P N D Q})
