# Game Design
### Contributors
Design and full implementation by Tyler Jang

### Overview
This is a breakout game with 3 levels. Each level has a different starting default block configuration.
The third level contains a "boss" that emits stunning lasers at the player's paddle. 
The game also includes several Power Ups that, if caught, will give the player a Shield, Multiball, Heavyball, or Extra Life effect.

### Context
This game was designed for the first project of the Spring 2020 semester of Duke's CS 308 course.
It constitutes a full coding application from start to finish, with an emphasis on studying clean code principles. 

### Design Goals
The project implementation operated along three core goals:

**Smooth Level Flow:** Ensure that the player can choose between levels and transition between them efficiently, in both back- and front-ends.

**Simple Power Up Implementation:** Ensure that Power Ups of different varieties can be added with relative ease, following similar and simple behavior.

**Intentional Collisions and Rebounds:** Have the ball bounce in patterns similar to those seen in professionally-designed breakout games (i.e. a collision with the side/corner of an object would induce a trajectory at a sharper angle).

### High-Level Design

**Main:**
The Main class stores constant values for the game scenes and gameplay. It starts the application, retrieving scene information from
the MenuPage and GameManager classes and begins the game loop.

Once in game, it repeatedly retrieves the updated scenes for each step.

It also handles key presses for cheat codes. 


**MenuPage:**
The MenuPage class handles the creation of the Menu screen scenes, with the different buttons. It has a method for each screen of the menu, including
Main Menu, Level Selector, and Help.

In creating these scenes, MenuPage makes use of instances of MenuBox, MenuText, and IconView as needed.

Upon clicking a MenuBox button, MenuPage handles sending the scene to the relevant controller to generate that scene.


**GameManager:**
The GameManager class handles all interactions involved during gameplay, including paddle movement, ball movement, collisions, and scores.

The GameManager class begins by resetting instance variables and using setLevel() for the current level, then using that information to populate the scene.
It steps through the scene, moving the different elements and checking for collisions and updates. 

The GameManager has different methods to handle collisions, Power Ups when caught, and launching balls, processing them accordingly.

In creating these scenes, MenuPage makes use of instances of Paddle, Powerup, Brick, Ball, Enemy, Laser, and ToolBar.

**Toolbar:**
The ToolBar class includes all the items at the top of the game screen, including the number of lives, the score, and the Quit button. It is refreshed as needed
to account for changes in score and lives.

The ToolBar class also includes part of the only known bug in the program, where if the Quit button is clicked (or if the game terminates normally), 
the different menu screens cannot all be accessed.

In creating its nodes, ToolBar makes use of instances of IconView, ImageView, Rectangle, and MenuText.

### Assumptions and Decisions
To simplify my implementation, I made a few key design decisions.

Primarily, I had Main process all user input, and then pass it as necessary to the relevant objects. This constituted a unidirectional pipeline. It may have created some longer method calls,
but it ensured that Main had access to all the information it needed to make other calls (including Powerup calls).

Early on in my design I created the MenuBox and MenuText classes. Rather than repeatedly create Rectangles and Text for my MenuPage, with many similar specifications,
I created these classes to extend Rectangle and Text, allowing these buttons to be created quickly. I did have to add additional constructors at some points to accommodate different use cases.

Additionally, I created the abstract class Entity as a superclass to Ball and Powerup. I wanted both of these to be circular elements with image skins and to move across the screen.
The movement and directions of Entity allowed for easy implementation of Ball and Powerup, just needing to add information for the Powerup ID as well as rebounding collisions and launching.

### Extension
The following outlines how to make changes to the project and add new features:

**Bugs:** The only known bug at this time is that the different MenuPage screen cannot switch between the different scenes (i.e. Help, Levels) after a game has been completed or quit. This is due to how
MenuPage and GameManger handle getMyScene() and pass it to Main, where they are interchanged to transition to the game. The majority of modifications to fix this bug would need to be handled in Main. 

**Levels:** To add additional levels, simply add more levelN.txt files to Resources and make the necessary changes to GameManager.setLevel() to load it in correctly, as well with any other elements (e.g. a new Enemy). 
The MenuPage setupLevel() would also be need to be altered to add a button for the new level, as well as adding a cheat key to switch to it in Main.handleKeyInput().

**Power Ups:** To add additional Power Ups, add a new skin filepath to Powerup.SKINS and change GameManager.NUM_POWERUPS to reflect the change + 1.
Add another if statement in GameManager.powerupHandler() to process the powerup's effect, and add any time-dependent effects to GameManager.powerupUpdate().  

**Other Changes:** Most other changes can be processed by making changes to the relevant Class's behavior within GameManager. The pipeline is described above in "High-Level Design."
