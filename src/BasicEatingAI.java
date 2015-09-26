import java.awt.Point;

/**
 * @author EnderCrypt
 * Standard eating AI, basic avoidant techniques, tries to spy for food 20 tiles to the left and right
 */
public class BasicEatingAI implements AI
	{
	@Override
	public void update(Snake snake)
		{
		if (snake.directionBlocked(snake.direction))
			{
			if (Math.random() < 0.5)
				{
				if (snake.directionBlocked(snake.direction.getLeft()))
					snake.direction = snake.direction.getRight();
				else
					snake.direction = snake.direction.getLeft();
				}
			else
				{
				if (snake.directionBlocked(snake.direction.getRight()))
					snake.direction = snake.direction.getLeft();
				else
					snake.direction = snake.direction.getRight();
				}
			}
		else
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
					if (Main.game.map[leftPoint.x][leftPoint.y].equals(MapObject.WALL))
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
					if (Main.game.map[rightPoint.x][rightPoint.y].equals(MapObject.WALL))
						{
						moveRight = false;
						}
					}
				}
			}
		}
	}
