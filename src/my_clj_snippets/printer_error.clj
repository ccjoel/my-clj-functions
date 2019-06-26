(ns my-clj-snippets.printer-error)

(def color-set 0)

(defn pe
  ""
  [label-colors]
  (let [denominator (count label-colors)
        numerator (count (re-seq #"[n-z]" label-colors))]
    (str numerator "/" denominator)))
