import java.awt.Point;

public enum EnumSnakeDirection
	{
	RIGHT(new Point(1,0)),
	UP(new Point(0,-1)),
	LEFT(new Point(-1,0)),
	DOWN(new Point(0,1)),
	;
	private Point movement;
	private EnumSnakeDirection(Point movement)
		{
		this.movement = movement;
		}
	public Point getMovement()
		{
		return movement.getLocation();
		}
	public static EnumSnakeDirection getRandom()
		{
		EnumSnakeDirection[] enums = EnumSnakeDirection.values();
		int index = (int)(Math.random()*enums.length);
		return enums[index];
		}
	public int getX()
		{
		return movement.x;
		}
	public int getY()
		{
		return movement.y;
		}
	public EnumSnakeDirection getLeft()
		{
		int movementIndex = this.ordinal()+1;
		if (movementIndex > 3)
			{
			movementIndex = 0;
			}
		return EnumSnakeDirection.values()[movementIndex];
		}
	public EnumSnakeDirection getRight()
		{
		int movementIndex = this.ordinal()-1;
		if (movementIndex < 0)
			{
			movementIndex = 3;
			}
		return EnumSnakeDirection.values()[movementIndex];
		}
	}
