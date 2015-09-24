/**
 * @author EnderCrypt
 * The standard default ai, still have to plan methods and parameters
 */
public class BasicAvoidingAI implements AI
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
		}
	}
