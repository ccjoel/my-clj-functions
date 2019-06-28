(ns codewars.core-test
  (:require [codewars.core :refer [persistence multiply-digits scramble]]
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

;; -------

(defn expect
  ([expected verb actual msg]
   (is (= expected actual) msg))
  ([expected verb actual]
   (expect expected verb actual nil))
  ([expected actual]
   (expect expected nil actual nil)))

(deftest scramble-test
  (testing "scramble contains the other word..?"

    (expect true :got (scramble "rkqodlw" "world") "world within no repeated chars")

    (is (= true (scramble "cedewaraaossoqqyt" "codewars")) "has multiple repeated chars, cant just compare sorted")

    (is (= false (scramble "katas" "steak")) "no steak")

    (expect false :got (scramble "scriptjavx" "javascript") "a repeats in javascript")

    (is (= true (scramble "scriptingjava" "javascript")) "javascript is within")

    (expect true :got (scramble "scriptsjava", "javascripts") "javascripts")

    (comment
      (test-assert(scramble "javscripts", "javascript"),false)
      (test-assert(scramble "aabbcamaomsccdd", "commas"),true)
      (test-assert(scramble "commas", "commas"),true)
      (test-assert(scramble "sammoc", "commas"),true))

    ))

;; ---------
