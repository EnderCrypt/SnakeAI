import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Main
	{
	static Dimension screenSize = new Dimension(180,55);
	static Game game;
	static BufferedOutputStream fastConsole = null;
	public static void main(String[] args)
		{
		fastConsole = new BufferedOutputStream(System.out);
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
	}
