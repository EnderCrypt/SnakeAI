import java.awt.Point;

/**
 * @author EnderCrypt
 * improved eating AI, basic avoidant techniques, tries to spy for food 20 tiles to the left and right
 */
public class ImprovedEatingAI implements AI
	{
	@Override
	public void update(Snake snake)
		{
		if (snake.directionBlocked(snake.direction))
			{
			if (!(
					snake.directionBlocked(snake.direction.getLeft()) && 
					snake.directionBlocked(snake.direction.getRight()) ))
				{ // both dirs are open
				Point leftPoint = snake.position.getLocation();
				EnumSnakeDirection leftDir = snake.direction.getLeft();
				
				Point rightPoint = snake.position.getLocation();
				EnumSnakeDirection rightDir = snake.direction.getRight();
				for (int i=0;i<20;i++)
					{
					leftPoint.translate(leftDir.getX(),leftDir.getY());
					if (Snake.isBlocked(leftPoint))//if (Main.game.map[leftPoint.x][leftPoint.y].equals(MapObject.WALL))
						{
						snake.direction = rightDir;
						return;
						}
					rightPoint.translate(rightDir.getX(),rightDir.getY());
					if (Snake.isBlocked(rightPoint))//if (Main.game.map[rightPoint.x][rightPoint.y].equals(MapObject.WALL))
						{
						snake.direction = leftDir;
						return;
						}
					}
				// all free, make random choice
				if (Math.random() < 0.5)
					snake.direction = snake.direction.getRight();
				else
					snake.direction = snake.direction.getLeft();
				}
			else
				{ // only one direction is valid
				if (snake.directionBlocked(snake.direction.getLeft()))
					snake.direction = snake.direction.getRight();
				else
					snake.direction = snake.direction.getLeft();
				}
			}
		else
			{
			Point nextPos = snake.direction.getMovement();
			nextPos.translate(snake.position.x, snake.position.y);
			if (!Main.game.map[nextPos.x][nextPos.y].equals(MapObject.FOOD)) // dont check for food on the sides if theres already food just infront of you
				{
				Point leftPoint = snake.position.getLocation();
				EnumSnakeDirection leftDir = snake.direction.getLeft();
				boolean moveLeft = (!snake.directionBlocked(leftDir));
				
				Point rightPoint = snake.position.getLocation();
				EnumSnakeDirection rightDir = snake.direction.getRight();
				boolean moveRight = (!snake.directionBlocked(rightDir));
				for (int i=0;i<20;i++)
					{
					if (moveLeft)
						{
						leftPoint.translate(leftDir.getX(),leftDir.getY());
						if (Main.game.map[leftPoint.x][leftPoint.y].equals(MapObject.FOOD))
							{
							snake.direction = leftDir;
							return;
							}
						if (Snake.isBlocked(leftPoint))//if (Main.game.map[leftPoint.x][leftPoint.y].equals(MapObject.WALL))
							{
							moveLeft = false;
							}
						}
					if (moveRight)
						{
						rightPoint.translate(rightDir.getX(),rightDir.getY());
						if (Main.game.map[rightPoint.x][rightPoint.y].equals(MapObject.FOOD))
							{
							snake.direction = rightDir;
							return;
							}
						if (Snake.isBlocked(rightPoint))//if (Main.game.map[rightPoint.x][rightPoint.y].equals(MapObject.WALL))
							{
							moveRight = false;
							}
						}
					}
				}
			}
		}
	}
