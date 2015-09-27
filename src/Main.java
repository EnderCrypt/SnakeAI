import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main
	{
	static Dimension screenSize = new Dimension(180,55);
	static Game game;
	static BufferedOutputStream fastConsole = null;
	public static void main(String[] args)
		{
		fastConsole = new BufferedOutputStream(System.out);
		parameters(args);
		game = new Game(screenSize);
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
			try{Thread.sleep(50);}catch(InterruptedException et){et.printStackTrace();}
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
				case "-s": //change screen size
					screenSize = new Dimension(Integer.parseInt(itr.next()),Integer.parseInt(itr.next())); 
				break;
				case "-h": //get help
				case "--h":
				case "-help":
				case "--help":
				default:
					System.out.println("-h help");
					System.out.println("	lists all the help\n");
					System.exit(0);
				break;
				}
			}
		}
	}
