# Fireworks-noAnimation

  Name: Uzair Tahamid Siam
  Lab section: MW 4:50-6:05 p.m
  Project: 03
  
  Contributors/Collaborators -  
 
  Name: Haolong Liu
  
----------------------------------------------------------------------------------------------------------------------------------------
  
  Classes used (4):
  
  Canvas
  FireworksGUI
  FireworksGUI2
  FireworksMain
  
----------------------------------------------------------------------------------------------------------------------------------------

  Canvas: Class used for all the drawings. Has 9 methods that are explained in the comments
 
  FireworksGUI: Contains the sliders to set time, velocity, and angle alongside two labels each: one labels what the user is setting, i.e. angle/velocity/time and
  the other shows current slider value.
 
  FireworksGUI2: Contains the two sets of radioboxes for color and type of explosion. Also contains 4 buttons - launch, night sky, day sky, and reset. Night sky and 
  day sky modes are for extra credits. 
  
  FireworksMain: The class with the main method where all the other classes are instantiated and added to a new JPanel so that the drawing area and the controls all appear
  at the same time. This is also where the launch button is added to an actionListener which when pressed stores the value in all the controls. 
  The reset, night sky mode, and day sky mode buttons are also in the main method and call the appropriate methods from the Canvas class. 
  
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

  Instructions:
  
  Select your prefered background - night sky / day sky. Set your angle, velocity, and timer using the sliders at the top. Once done, select the color of your trajectory,
  and the type of explosion you want. Some explosions have their own colors while others have the color of the trajectory. Finally press launch.
  If you want to reset the canvas, press reset - it also resets all sliders and color/explosion type to red and standard.
  
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  ENJOY!
  
  
