(defproject my-clj-snippets "0.1.0-SNAPSHOT"
  :description "playing around with clojure"
  :url "teh0xqb.github.io"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 ;; https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
                 [org.apache.commons/commons-lang3 "3.4"]
                 [commons-io "2.5"]]

  :test-selectors {:default (every-pred (complement :integration))
                   :integration :integration
                   :unit    (complement :integration)
                   :all     (constantly true)}
  )
