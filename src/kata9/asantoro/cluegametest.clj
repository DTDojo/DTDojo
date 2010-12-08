(ns cluegametest
  (:use clojure.test))

(load "cluegame")

;; test-help functions
(defn list-contains? [l val]
     (cond (empty? l) 'false
	   (= (first l) val) 'true
	   :else (list-contains? (rest l) val)))

;; fixtures 
(def crime [ "s" "w" "p"] )
(def game (make-game ["s"] ["w"] ["p"]))
			   
;; tests
(deftest test-bad-answers
  (is (= [ ] (bad-answers crime "s" "w" "p")))
  (is (= [ "s1" ] (bad-answers crime "s1" "w" "p")))
  (is (= [ "w1" ] (bad-answers crime "s" "w1" "p")))
  (is (= [ "p1" ] (bad-answers crime "s" "w" "p1")))
  (is (= [ "s1" "w1" ] (bad-answers crime "s1" "w1" "p")))
  (is (= [ "s1" "p1" ] (bad-answers crime "s1" "w" "p1")))
  (is (= [ "w1" "p1" ] (bad-answers crime "s" "w1" "p1")))
  (is (= [ "s1" "w1" "p1" ] (bad-answers crime "s1" "w1" "p1")))
  )

(deftest test-games
 (is (= nil (game "s" "w" "p")))
 (is (= "s1"  (game "s1" "w" "p")))
 (is (= "w1" (game "s" "w1" "p")))
 (is (= "p1" (game "s" "w" "p1")))
 (is (list-contains? [ "s1" "w1" ] (game "s1" "w1" "p")))
 (is (list-contains? [ "s1" "p1" ] (game "s1" "w" "p1")))
 (is (list-contains? [ "w1" "p1" ] (game "s" "w1" "p1")))
 (is (list-contains? [ "s1" "w1" "p1" ] (game "s1" "w1" "p1")))
 )

(deftest test-player
  (is (= "s with the w in the p" (play game ["s1" "s2" "s"] ["w1" "w" "w2"] ["p" "p2"]))))

(run-tests)