(ns codewars.core-test
  (:require [codewars.core :refer [persistence multiply-digits]]
            [clojure.test :refer :all]))

(deftest persistence-test
  (testing "ensure correct number of times is caculated"
    (are [a b] (= a b)
      3 (persistence 39)
      0 (persistence 4)
      2 (persistence 25)
      4 (persistence 999))))


(deftest multiply-digits-test
  (testing "ensure correct number of times is caculated"
    (are [a b] (= a b)
      27 (multiply-digits 39)
      14 (multiply-digits 27)
      4 (multiply-digits 14))))
