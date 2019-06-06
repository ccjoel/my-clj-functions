(ns my-clj-snippets.coins.const
  (:gen-class))

(def ^:const P 1)
(def ^:const N 5)
(def ^:const D 10)
(def ^:const Q 25)
(def ^:const U 100)

(def ^:const mappings
  {P [[1]]
   N [[5] [1 1 1 1 1]]
   D [[10] [5 5]]
   Q [[25] [10 10 5]]})

(def ^:const ordered-coins [P N D Q])
(def ^:const inv-ordered-coins (reverse ordered-coins))

(def ^:const coin-set #{P N D Q})
