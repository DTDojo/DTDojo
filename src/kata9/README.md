Clue Game Kata
------------------------

It's time to keep the guests entertained during the holidays, and for that you need to create a game of Clue. The games consists of a set of suspects, a set of weapons and a set of places. Given those three sets, create a Game class that will initialize itself with a random murderer, weapon and place from the set. Users can then call a method on the game instance, passing it a suspect, a weapon and a place and the game will randomly return one of the wrong values, or null if the solution is correct.

For example:

    game = Game.new( [ 'van Helsing', 'Wyatt Earp' ] , [ 'stake', ' gun' ], [ 'cemetery', 'OK Corral' ] )
    // assume the game selects van-helsing at the cemetery with the stake
    game.isIt("Wyatt Earp", "gun", "OK Corral") -> returns one of "Wyatt Earp", "gun" or "OK Corral"
    game.isIt("Van Helsing", "gun", "cemetery") -> returns "gun"
    game.isIt("Van Helsing", "stake", "cemetery) -> returns null

But wait, there is more! You are too busy to play the game yourself, since you have to cook and clean after all those guests, so you create an automatic player for yourself. The player will solve the game, returning who, where and with what weapon. The code should go something like this:

    suspects = [ 'Van Helsing', 'Wyatt Earp' ]
    weapons = [ 'stake', 'gun' ]
    places = [ 'cemetery', 'OK Corral' ]
    game = new Game( suspects, weapons, places)
    Player = new Player( suspects, weapons, places)
    solution = player.solve(game)
    print solution  -> returns "Van Helsing with the stake at the cemetery"