(ns my-clj-snippets.core-test
  (:require [clojure.test :refer :all]
            [my-clj-snippets.core :refer :all]))

(deftest get-unicode-Code-point-name-test
  (testing "Given a valid unicode code point hex, returns the name"
    (is (= "PILE OF POO" (get-unicode-code-point-name 0x1F4A9)))))

(deftest ^:integration all-java-methods-test
  (testing "Given a valid java class name (not as string) returns a vector with all methods as strings"
    (is (contains? (set (all-java-methods String)) ".charAt")
    )))

(deftest unescape-java-test
  (testing "converts escaped unicode to unicode character"
    ;; strange symbol
    (is (= "ð©" (unescape-java "\\u00f0\\u009f\\u0092\\u00a9")))
    ;; pile of poop
    (is (= "💩" (unescape-java "\\uD83D\\uDCA9")))))

(deftest bytes-to-unicode-char-test
  (testing "utf hex bytes to emoji"
    ;; should be poop emoji
    ;; UTF-8: 0xF0 0x9F 0x92 0xA9
    (is (= "💩" (bytes-to-unicode-char 4 [0x00f0 0x009f 0x0092 0x00a9] "UTF-8")))
    (is (= "💩" (bytes-to-unicode-char 4 [0xf0 0x9f 0x92 0xa9] "UTF-8")))
    ;; UTF-16: 0xD83D 0xDCA9
    (is (= "㶩" (bytes-to-unicode-char 2 [0xD83D 0xDCA9] "UTF-16")))
    (is (= "💩" (bytes-to-unicode-char 4 [0xD8 0x3D 0xDC 0xA9] "UTF-16")))
    (is (= "è" (bytes-to-unicode-char 2 [0xc3 0xa8] "UTF-8")))
    ))
