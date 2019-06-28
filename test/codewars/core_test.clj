(ns codewars.core-test
  (:require [codewars.core :refer [persistence]]
            [clojure.test :refer :all]))

(deftest persistence-test
  (testing "123"

    (is (= 2 (persistence 39)))

    #_(are [a b] (= a b)
      3 (persistence 39)
      0 (persistence 4)
      2 (persistence 25)
      4 (persistence 999))
    ))

