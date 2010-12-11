(ns loc
  (:use clojure.contrib.duck-streams))

(defn strip-single-line-comment
     "strips // comments from line"
     [ line ]
     (.replaceAll line "//.*$" ""))

(defn rm-empty-lines
  "removes all empty lines from list"
  [ l ]
  (defn trim [x] (.trim x))
  (defn notempty? [x] (not (empty? x)))
  (filter notempty? (map trim l))) 

(defn rm-braces-only-lines
  "removes all lines that contain only an opening or closing curly brace"
  [ lines ]
  (defn not-brace-only? [ line ]
    (let [ short-line (.trim line) ]
      (and (not= "{" short-line) (not=  "}" short-line))))
  (filter not-brace-only? lines))
     
(defn java-loc
  "counts the number of code lines in a java file"
  [ filename ]
  (let [ lines (read-lines filename) ]
    (count (rm-empty-lines (rm-braces-only-lines (map strip-single-line-comment lines))))))
	

