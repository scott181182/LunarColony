package BFS.lunarcolony.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import BFS.lunarcolony.LunarApplet;
//Nolan
import BFS.lunarcolony.entity.Astronaut;
import BFS.lunarcolony.gui.GuiElement;
import BFS.lunarcolony.gui.GuiLabel;
import BFS.lunarcolony.gui.GuiProgressBar;
import BFS.lunarcolony.world.Tile;
import BFS.lunarcolony.world.World;

public class GameHandler implements KeyListener
{	
	public int offsetX, offsetY;
	
	private byte velX, velY;
	private LunarApplet applet;
	
	private ArrayList<GuiElement> interfaceElements;
	private GuiLabel creditLabel;
	private GuiProgressBar popBar;
	
	public GameHandler(LunarApplet la)
	{
		this.applet = la;
		
		interfaceElements = new ArrayList<>();
		creditLabel = new GuiLabel("$ 100000", true, 25, 25, 150, 25);
		creditLabel.setColors(Color.GRAY, Color.WHITE, Color.BLACK);
		interfaceElements.add(creditLabel);
		popBar = new GuiProgressBar(true, 25, 60, 150, 25);
		popBar.setColors(Color.GRAY, Color.WHITE, Color.GREEN, Color.BLACK);
		interfaceElements.add(popBar);
	}
	public void init()
	{
		int x = (applet.world.lander.x * World.TILE_SIZE) - 250;
		int y = (applet.world.lander.y * World.TILE_SIZE) - 200;
		offsetX = x;
		offsetY = y;
	}

	public void tick()
	{
		if(applet.world != null) { popBar.max = applet.world.popMax; popBar.lvl = applet.world.pop; }
		
		applet.world.tick();
		
		offsetX += velX;
		offsetY += velY;
		if(offsetX < 0) { offsetX = 0; }
		else if(offsetX > (World.WORLD_SIZE.width * World.TILE_SIZE) - 600) { offsetX = (World.WORLD_SIZE.width * World.TILE_SIZE) - 600; }
		if(offsetY < 0) { offsetY = 0; }
		else if(offsetY > (World.WORLD_SIZE.height * World.TILE_SIZE) - 500) { offsetY = (World.WORLD_SIZE.height * World.TILE_SIZE) - 500; }
	}
	public void renderGameWorld(Graphics2D g)
	{
		applet.world.render();
		
		g.drawImage(applet.world.worldImage, 200, 0, 800, 500, offsetX, offsetY, offsetX + 600, offsetY + 500, null);
	}
	public void renderGameInterface(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 200, 600);
		g.fillRect(200, 500, 600, 100);
		g.setColor(Color.GRAY);
		g.fillRect(0 + 2, 0 + 2, 200 - 4, 600 - 4);
		g.fillRect(200 + 2, 500 + 2, 600 - 4, 100 - 4);
		
		if(applet.world.select.x != -1 && applet.world.select.y != -1) 
		{
			Tile sel = applet.world.getTile(applet.world.select.x, applet.world.select.y);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(200 + 10, 500 + 10, 68, 68);
			g.drawImage(Tile.icons[sel.blockID], 200 + 10 + 2, 500 + 10 + 2, null);
			
			if(sel.getName() != null && !sel.getName().equals(""))
			{
				g.setFont(new Font("Arial", Font.BOLD, 16));
				g.drawString(sel.getName(), 200 + 12 + 64 + 12, 500 + 12 + 12);
			}
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 32));
			g.drawString("Nothing Selected", 200 + 12, 500 + 12 + 25);
		}
		
		for(GuiElement ge : interfaceElements) { ge.draw(g); }
	}
	
	public void mouseMoved(int mX, int mY)
	{
		if(this.inWorld(mX, mY)) { applet.world.hover = new Point(World.pixelToCoord(mX + offsetX - 200), World.pixelToCoord(mY + offsetY)); }
	}
	public void mouseClicked(int mX, int mY)
	{
		if(this.inWorld(mX, mY)) 
		{ 
			if(applet.world.select.x == applet.world.hover.x && applet.world.select.y == applet.world.hover.y) { applet.world.select = new Point(-1, -1); }
			else { applet.world.select = applet.world.hover; }
		}
	}
	@Override public void keyTyped(KeyEvent e) {  }
	@Override public void keyPressed(KeyEvent e) 
	{
		if(LunarApplet.state == LunarApplet.GameState.GAME)
		{
			if(e.getKeyCode() == KeyEvent.VK_W) { velY -= 6; }
			else if(e.getKeyCode() == KeyEvent.VK_S) { velY += 6; }
			if(e.getKeyCode() == KeyEvent.VK_D) { velX += 6; }
			else if(e.getKeyCode() == KeyEvent.VK_A) { velX -= 6; }
			
			//Testing key for movement (Nolan)
			if(e.getKeyCode() == KeyEvent.VK_M) 
			{ 
				System.out.println("Key 'M' pressed");
				Astronaut test = new Astronaut(World.entities.get(0).bounds.x, World.entities.get(0).bounds.y, 64, 64);
				World.entities.set(0, test);
				try { test.goToward(0, 0); } catch (InterruptedException e1) {	e1.printStackTrace(); }
			}
			
			if(e.getKeyCode() == KeyEvent.VK_V) { velX = 0; velY = 0; }
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) { LunarApplet.state = LunarApplet.GameState.IN_GAME_MENU; }
		}
	}
	@Override public void keyReleased(KeyEvent e) 
	{
		if(LunarApplet.state == LunarApplet.GameState.GAME)
		{
			if(e.getKeyCode() == KeyEvent.VK_W) { velY += 6; }
			else if(e.getKeyCode() == KeyEvent.VK_S) { velY -= 6; }
			if(e.getKeyCode() == KeyEvent.VK_D) { velX -= 6; }
			else if(e.getKeyCode() == KeyEvent.VK_A) { velX += 6; }
		}
	}
	
	public boolean inWorld(int x, int y)
	{
		if((x > 200 && x < LunarApplet.GAME_SIZE.width) && (y > 0 && y < 500)) { return true; }
		else { return false; }
	}
	public boolean inInterface(int x, int y)
	{
		if((x > 0 && x < 200) && (y > 500 && y < LunarApplet.GAME_SIZE.height)) { return true; }
		else { return false; }
	}
}
