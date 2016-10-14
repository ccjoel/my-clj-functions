(ns my-clj-snippets.core
  (:require [clojure.reflect :refer [reflect]])
  (:import (org.apache.commons.lang3 StringEscapeUtils)))


(defn get-unicode-code-point-name
  "..."
  [code-point-hex]
  (Character/getName code-point-hex))

(defn all-java-methods
  ""
  [class-inst]
  (->> class-inst reflect
       :members
       (filter :return-type)
       (map :name)
       sort
       (map #(str "." %) )
       distinct
       (into [])))


;; String s = StringEscapeUtils.unescapeJava("\\u00f0\\u009f\\u0092\\u00a9");
(defn unescape-java
  ""
  [escaped-hex-string]
  (StringEscapeUtils/unescapeJava escaped-hex-string))

(defn bytes-to-unicode-char
  "example use:
  byte-count: 4
  bytes-vector: [0x00f0 0x009f 0x0092 0x00a9]
  encoding 'UTF-8'"
  [byte-count bytes-vector encoding]
  (new String (byte-array byte-count (map unchecked-byte bytes-vector)) encoding))


;; (defn escape-javascript
;;   ""
;;   [i]
;; (StringEscapeUtils/escapeJavaScript i)
;;   )

;; (defn escape-html
;;   ""
;;   []
;;   (StringEscapeUtils/escapeHtml "joe & co, go to > and ; yay!"))
