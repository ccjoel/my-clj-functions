(ns my-clj-snippets.coins.core-test
  (:require [my-clj-snippets.coins.core
             :refer [get-coins
                     get-change]]
            [clojure.test :refer :all]))

(deftest get-change-test
  (testing "perfect change"
    (are [a b] (= a b)
      [25 25 25 25] (get-change 100)
      [25 25 25 25 1] (get-change 101)
      [10 10 5] (get-change 25)
      [25 1] (get-change 26)
      [1] (get-change 1)
      [10 1 1] (get-change 12)
      [10 1 1 1 1] (get-change 14)
      [25 10 5 1 1] (get-change 42)
      [25 25 25 1 1 1 1] (get-change 79)
      [25 25 25 5] (get-change 80))))

(deftest get-coins-test
  (testing "returns optimal change to return from cash"

    ;; expects first, then actual
    (is (= [25 25 25 25]
         (get-coins 100))) ;; four quarters

    (is (= [5] (get-coins 5)))

    (is (= [5 1] (get-coins 6))
        "Optimal change for $0.06 USD should be a Nickel and a Penny")

    (is (= [25 5 1 1 1 1] (get-coins 34)))

    (is (= [1] (get-coins 1)))

    (is (= [] (get-coins 0)))))

;; 13 so far
(def ^:const all-Q-combinations
  #{[25] [10 10 5] [10 5 5 5] [5 5 5 5 5]
   [10 10 1 1 1 1 1] [10 5 5 1 1 1 1 1]
   [5 5 5 5 1 1 1 1 1] [10 5 1 1 1 1 1 1 1 1 1 1]
   [5 5 5 1 1 1 1 1 1 1 1 1 1]
   [10 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
   [5 5 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
   [5 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
   [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]})

(def ^:const all-D-combinations
  #{[10] [5 5] [5 1 1 1 1 1] [1 1 1 1 1 1 1 1 1 1]})

(defn- purses-values [purses]
  (for [purse purses] (apply + purse)))

(defn- are-all-answers? [purses]
  (count (set (purses-values purses))))
