import java.awt.Dimension;

public class Main
	{
	static Dimension screenSize = new Dimension(182,56);
	static Game game;
	public static void main(String[] args)
		{
		game = new Game(screenSize);
		System.out.println("test");
		while (true)
			{
			game.update();
			game.drawScreen();
			try{Thread.sleep(500);}catch(InterruptedException e){e.printStackTrace();}
			}
		}
	}
