import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EnderCrypt
 * the snake class, each snake has its own body and AI
 */
public class Snake
	{
	Point position = new Point();
	List<Point> body = new ArrayList<>();
	boolean addBody = false;
	AI snakeAI;
	Snake(Point position)
		{
		this.position = position;
		snakeAI = new DefaultAI();
		}
	public void update()
		{
		// TODO: ai integration
		// TODO: movement code
		}
	public void move(Point newPos)
		{
		body.add(0, position.getLocation());
		position = newPos;
		
		if (!addBody)
			{
			body.remove(body.size()-1);
			}
		}
	}
