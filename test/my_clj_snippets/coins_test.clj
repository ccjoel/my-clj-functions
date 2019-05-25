(ns my-clj-snippets.coins-test
  (:require [my-clj-snippets.coins :refer [prev-coin next-coin amt->change
                                           unexact-change? all-pennies? change-but]]
            [clojure.test :refer :all]))

(deftest prev-coin-test
  (testing "returns previous usable coin value"

    (is (= (prev-coin 10)
           5))
    (is (= (prev-coin 25)
           10))
    (is (= (prev-coin 5))
        1)
    (is (nil? (prev-coin 1)))))

(deftest next-coin-test
  (testing "returns next biggest coin to use value"

    (is (= (next-coin 10)
           25))

    (is (nil? (next-coin 25)))

    (is (= (next-coin 5))
        10)))

(deftest amt->change-test
  (testing "returns optimal change to return from cash"

    (is (= (amt->change 100)
           [25 25 25 25])) ;; four quarters

    (is (= (amt->change 5)
           [5]))

    (is (= (amt->change 6)
           [5 1]))

    (is (= (amt->change 34)
           [25 5 1 1 1 1]))

    (is (= (amt->change 1) [1]))

    (is (= (amt->change 0) []))))

(deftest unexact-change?-test
  (testing "returns true or fale if the change is exact"
    (is (unexact-change? 10 5))
    (is (false? (unexact-change? 5 5)))

    (is (false? (unexact-change? 5 nil)))

    (is (false? (unexact-change? nil 5)))
    ))

(deftest all-pennies?-test
  (testing "returns true if purse only has pennies :-("
    (is (all-pennies? [1 1 1 1]))
    (is (false? (all-pennies? [1 1 1 5 10])))))

(deftest change-but-test
  (testing "returns all combinations of coin change for an amount"
    
    (is (= (change-but [1 1 1 1]) [1 1 1 1]))

    ;; (is (= (change-but [5]) [[1 1 1 1 1] [5]]))

    ))
