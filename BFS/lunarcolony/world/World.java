package BFS.lunarcolony.world;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import BFS.lunarcolony.entity.Astronaut;
import BFS.lunarcolony.entity.Entity;

public class World
{
	public static final Dimension WORLD_SIZE = new Dimension(20, 20);
	public static final int TILE_SIZE = 64;
	public static Image[] highlights = new Image[16];
	public static Image[] entImg = new Image[16];
	
	public int credits, score;
	public int pop, popMax;
	
	public Image worldImage;
	public Random worldRand;
	public Point hover, select, lander;
	//Static Modifier Added (Nolan)
	public static ArrayList<Entity> entities;
	
	private Tile[][] worldTiles = new Tile[WORLD_SIZE.width][WORLD_SIZE.height];
	
	public World()
	{
		worldRand = new Random();
		hover = new Point(-1, -1);
		select = new Point(-1, -1);
		entities = new ArrayList<>();
		
		for(int x = 0; x < worldTiles.length; x++)
		{
			for(int y = 0; y < worldTiles[x].length; y++)
			{
				if(worldRand.nextInt(40) == 0) { this.generateDustDeposit(x, y); }
				if(worldRand.nextInt(20) == 0) { this.worldTiles[x][y] = Tile.cheese; }
				else { worldTiles[x][y] = Tile.moonstone; }
			}
		}
		int x = worldRand.nextInt(10) + 5;
		int y = worldRand.nextInt(10) + 5;
		worldTiles[x][y] = Tile.lander;
		lander = new Point(x, y);
		for(int i = 0; i < 5; i++)
		{
			x = lander.x - 3 + worldRand.nextInt(7);
			y = lander.y - 3 + worldRand.nextInt(7);
			Astronaut astro = new Astronaut(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			entities.add(astro);
		}
		
		credits = 100000;
		pop = 5; popMax = 0;
	}
	
	public void render()
	{
		Graphics2D g = (Graphics2D)worldImage.getGraphics();
		
		for(int x = 0; x < worldTiles.length; x++)
		{
			for(int y = 0; y < worldTiles[x].length; y++)
			{
				g.drawImage(Tile.icons[getTile(x, y).blockID], x * TILE_SIZE, y * TILE_SIZE, null);
				//System.out.println(getTile(x, y).blockID);
				if(x == hover.x && y == hover.y) { g.drawImage(highlights[0], x * TILE_SIZE, y * TILE_SIZE, null); }
				if(x == select.x && y == select.y) { g.drawImage(highlights[1], x * TILE_SIZE, y * TILE_SIZE, null); }
			}
		}
		for(final Entity e : entities) { if(e != null) { e.draw(g); } }
		
		g.dispose();
	}
	public void tick()
	{
		for(final Entity e : entities) 
		{ 
			if(e != null) 
			{
				if(e instanceof Astronaut)
				{
					if(this.select.x != -1 && this.select.y != -1) { ((Astronaut)e).setGoal(new Point(select.x * TILE_SIZE, select.y * TILE_SIZE)); }
				}
				e.tick();
			}
		}
	}
	
	public Tile getTile(int x, int y) 
	{
		if(x < 0 || x >= worldTiles.length || y < 0 || y >= worldTiles[x].length) { return null; }
		else { return worldTiles[x][y]; }
	}
	public static int pixelToCoord(int x) { return x >> 6; }
	public void generateDustDeposit(int x, int y)
	{
		this.worldTiles[x][y] = Tile.moondust;
		for(int x1 = x - 1; x1 < x + 1; x1++)
		{
			for(int y1 = y - 1; y1 < y + 1; y1++)
			{
				if(x1 >= 0 && x1 < worldTiles.length && y1 >= 0 && y1 < worldTiles[x].length)
				{
					if(worldRand.nextInt(6) != 0) { worldTiles[x1][y1] = Tile.moondust; }
				}
			}
		}
		for(int x1 = x - 2; x1 < x + 2; x1++)
		{
			for(int y1 = y - 2; y1 < y + 2; y1++)
			{
				if(x1 >= 0 && x1 < worldTiles.length && y1 >= 0 && y1 < worldTiles[x].length)
				{
					int i = 0;
					if(getTile(x1 - 1, y1) == Tile.moondust) { i++; }
					if(getTile(x1, y1 - 1) == Tile.moondust) { i++; }
					if(getTile(x1 + 1, y1) == Tile.moondust) { i++; }
					if(getTile(x1, y1 + 1) == Tile.moondust) { i++; }
					
					if(worldRand.nextInt(i + 1) != 0) { worldTiles[x1][y1] = Tile.moondust; }
				}
			}
		}
	}
}
