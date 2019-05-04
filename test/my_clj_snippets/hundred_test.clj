(ns my-clj-snippets.hundred-test
  (:require [my-clj-snippets.hundred :refer [parse split-seq]]
            [clojure.test :refer :all]))

#_(deftest fun-test
  (testing "fun test"
    (is (= (fun 100) [123 + 4 - 5 + 67 - 89]))))

(deftest parse-test
  (testing "parses vector with numbers and + - operations of simple valid expressions"
    (is (= (parse [123 '+ 4 '- 5 '+ 67 '- 89]) 100))))

(deftest split-seq-test
  (testing "splits string seq into the first number and the rest of the string"
    (is (= (split-seq "12345") [1 "2345"]))))

;; (deftest extend-equation-test
;;   (testing "crates an eq that makes sense"
;;     (is (=
;;          (extend-equation [1 + 2] 3)
;;          [1 + 23]))
;;     (is (=
;;          (extend-equation [1 +] 3)
;;          [1 + 3]))
;;     ))
