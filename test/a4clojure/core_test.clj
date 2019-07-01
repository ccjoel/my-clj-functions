(ns a4clojure.core-test
  (:require [a4clojure.core :refer [groupy]]
            [clojure.test :refer :all]))

(deftest groupy-test

  (testing "groups with fn shorthand > 5"
    (is (= (groupy #(> % 5) [1 3 6 8])
           {false [1 3], true [6 8]})))

  (testing "groups using apply fn"
    (is (= (groupy #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
           {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})))

  (testing "groups with std lib count fn"
    (is (= (groupy count [[1] [1 2] [3] [1 2 3] [2 3]])
           {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))))

