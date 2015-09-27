import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
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
	int updateCount = 0;
	int foodCount = 0;
	long updateTime = 0;
	long drawTime = 0;
	Game(Dimension gameSize)
		{
		this.gameSize = gameSize;
		map = generateMap(gameSize);
		while (snakes.size() < 20)
			{
			Point position = new Point((int)(Math.random()*gameSize.width),(int)(Math.random()*gameSize.height));
			if (map[position.x][position.y].equals(MapObject.FLOOR))
				{
				snakes.add(new Snake(position, this));
				}
			}
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
		updateTime = System.currentTimeMillis();
		updateCount++;
		Iterator<Snake> itr = snakes.iterator();
		while(itr.hasNext())
			{
			Snake snake = itr.next();
			snake.update();
			snake.checkFood();
			snake.collisionCheck(itr);
			}
		if (foodCount < Math.max(5,snakes.size()))
			{
			addFood();
			}
		updateTime = System.currentTimeMillis()-updateTime;
		}
	public void addFood()
		{
		while (true)
			{
			Point point = new Point((int) (Math.random()*(gameSize.width)),(int) (Math.random()*(gameSize.height)));
			if (map[point.x][point.y].equals(MapObject.FLOOR))
				{
				for (Snake snake: snakes)
					{
					if (point.equals(snake.position))
						{
						continue;
						}
					for (Point body : snake.body)
						{
						if (point.equals(body))
							{
							continue;
							}
						}
					}
				map[point.x][point.y] = MapObject.FOOD;
				foodCount++;
				return;
				}
			}
		}
	public void drawScreen() throws IOException
		{
		drawTime = System.currentTimeMillis();
		int bodies = 0;
		int longestBody = 0;
		// create 2D array for all snakes to save efficiency
		int[][] snakeMap = new int[gameSize.width][gameSize.height];
		for (Snake snake: snakes)
			{
			int size = snake.body.size();
			bodies += size;
			if (size > longestBody)
				{
				longestBody = size;
				}
			snakeMap[snake.position.x][snake.position.y] = 1;
			for (Point point : snake.body)
				{
				snakeMap[point.x][point.y] = 2;
				}
			}
		// draw
		Main.fastConsole.write('\n');
		for (int y=0;y<gameSize.height;y++)
			{
			//String out = "";
			for (int x=0;x<gameSize.width;x++)
				{
				MapObject tile = map[x][y];
				switch (tile)
					{
					case FLOOR:
						int snakeMapInt = snakeMap[x][y];
						if (snakeMapInt > 0)
							{
							if (snakeMapInt == 1)
								{// head
								Main.fastConsole.write('o');
								}
							else
								{// body
								Main.fastConsole.write('.');
								}
							}
						else
							{
							Main.fastConsole.write(tile.getChar());
							}
					break;
					default:
						Main.fastConsole.write(tile.getChar());
					}
				}
			Main.fastConsole.write('\n');
			}
		drawTime = System.currentTimeMillis()-drawTime;
		double exactAvg = (double)bodies/(double)snakes.size();
		double avg = Math.round(exactAvg*10.0)/10.0;
		String outData = ("Snakes: "+(snakes.size())+" / Bodies: "+bodies+" (Longest: "+longestBody+", Avg: "+avg+") / Food: "+foodCount+" / updateDelta: "+updateTime+" Ms / drawDelta: "+drawTime+" Ms");
		Main.fastConsole.write(outData.getBytes());
		Main.fastConsole.flush();
		}
	}