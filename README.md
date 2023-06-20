# Nine-Mens-Morris-Game
The Morris Game Variant is a variant of Nine Men's Morris game.Pieces can be placed on intersections of lines.There are a total of 22 locations for pieces.
# Artificial Intelligence: 9 Men's Morris Game Player

This repository contains a computer program developed using MinMax and AlphaBeta pruning algorithms to play the 9 Men's Morris variant game. The program demonstrates a high success rate of 90% against human opponents, based on extensive testing and evaluation.

## Game Description

9 Men's Morris is a strategic board game played on a 3x3 grid with 9 positions. Each player starts with 9 pieces and aims to form a "mill" by placing three pieces in a row, either horizontally or vertically. Once a mill is formed, the player can remove one of the opponent's pieces from the board. The game continues until one player has only two pieces remaining or is unable to make a valid move.

## Program Features

The AI program implemented in this repository utilizes the following features:

1. MinMax Algorithm: The MinMax algorithm is a decision-making algorithm that analyzes the game tree to determine the best possible move for the AI player. It explores different possible moves and assigns a value to each move based on the outcome of the game.

2. AlphaBeta Pruning: AlphaBeta pruning is an optimization technique applied to the MinMax algorithm. It reduces the number of nodes evaluated by pruning branches that are determined to be irrelevant based on the current state of the game.



## Future Enhancements

There are several areas where the AI program can be further enhanced:

1. Improved Heuristics: Enhance the evaluation function by incorporating more advanced heuristics to evaluate the game state and assign values to different moves.

2. User Interface: Develop a graphical user interface (GUI) to provide a more user-friendly experience for playing the game against the AI opponent.

3. Game Variants: Extend the program to support other variations of the 9 Men's Morris game or include additional board games with varying complexities.

