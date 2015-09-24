import java.awt.Dimension;
import java.util.ArrayList;
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
		// TODO: generate map
		return map;
		}
	public void update()
		{
		// TODO: update snakes 
		// TODO: create random food
		}
	public void drawScreen()
		{
		// TODO: draw screen
		}
	}
