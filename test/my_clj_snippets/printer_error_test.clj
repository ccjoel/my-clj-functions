(ns my-clj-snippets.printer-error-test
  (:require [my-clj-snippets.printer-error :refer [pe]]
            [clojure.test :refer :all]))

(deftest pe-test
  (testing "returns correct ratio"
    (are [a b] (= a b)
      "0/14" (pe "aaabbbbhaijjjm")
      "8/22" (pe "aaaxbbbbyyhwawiwjjjwwm"))))

