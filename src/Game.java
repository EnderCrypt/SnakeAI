import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author EnderCrypt
 * Game class, keeps track of everything related to the game
 */
public class Game
	{
	Dimension gameSize;
	MapObject[][] map;
	List<Snake> snakes = new ArrayList<>();
	Game(Dimension gameSize)
		{
		this.gameSize = gameSize;
		map = generateMap(gameSize);
		// TODO: place snakes
		}
	public MapObject[][] generateMap(Dimension mapSize)
		{
		MapObject[][] map = new MapObject[mapSize.width][mapSize.height];
		// create default map (just floors)
		for (int x=0;x<gameSize.width;x++)
			{
			for (int y=0;y<gameSize.height;y++)
				{
				map[x][y] = MapObject.FLOOR;
				}
			}
		// creates walls
		for (int x=0;x<gameSize.width;x++)
			{
			map[x][0] = MapObject.WALL;
			map[x][gameSize.height-1] = MapObject.WALL;
			}
		for (int y=0;y<gameSize.height;y++)
			{
			map[0][y] = MapObject.WALL;
			map[gameSize.width-1][y] = MapObject.WALL;
			}
		return map;
		}
	public void update()
		{
		Iterator<Snake> itr = snakes.iterator();
		while(itr.hasNext())
			{
			Snake snake = itr.next();
			// TODO: update snake
			}
		// TODO: create random food
		}
	public void drawScreen()
		{
		// TODO: draw map
		// TODO: draw snakes
		}
	}
