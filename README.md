# Love-Letter-Game

This is my very first project that I have completed. I wasn't focused on it being user friendly, it was an exercise to get used to Java and to break down the game logically and turn it into code. There are probably some edge cases that I am still missing, and some logic that could slightly off, but over all I am happy with what I built. 

Objective of the game:

Each player has one card in their hand. They take turns drawing one card from the deck the playing the card, resolving that action. Players are able to knock each other out of the round, or if the deck runs out, who ever has the highest value card wins the round is awarded a point. Rack up as many points as you can to win the game!

The deck contains
1. Guard (5 copies)
2. Priest (2 copies)
3. Baron (2 copies)
4. Handmaid (2 copies)
5. Prince (2 copies)
6. King
7. Countess
8. Princess

Winning the Game
2 players: 7 points
3 players: 5 points
4 players: 4 points



Takeaways from this project:

This project wasn't planned out from the beginning. The order of my build progression was as follows: Creating a deck > shuffuling a deck> creating Card and Player classes> dealing to players> creating logic for all the cards > changing the win conditions depending on number of players> repeating a round > winning a game. There were times where I would have to go back into the Card class or Player class to create more accessor methods that I over looked such as getHandVal().

The Handmaid and Countess logic was especially tricky for me. for Handmaid, I had created a method to detect if there were no valid target due to Handmaid's effect of not allowing other players to target them, but because I built this with a 2 player scenario in mind, it led me to not see a huge problem scaling for 3 or 4 players. Take for example below: 

Player A: Current Player
Player B: Out of the round
Player C: Handmaid

I had first built the checkHandmaid() method to count the number of Handmaids currently active, and compare them to the number of players -1. But in the case of the example above, player A could not target themself, Player B was out of the game, Player C was protected with handmaid and the count of the number of Handmaid was 1 when it needed to be 2. This bug took me the longest to recognize, but I was happy with my solution of subtracting and additional 1 for each player out of the round.

For the Countess, if a player had a Countess in their hand along with a King OR a Prince, then they would be required to discard the Countess. It took me a while to figure out where to put this check, not so much the logic. I could put it where the countess logic but that would be too far along the flow of the game, or put it right a the beginning of the players turn, but figured the best was to put it in the getCardchoice() method. 

What to improve on futrue projects:

Self commenting code is something I'll try and make a habbit out of. For example In my burnCard() method, I have parameters "n" and "p" and it isn't too bad looking at what it does. However, if you took at the PlayCard() method where it takes in seven arguments, I have a legend commented above thats translating the "t" or "c" parameters. This could be avoided if I was more mindful of self commenting code.

There were parts of my code that were very inconsistant. I go back and forth between using players.length and having a seperate variable for nPlayers which is redundant. I also used Players[i].getHandVal() and players[i].getPlayerHand()[0].getVal(). I have code for the getTarget() method copied 3 times I belive will 3 variations, I think I could of handeled it in a more efficient way. I think doing some baseline planning will help out a lot next time.

I plan to do another Love Letter project with the player useability in mind with graphics, multiplayer(multi device or hotseat), and a game history played. 
