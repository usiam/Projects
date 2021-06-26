# -*- coding: utf-8 -*-

"""
Created on Thu May 27 17:46:47 2021

@author: Tahamid
"""

import random
import HangmanUtils

logo = HangmanUtils.logo
stages = HangmanUtils.stages
lb = HangmanUtils.lineBreak
lost = HangmanUtils.youLose
won = HangmanUtils.youWin

word_list = HangmanUtils.word_list

word = random.choice(word_list)
lives = 6

display = ['_'] * len(word)

print(logo)

print("\n")

print(display)

while display.count('_') != 0 and lives != 0:
    guess = input("\nGuess a letter: ").lower()
    print(lb)
    
    for index, letter in enumerate(word): 
        if letter == guess:
          display[index] = guess
    if guess not in word:
        lives -= 1
       
    print(stages[lives])
    print(display)

if lives == 0:
    print(f"\n{lb}")
    print(f"\nThe word was {word}.")
    print(lost)
else:
    print(f"\n{lb}")
    print(won)
    