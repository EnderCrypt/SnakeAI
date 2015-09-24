import java.awt.Dimension;
import java.awt.Point;
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
		while (snakes.size() < 10)
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
		Iterator<Snake> itr = snakes.iterator();
		while(itr.hasNext())
			{
			Snake snake = itr.next();
			snake.update();
			snake.collisionCheck(itr);
			}
		// TODO: check if eaten food
		// TODO: create random food
		}
	public void drawScreen()
		{
		int bodies = 0;
		// create 2D array for all snakes to save efficiency
		int[][] snakeMap = new int[gameSize.width][gameSize.height];
		for (Snake snake: snakes)
			{
			bodies += snake.body.size();
			snakeMap[snake.position.x][snake.position.y] = 1;
			for (Point point : snake.body)
				{
				snakeMap[point.x][point.y] = 2;
				}
			}
		// draw
		System.out.println();
		for (int y=0;y<gameSize.height;y++)
			{
			String out = "";
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
								out = out+'o';
								}
							else
								{// body
								out = out+'.';
								}
							}
						else
							{
							out = out+tile.getChar();
							}
					break;
					default:
						out = out+tile.getChar();
					}
				}
			System.out.println(out);
			}
		System.out.print("Snakes: "+(snakes.size())+" ("+bodies+" bodies)");
		}
	}
