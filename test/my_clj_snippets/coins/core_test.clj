(ns my-clj-snippets.coins.core-test
  (:require [my-clj-snippets.coins.core :refer [amt->change
                                                get-change
                                                amt->inexact-change]]
            [clojure.test :refer :all]))

(deftest amt->change-test
  (testing "returns optimal change to return from cash"

    (is (= (amt->change 100)
           [25 25 25 25])) ;; four quarters

    (is (= (amt->change 5)
           [5]))

    (is (= (amt->change 6)
           [5 1])
        "Optimal change for $0.06 USD should be a Nickel and a Penny")

    (is (= (amt->change 34)
           [25 5 1 1 1 1]))

    (is (= (amt->change 1) [1]))

    (is (= (amt->change 0) []))))

(deftest amt->inexact-change-test
  ;; (use-fixtures :once
  ;;   {:before (fn [] ...)
  ;;    :after  (fn [] ...)})
  (testing "returns all combinations of coin change for an amount"
    (are [a b] (= a b)
      (amt->inexact-change 100)  [25 25 25 10 10 1 1 1 1 1]
      (amt->inexact-change 25)   [10 10 1 1 1 1 1]
      (amt->inexact-change 11)   [10 1])))

(def ^:const all-Q-combinations
  #{[25] [10 10 5] [10 5 5 5] [5 5 5 5 5]
   [10 10 1 1 1 1 1] [10 5 5 1 1 1 1 1]
   [5 5 5 5 1 1 1 1 1] [10 5 1 1 1 1 1 1 1 1 1 1]
   [5 5 5 1 1 1 1 1 1 1 1 1 1]
   [10 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
   [5 5 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
   [5 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]
   [1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1]})

(defn purses-values [purses]
  (for [purse purses] (apply + purse)))

(defn are-all-answers? [purses]
  (count (set (purses-values purses))))

(deftest get-change-test
  (testing "returns all possible purses from parallel universes that could contain the input amount"
    (are [a b] (= a b)
      (get-change [25]) all-Q-combinations)))
