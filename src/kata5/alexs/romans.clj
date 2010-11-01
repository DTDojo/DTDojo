;; we solve the problem by iteratively building a string
;; by concatenating the symbol for the highest value that
;; is less than the number, subtracting that ffom the number
;; and recursively building it again.
(defn roman [ x ]
   (defn roman-help [x,val,symbol]
     (str symbol (roman (- x val))))
   (cond (>= x 1000) (roman-help x 1000 "M")
         (>= x 900)  (roman-help x 900  "CM")
         (>= x 500)  (roman-help x 500  "D")
         (>= x 400)  (roman-help x 400  "CD")
         (>= x 100)  (roman-help x 100  "C")
         (>= x 90)   (roman-help x 90   "XC")
         (>= x 50)   (roman-help x 50   "L")
         (>= x 40)   (roman-help x 40   "XL")
         (>= x 10)   (roman-help x 10   "X")
         (>= x 9)    (roman-help x 9    "IX")
         (>= x 5)    (roman-help x 5    "V")
         (>= x 4)    (roman-help x 4    "IV")
         (>= x 1)    (roman-help x 1    "I")
	 :else ""))
