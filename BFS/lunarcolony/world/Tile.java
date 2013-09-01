package BFS.lunarcolony.world;

import java.awt.Image;

public class Tile
{
	public static Tile[] tiles = new Tile[64];
	
	public int blockID;
	
	private String name;
	
	public static Image[] icons = new Image[64];
	
	public static final Tile moonstone = new Tile(1).setName("Moon Stone");
	public static final Tile cheese = new Tile(2).setName("Cheese");
	public static final Tile moondust = new Tile(3).setName("Moon Dust");
	
	public static final Tile lander = new Tile(8).setName("Lander");
	
	public Tile(int blockID)
	{
		this.blockID = blockID;
		
		tiles[blockID] = this;
	}
	public Tile setName(String n) { this.name = n; return this; }
	public String getName() { return this.name; }
}
