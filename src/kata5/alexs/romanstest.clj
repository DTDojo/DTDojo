(ns test-romans
  (:use clojure.test))

(load-file "romans.clj")

;; unit tests
(deftest test-roman
  (is (= "I"     (roman 1)))
  (is (= "II"    (roman 2)))
  (is (= "III"   (roman 3)))
  (is (= "IV"    (roman 4)))
  (is (= "V"     (roman 5)))
  (is (= "VII"   (roman 7)))
  (is (= "XIX"   (roman 19)))
  (is (= "XLI"   (roman 41)))
  (is (= "XCVII" (roman 97)))
  (is (= "CCXIX" (roman 219)))
  (is (= "CDLXI" (roman 461)))
  (is (= "DCXCI" (roman 691)))
  (is (= "CMXII" (roman 912)))
  (is (= "MMXVI" (roman 2016)))
)

(run-tests)

