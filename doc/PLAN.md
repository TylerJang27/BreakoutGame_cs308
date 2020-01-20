# Game Plan
## Tyler Jang

### Breakout Variant
I will opt for a version of breakout most similar to Super Breakout. This form uses the traditional brick-breaker style, without tying it into another popular arcade model.

It combines a variety of different power ups and bricks, to create an amusing and varied experience. I will use Super Breakout as my starting point and work from there. 

### General Level Descriptions
My game will include three levels. The first two will be standard brick-breaker models, while the third will be a basic boss fight.

Per the standard brick-breaker model, the side and top walls will statically deflect the bouncer. If the bouncer hits the bottom of the level, a life will be lost.

Upon starting a level or after a life is lost, the pad will go to the center, and the player will press "SPACE" to release the bouncer. The pad may be moved with the arrow keys.

**Level 1**
From top to bottom, the first level will be arranged with a 1-row space, 3 rows of medium bricks, a 1-row space, and 1 row of easy bricks.
This will be a basic level, with power ups dropping from the occasional brick and the object being to clear all of the bricks.

**Level 2**
The second level will be arranged with an isosceles triangle with its base flat against the top of the screen, with hard bricks at its center. There will then be a row of medium bricks below it.
This will be a basic level, with power ups dropping from the occasional brick and the object being to clear all of the bricks.

**Level 3**
The last level will be a boss level. A circular red ball will represent the enemy boss, with a health bar displayed at the top of the screen. A row of medium bricks will be present below it.
Upon hitting the boss 20 times with the bouncer and clearing the row of bricks, the level will terminate and the game will finish.
Power ups will drop from the bottom row of bricks.

The boss will regularly drop lasers down that pass through the bricks. Upon hitting the pad, the player will be immobilized for a short period.
### Bricks Ideas
There will be three varieties of rectangular bricks. Blue bricks will have 1 hp. Red bricks will have 2 hp. Grey bricks will have 3 hp. Upon hitting a brick, its hp will degrade to the next category until it disappears.

Bricks will randomly drop power ups upon being destroyed.

If the bouncer collides with a flat side of the rectangle, it will bounce as if against a flat surface. If it collides near the corner, its direction will shift slightly.

### Power Up Ideas
Power ups will fall downward from some destroyed bricks. Power ups will all have the same skin until caught by the pad.

**Multiball**

The player will release another bouncer, in addition to the first. Only when all bouncers hit the bottom of the level will the player lose a life.

**Heavy ball**

The bouncer will degrade brick and boss hp by 2 for a short period.

**Extra life**

The player will gain an extra life (max 5).

**Shield**

The player will be invulnerable to the boss's lasers for a short time.

### Cheat Key Ideas
There will be a few cheat codes that may be used by the player and for debugging purposes.

**8: Critical Hit**

All bricks and the boss (if present) will take one hp damage.

**P: Power Up**

The player will gain the effect of all power ups for the remainder of the game.

### Something Extra
The addition will be the boss of the third level, with its menacing lasers and significant hp. This adds an additional NPC to an otherwise traditional brick-breaker model.