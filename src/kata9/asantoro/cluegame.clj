;; generates a random int between 0 and the specified value
(defn rand-element [ l ]
  (if (= 0 (count l)) nil
      (nth l  (rand-int (count l)))))

(defn bad-answers
  "returns all the values that are not part of the solution"
  [ solution suspect weapon place ]
  (defn diff-or-nil [ x y ]
    (if (= x y) nil y))
  (defn remove-nil
    "removes nil elements form the list"
    [ l ]
    (defn not-nil [x] (not= x nil))
    (filter not-nil l))
  (remove-nil (list (diff-or-nil (nth solution 0) suspect)
	        (diff-or-nil (nth solution 1) weapon)
	        (diff-or-nil (nth solution 2) place) ) ))

(defn make-game
  "Creates a clue game from the provided suspects, weapons and places"
  [ suspects weapons places ]
  (defn create-murder
    "creates a murder by picking one element from each list"
    [ x y z ]
    ( list (rand-element x) (rand-element y) (rand-element z)  ))
  (let [ m (create-murder suspects weapons places) ]
    (fn [s w p] (rand-element (bad-answers m s w p)))))

(defn player-solve
  "Solves the game from the lists of suspects, weapons and places"
  [ game suspects weapons places ]
  (let [ suspect (first suspects)
	weapon  (first weapons)
	place   (first places) 
        result  (game suspect weapon place) ]
    (cond (nil? result) (list suspect weapon place)
	  (= result suspect) (player-solve game (rest suspects) weapons places)
          (= result weapon)  (player-solve game suspects (rest weapons) places)
          (= result place)   (player-solve game suspects weapons (rest places)))))

(defn play [ game suspects weapons places ]
  (let [ solution (player-solve game suspects weapons places)
	killer (nth solution 0)
	weapon (nth solution 1)
	place  (nth solution 2) ]
    (print-str killer "with the" weapon "in the" place)))

