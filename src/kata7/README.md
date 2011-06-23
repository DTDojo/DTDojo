Secret Santa Kata
------------------------

Secret Santa season is approaching and you are tasked with creating the pairings of gift giver and git receiver. The organizers provide you a a list where each entry is the first and last name of each member. Your program should produce a list of peoples names and their secret santa, with the following conditions:

  1.  each person has one, and only one, Secret Santa
  2.  a person cannot be his own Secret Santa
  2.  a person cannot be a Secret Santa for someone in his family (i.e., with the same last name) 

For example, for the following list:

    Mickey Mouse
    Minnie Mouse
    Donald Duck
    Daisy Duck
    Gladstone Gander 

A valid solution would be:
    
     _____________________________________
    |    Person        | Secret Santa     |
    |------------------|------------------|
    | Mickey Mouse     | Gladstone Gander |
    | Minnie Mouse     | Daisy Duck       |
    | Donald Duck      | Mickey Mouse     |
    | Daisy Duck       | Minnie Mouse     |
    | Gladstone Gander | Donald Duck      |
    |-------------------------------------|