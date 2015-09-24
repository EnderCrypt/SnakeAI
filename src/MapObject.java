/**
 * @author EnderCrypt
 * Enum about all objects that can be found on the map
 */
public enum MapObject
	{
	FLOOR(' '),
	WALL('H'),
	FOOD('%');
	
	private char icon;
	private MapObject(char icon)
		{
		this.icon = icon;
		}
	public char getChar()
		{
		return icon;
		}
	}
