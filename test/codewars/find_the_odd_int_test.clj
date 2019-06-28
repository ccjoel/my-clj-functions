(ns codewars.find-the-odd-int-test
  (:require [codewars.find-the-odd-int :refer [count-num vec->str find-odd]]
            [clojure.test :refer :all]))

(deftest count-num-test
  (testing "count occurrences of a num"
    (is (= 3 (count-num 5 "  1  2  3  6  5  5  6  7  5  ")))
    (is (= 1 (count-num -1 "  1  1  2  -2  5  2  4  4  -1  -2  5  " )))))

(deftest vec->str-test
  (testing "converts vector of numbers to numeric string"
    (is (= "  1  2  3  4  5  6  7  -10  -22  " (vec->str [1 2 3 4 5 6 7 -10 -22])))
    (is (= "  1  1  2  -2  5  2  4  4  -1  -2  5  " (vec->str [1 1 2 -2 5 2 4 4 -1 -2 5])))))

(deftest find-odd-test
  (testing "given vector of numbers, finds number that repeats odd number of times"

    (is (= (find-odd [20 1 -1 2 -2 3 3 5 5 1 2 4 20 4 -1 -2 5]) 5))

    (are [answer xs] (= answer (find-odd xs))
      5 [20 1 -1 2 -2 3 3 5 5 1 2 4 20 4 -1 -2 5]
      -1 [1 1 2 -2 5 2 4 4 -1 -2 5]
      5 [20 1 1 2 2 3 3 5 5 4 20 4 5 4 4]
      10 [10]
      10 [1 1 1 1 1 1 10 1 1 1 1]
      1 [5 4 3 2 1 5 4 3 2 10 10])))
