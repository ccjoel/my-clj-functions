(ns a4clojure.core-test
  (:require [a4clojure.core :refer [groupy tree?]]
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



(deftest tree?-test
  (testing "is tree?... needs a non-nil, non-coll non-bool value, and left and right leaves"

    (is (= (tree? '(:a (:b nil nil) nil))
          true))

    (is (= (tree? '(:a (:b nil nil)))
           false))

    (is (= (tree? [1 nil [2 [3 nil nil] [4 nil nil]]])
           true))

    (is (= (tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]])
           false))

    (is (= (tree? [1 [2 [3 [4 nil nil] nil] nil] nil])
           true))

    (is (= (tree? [1 [2 [3 [4 false nil] nil] nil] nil])
           false))

    (is (= (tree? '(:a nil ()))
           false))

    (is (= (tree? [5 [2 [nil nil]] [3 [[7 nil nil] [8 7 nil]]]]) false))))

