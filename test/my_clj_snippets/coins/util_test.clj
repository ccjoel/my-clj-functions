(ns my-clj-snippets.coins.util-test
  (:require [my-clj-snippets.coins.util :refer [prev-coin next-coin
                                                all-pennies?
                                                highest-coin-that-fits
                                                fit-coll
                                                contains-non-pennies?
                                                ]]
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

(deftest all-pennies?-test
  (testing "returns true if purse only has pennies. sounds sad"
    (is (all-pennies? [1 1 1 1]))
    (is (all-pennies? [1]))
    (is (false? (all-pennies? [1 1 1 5 10])))))

(deftest highest-coin-that-fits-test
  (testing "returns all combinations of coin change for an amount"
    (are [a b] (= a b)
      (highest-coin-that-fits 100) 25
      (highest-coin-that-fits 5) 5
      (highest-coin-that-fits 24) 10)))

(deftest contains-non-pennies?-test
  (testing "true if your purse contains at least one non penny, false if only pennies in purse"
    (is (contains-non-pennies? [1 5 1 10]))
    (is (false? (contains-non-pennies? [1 1 1 1])))))

(deftest fit-coll-test
  (testing "fits a coll inside coll"
    (are [a b] (= a b)
      [25 10 10 5 25] (fit-coll [25 25 25] 1 [10 10 5]))))
