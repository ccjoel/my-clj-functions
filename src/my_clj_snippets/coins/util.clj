(ns my-clj-snippets.coins.util
  (:require [my-clj-snippets.coins.const :as c])
  (:gen-class))

(defn next-coin
  "Given a coin, returns next highest coin value"
  [curr-coin]
  (some #(when (> % curr-coin) %) c/ordered-coins))

(defn prev-coin
  [curr-coin]
  (some #(when (< % curr-coin) %) c/inv-ordered-coins))

(defn highest-coin-that-fits [amount]
  (some #(when (<= % amount) %) c/inv-ordered-coins))

(defn is-penny? [amt]
  (= amt c/P))

(defn all-pennies? [purse]
  (every? is-penny? purse))

(def contains-non-pennies? (complement all-pennies?))

(defn is-coin? [value]
  (not (nil? (c/coin-set value))))

(defn fit-coll
  "replace in place:
   So that this is possible: [25 25 4] -> [10 10 5 25 4]"
  [vect index coll]
  (concat (take index vect)
          coll
          (drop (inc index) vect)))

(defn fit-coll-slower?
  [coll pos m]
  (concat (subvec coll 0 pos) m (subvec coll (inc pos))))

(defn vector-with-index [v]
  (map vector v (range)))

#_(defn inexact-change? [amount coin]
  (and
   (not (some nil? [amount coin]))
   (not= amount coin)
   (>= (- amount coin) 0)))
