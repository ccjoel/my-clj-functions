(ns my-clj-snippets.adiff-test
  (:require [my-clj-snippets.adiff :refer [array-diff]]
            [clojure.test :refer :all]))

(deftest adiff-test
  (testing "auhga"
    (are [a b] (= a b)
      [2] (array-diff [1 2] [1])
      [1,3] (array-diff [1,2,2,2,3] [2])
      [1 3 5] (array-diff [1,2,2,2,3 4 5 6] [2 4 6]))))

