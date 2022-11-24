# Freecell
Game of freecell played in the terminal

The number of cascade files dictates the number of piles being played with, while the number 
of open piles is how many open spaces are available for a free card. In main, just input the desired
number of either pile and the game will shuffle the cards out in the terminal. 

To make a move, you must input the move in a specific format so that the move parser knows where to move the card
Input: C/O/F for the pile type, followed by the index of that specific pile type, followed by the card index in that pile,
and lastly the pile type and pile index of the destination.

The rules follow standard multimove freecell rules, where you can only move one card at a time unless the
subpile has the right build (alternating colors and decreasing values).
