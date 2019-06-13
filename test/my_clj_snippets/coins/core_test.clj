(ns my-clj-snippets.coins.core-test
  (:require [my-clj-snippets.coins.core
             :refer [amt->change
                     amt->change-p]]
            [clojure.test :refer :all]))

(deftest amt->change-p-test
  (testing "perfect change"
    (are [a b] (= a b)
      [25 25 25 25] (amt->change-p 100)
      [25 25 25 25 1] (amt->change-p 101)
      [10 10 5] (amt->change-p 25)
      [25 1] (amt->change-p 26)
      [1] (amt->change-p 1)
      [10 1 1] (amt->change-p 12)
      [10 1 1 1 1] (amt->change-p 14)
      [25 10 5 1 1] (amt->change-p 42)
      [25 25 25 1 1 1 1] (amt->change-p 79)
      [25 25 25 5] (amt->change-p 80))))

(deftest amt->change-test
  (testing "returns optimal change to return from cash"

    ;; expects first, then actual
    (is (= [25 25 25 25]
         (amt->change 100))) ;; four quarters

    (is (= [5] (amt->change 5)))

    (is (= [5 1] (amt->change 6))
        "Optimal change for $0.06 USD should be a Nickel and a Penny")

    (is (= [25 5 1 1 1 1] (amt->change 34)))

    (is (= [1] (amt->change 1)))

    (is (= [] (amt->change 0)))))

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
