(ns my-clj-snippets.adiff)

(defn array-diff [a b]
  (let [b->set (set b)]
    (remove #(b->set %) a)))
