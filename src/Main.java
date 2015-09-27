import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main
	{
	static Dimension screenSize = new Dimension(180,55);
	static int updateDelay = 50;
	static int spawnCount = 10;
	static Game game;
	static BufferedOutputStream fastConsole = null;
	public static void main(String[] args)
		{
		fastConsole = new BufferedOutputStream(System.out);
		parameters(args);
		game = new Game(screenSize, spawnCount);
		while (true)
			{
			game.update();
			try
				{
				game.drawScreen();
				}
			catch(IOException e)
				{
				e.printStackTrace();
				System.exit(1);
				}
			try{Thread.sleep(updateDelay);}catch(InterruptedException et){et.printStackTrace();}
			}
		}
	public static void parameters(String[] args)
		{
		// load all args
		List<String> list = Arrays.asList(args);
		Iterator<String> itr = list.iterator();
		while (itr.hasNext())
			{
			String input = itr.next().toLowerCase();
			switch(input)
				{
				case "-screen": //change screen size
					screenSize = new Dimension(Integer.parseInt(itr.next()),Integer.parseInt(itr.next())); 
				break;
				case "-speed": //change update speed
					updateDelay = Integer.parseInt(itr.next()); 
				break;
				case "-snakes": //change update speed
					spawnCount = Integer.parseInt(itr.next()); 
				break;
				case "-h": //get help
				case "--h":
				case "-help":
				case "--help":
				default:
					System.out.println("-h help");
					System.out.println("	lists all the help\n");
					System.out.println("-screen, -speed, -snakes");
					System.exit(0);
				break;
				}
			}
		}
	}
