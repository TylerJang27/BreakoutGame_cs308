game
====

This project implements the game of Breakout.

Name: Tyler Jang

### Timeline

Start Date: January 16, 2020

Finish Date: January 19, 2020

Hours Spent: 21

### Resources Used
OpenJavaFX Tutorial (https://www.tutorialspoint.com/javafx/index.htm)

GeeksForGeeks File Reading Tutorial (https://www.geeksforgeeks.org/different-ways-reading-text-file-java/)

### Running the Program

**Main class:** Main.java
    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Calls MenuPage.java and GameManager.java 

**Data files needed:** instructions.txt, level1.txt, level2.txt, level3.txt Without these and additional images, a FileNotFoundException will be thrown and the game will crash.

**Key/Mouse inputs:** 

- Mouse click to select menu option
- Arrow Keys to move paddle
- Space to launch ball
- Numbers 1-3 to switch between levels

**Cheat keys:**

- 8: Critical Hit. Damages all bricks and enemies by 1.
- P: Power Rush. Gives player all powerup effects for a short period.
  - Shield. Powerup prevents the paddle from being stunned.
  - Multiball. An additional ball is spawned and attached to the paddle.
  - Heavy Ball. The ball does twice the damage to bricks and enemies.
  - Bonus Life. The player gains an extra life.
- L: Bonus Life. Gives the player one bonus life.
- R: Reset. Resets the ball to the paddle.

**Known Bugs:**

When the game is finished, when the game is lost, or when the quit button is clicked, the other menu scenes (Help and Levels) cannot be accessed.
The only working button is Start, after which point the number keys can be used to switch between levels. This is because of the way in which Scene
manipulation is handled in my implementation.

**Extra credit:**

The game incorporates small additional animations, such as spinning Entities and glowing menu boxes. 

The game also incorporates a boss for the third level. This boss animates and sends lasers directed at the paddle. These lasers stun the paddle's movement, barring
the player's acquisition of a shield powerup. This boss adds variety and considerable difficulty to the game.

### Notes/Assumptions
Through careful planning and intentional structuring, I was able to implement every part of my plan, with some minor changes (i.e. level design specifics, speeds, etc.).

Due to some past experiences with level design and my own unfamiliarity with OpenJavaFX, I began the construction of this game with a very intentional implementation of the Menu and associated resources (i.e. MenuBox, MenuText, etc.). This allowed for decent quality
Object Oriented design. As development progressed, however, my unfamiliarity with some of the Application-level resources of OpenJavaFX led me to take some shortcuts that stunted
Object Oriented development when it came to some of the game features. As a result, there were more private instance variables associated with many of the classes than I would have liked.

With that being said, I was able to implement a working game that demonstrates a reliable experience.

### Impressions

While ExampleBounce.java provided many of the basic features needed for the gameplay design in this project, there was for me a notable lack of
guidance when it came to the Application-level pipeline used by JavaFX. There was documentation available online, but there is a stark difference between reading
documentation and seeing it in practice. The largest bugs I ran into involved switching between different scenes, and I feel that my final implementation did not
perfectly adhere to Object Oriented design norms. For this reason, I would have appreciated more examples of scenes and scene switching.