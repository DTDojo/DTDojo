(ns loc
  (:use clojure.test))

(load "javacount")

(deftest test-loc
  (is (= 4 (java-loc "fourlinefile.java")))
  (is (= 3 (java-loc "hasblankspace.java")))
  (is (= 3 (java-loc "threelinesandcomment.java")))
  (is (= 4 (java-loc "emptybraces.java")))
  )

(deftest multi-line
  (is (= 4 (java-loc "multilinecomments.java")))
  )
(run-tests)
