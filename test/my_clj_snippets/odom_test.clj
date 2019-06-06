(ns my-clj-snippets.odom-test
  (:require [my-clj-snippets.odom :refer [pad->str
                                          next-number
                                          cap-width->max-number
                                          odo-lazy]]
            [clojure.test :refer :all]))

(deftest pad->str-test
  (testing "pads input number to output str"
    (are [a b] (= a b)
      "0012" (pad->str 4 "12")
      "1200" (pad->str 4 "1200"))))

(deftest next-number-test
  (testing "creates the correct next number"
    (are [a b] (= a b)
      "3220" (next-number "3214" 4 4)
      "65432" (next-number "65431" 6 5)
      "21" (next-number "20" 2 2)
      "22" (next-number "21" 2 2)
      "0023" (next-number "22" 4 4)
      "4446" (next-number "4445" 6 4)
      "4500" (next-number "4466" 6 4)
      "4450" (next-number "4446" 6 4)
      "440" (next-number "434" 4 3)
      nil (next-number "22" 2 2))))

(deftest cap-width->max-number-test
  (testing "aa"
    (are [a b] (= a b)
      "22" (cap-width->max-number 2 2)
      "33" (cap-width->max-number 3 2)
      "4444" (cap-width->max-number 4 4)
      "111111" (cap-width->max-number 1 6)
      "77777" (cap-width->max-number 7 5))))

(deftest odo-lazy-test
  (testing "generates successive odometer values"
    (is (= '("00" "01" "02" "10" "11" "12" "20" "21" "22")
           (take 9 (odo-lazy 2 2))))

    (is (= 100
           (count (take 100 (odo-lazy 8 8)))))))
