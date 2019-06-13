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

(defn is-coin? [value]
  (not (nil? (c/coin-set value))))

(def contains-non-pennies? (complement all-pennies?))
