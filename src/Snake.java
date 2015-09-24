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
	AI snakeAI;
	Snake(Point position)
		{
		this.position = position;
		snakeAI = new DefaultAI();
		}
	}
