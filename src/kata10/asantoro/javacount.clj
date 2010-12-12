(ns loc
  (:use clojure.contrib.duck-streams))

(defn rm-single-line-comment
  "strips //... and /*...*/  comments from line"
  [ line ]
  (.replaceAll
   (.replaceAll line "//.*$" "")
   "/\\*[^/]*\\*/" ""))

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

(defn rm-half-comments [lines]
  (defn rm-end [line] (.replaceAll line ".*\\*/" ""))
  (defn rm-start [line] (.replaceAll line "/\\*.*$" ""))
  (map (fn [ x ] (rm-end (rm-start x))) lines))


(defn rm-comment-middle
  "take away all comment lines that do not include /* or */"
  [lines]
  (defn starts-mc? [ line ] (.contains line "/*"))
  (defn ends-mc?   [ line ] (.contains line "*/"))
  (defn scm-it [ in-comment? l1 l2 ]
    (let [ head (first l2) ]
      (cond (empty? l2) l1
	    (and in-comment? (not (ends-mc? head))) (scm-it in-comment? l1 (rest l2))
            :else (scm-it (starts-mc? head) (list* head l1) (rest l2)))))
  (scm-it false '() lines))
	    
(defn java-loc
  "counts the number of code lines in a java file"
  [ filename ]
  (let [ lines (read-lines filename)
       ]
    (count (rm-empty-lines
	    (rm-braces-only-lines
	     (rm-half-comments
	       (rm-comment-middle 
	          (map rm-single-line-comment lines))))))))
	

