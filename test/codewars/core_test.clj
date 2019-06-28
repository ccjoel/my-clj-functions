(ns codewars.core-test
  (:require [codewars.core :as my]
            [clojure.test :refer :all]))

(deftest persistence-test
  (testing "123"
    (are [a b] (= a b)
      3 (persistence 39)
      0 (persistence 4)
      2 (persistence 25)
      4 (persistence 999))))

