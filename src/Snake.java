import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author EnderCrypt
 * the snake class, each snake has its own body and AI
 */
public class Snake
	{
	Point position = new Point();
	List<Point> body = new ArrayList<>();
	EnumSnakeDirection direction;
	boolean addBody = true;
	AI snakeAI;
	Game game;
	Snake(Point position, Game game)
		{
		this.position = position;
		this.game = game;
		snakeAI = new BasicAvoidingAI();
		// start moving in random direction
		direction = EnumSnakeDirection.getRandom();
		}
	public void update()
		{
		snakeAI.update(this);
		Point movement = direction.getMovement();
		move(new Point(position.x+movement.x, position.y+movement.y));
		}
	public void collisionCheck(Iterator<Snake> itr)
		{
		if (Main.game.map[position.x][position.y].equals(MapObject.WALL))
			{
			itr.remove();
			return;
			}
		for (Snake snake: game.snakes)
			{			
			if (snake != this)
				{
				if (position.equals(snake.position))
					{
					itr.remove();
					return;
					}
				}
			for (Point point : snake.body)
				{
				if (position.equals(point))
					{
					itr.remove();
					return;
					}
				}
			}
		}
	public boolean directionBlocked(EnumSnakeDirection dirEnum)
		{
		Point nextPos = dirEnum.getMovement();
		nextPos.translate(position.x, position.y);
		return Snake.isBlocked(nextPos);
		}
	public static boolean isBlocked(Point tile)
		{
		if (Main.game.map[tile.x][tile.y].equals(MapObject.WALL))
			{
			return true;
			}
		for (Snake snake: Main.game.snakes)
			{
			if (tile.equals(snake.position))
				{
				return true;
				}
			for (Point point : snake.body)
				{
				if (tile.equals(point))
					{
					return true;
					}
				}
			}
		return false;
		}
	public void move(Point newPos)
		{
		body.add(0, position.getLocation());
		position = newPos;
		
		if (!addBody)
			{
			body.remove(body.size()-1);
			}
		addBody = false;
		if (Math.random() < 0.1)
			{
			addBody = true;
			}
		}
	}
