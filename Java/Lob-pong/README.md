# Lob-pong
CSC 171 (JAVA) - Final project


  Name: Uzair Tahamid Siam
  Lab section: MW 4:50-6:05 p.m
  Project: 04
  
  Contributors/Collaborators -  
 
  Name: Haolong Liu
  
  Name: Omri Goldenberg
  
  
----------------------------------------------------------------------------------------------------------------------------------------

  Classes used (3):
  
  Canvas
  PanelWithMain 
  JPaneWithBackground
  
----------------------------------------------------------------------------------------------------------------------------------------

 Canvas: Class used for all the drawings.
 
 PanelWithMain: Contains all the code for the animation, collisions, and keylistener controls.
 
 JPaneWithBackground: Contains code to put background image.
 
----------------------------------------------------------------------------------------------------------------------------------------

 Designs: 
  
	Three blocks: For extra credits assignment, we created three rectangle blocks. The blocks will appear in random positions in the screen,
	but they will not overlap with each other, because their random horizontal positions are adjusted.

	Music and sound effects: For extra credits assignment, we added background music and sound effects to our project. Background music we used is 
	Intergalactic-Odyssey.wav. We added a timer to the backgroundmusic so that when it ends, the music starts again. The music countinue to play untill the project is closed.
	Sound effect happen while ball collides with the paddle.

	Boundary for paddle: The paddle cannot go beyond the frame. Work as well when resizing.

	Random speed and direction: Every time the ball hits the paddle, x and y speed may change a little to ensure that the ball does not always have the same speed. 
	Also, x direction may change (right or left).

----------------------------------------------------------------------------------------------------------------------------------------

 Controls: 
 	
 	Use A/D keys to move left/right. Use W/S to speed up/slow down ball. 
 	
----------------------------------------------------------------------------------------------------------------------------------------
 Possible bugs/improvements:
 
 The collisions are not perfect and there might be some problems with them. We tried being as precise as we could but it is not 100% accurate.
 The music does not stop when the game over/time over screen appears. 
 
----------------------------------------------------------------------------------------------------------------------------------------



