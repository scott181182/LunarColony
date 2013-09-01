package BFS.lunarcolony;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import javax.imageio.ImageIO;

import BFS.lunarcolony.graphic.Renderer;
import BFS.lunarcolony.input.MouseHandler;
import BFS.lunarcolony.world.Tile;
import BFS.lunarcolony.world.World;
import BFS.util.MethodThread;

public class LunarApplet extends Applet implements Runnable
{
	public static final Dimension GAME_SIZE = new Dimension(800, 600);
	
	private static final long serialVersionUID = 1L;
	
	public static Renderer rend;
	public static GameState state = GameState.INTRO;
	
	public MouseHandler mouseHandle;
	public World world;
	
	private static int ticker = 0;
	private boolean isFocused = true, isPaused = false, isRunning = false;
	private Image imageBuffer;
	
	public static enum GameState { INTRO, LOADING, MAIN_MENU, GAME, IN_GAME_MENU; }
	
	public LunarApplet()
	{
		this.setMinimumSize(GAME_SIZE);
	}
	@Override public void init()
	{
		System.out.println("Initializing...");

		isRunning = true;
		imageBuffer = this.createVolatileImage(GAME_SIZE.width, GAME_SIZE.height);
		rend = new Renderer(this);
		
		System.out.println("Initialized");
	}
	@Override public void start()
	{
		System.out.println("Starting...");
		
		isPaused = false;
		isFocused = true;
		new Thread(this).start();
		
		System.out.println("Started");
	}
	public void load()
	{
		System.out.println("Loading...");
		
		mouseHandle = new MouseHandler(this);
		this.addMouseListener(mouseHandle);
		this.addMouseMotionListener(mouseHandle);
		this.addMouseWheelListener(mouseHandle);
		this.addKeyListener(mouseHandle.game);
		this.addKeyListener(mouseHandle.igm);
		
		try
		{
			Image iconSheet = ImageIO.read(Tile.class.getResource("/BFS/lunarcolony/res/tiles/terrain.png"));
			int i = 0;
			for(int y = 0; y < 8; y++)
			{
				for(int x = 0; x < 8; x++)
				{
					Tile.icons[i] = createImage(new FilteredImageSource(iconSheet.getSource(), new CropImageFilter(x * World.TILE_SIZE, y * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE)));
					i++;
				}
			}
		} catch(IOException ioe) { ioe.printStackTrace(); }
		try
		{
			Image highlights = ImageIO.read(Tile.class.getResource("/BFS/lunarcolony/res/effects/highlights.png"));
			int i = 0;
			for(int y = 0; y < 4; y++)
			{
				for(int x = 0; x < 4; x++)
				{
					World.highlights[i] = createImage(new FilteredImageSource(highlights.getSource(), new CropImageFilter(x * World.TILE_SIZE, y * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE)));
					i++;
				}
			}
		} catch(IOException ioe) { ioe.printStackTrace(); }
		try
		{
			Image ent = ImageIO.read(Tile.class.getResource("/BFS/lunarcolony/res/entities/entities.png"));
			int i = 0;
			for(int y = 0; y < 4; y++)
			{
				for(int x = 0; x < 4; x++)
				{
					World.entImg[i] = createImage(new FilteredImageSource(ent.getSource(), new CropImageFilter(x * World.TILE_SIZE, y * World.TILE_SIZE, World.TILE_SIZE, World.TILE_SIZE)));
					i++;
				}
			}
		} catch(IOException ioe) { ioe.printStackTrace(); }

		System.out.println("Loaded");
	}
	@Override public void run()
	{
		try
		{
			MethodThread loading = new MethodThread(this, this.getClass().getMethod("load", (Class<?>[])null), (Object[])null);
			loading.start();
		}
		catch(NoSuchMethodException nsme) { nsme.printStackTrace(); }
		
		while(isRunning)
		{
			if(isFocused)
			{
				if(state == GameState.GAME)
				{
					if(!isPaused) { mouseHandle.game.tick(); }
				}
				else { tick(); }
				
				render();
				
				try { Thread.sleep(20); }
				catch(InterruptedException ie) { ie.printStackTrace(); }
			}
			else
			{
				if(state == GameState.GAME) { state = GameState.IN_GAME_MENU; }
				
				try { Thread.sleep(100); }
				catch(InterruptedException ie) { ie.printStackTrace(); }
			}
		}
	}
	public void tick()
	{
		if(state == GameState.INTRO)
		{
			if(ticker >= 250) { ticker = 0; state = GameState.MAIN_MENU; }
			else { ticker++; }
		}
		else if(state == GameState.MAIN_MENU)
		{
			
		}
	}
	public void render()
	{
		Graphics2D g = (Graphics2D)imageBuffer.getGraphics();
		g.setColor(Color.WHITE); g.fillRect(0, 0, GAME_SIZE.width, GAME_SIZE.height);
		switch(state)
		{
			case INTRO: rend.renderIntro(g); break;
			case MAIN_MENU: rend.renderMainMenu(g); break;
			case LOADING: rend.renderLoadingScreen(g); break;
			case GAME: rend.renderGame(g); break;
			case IN_GAME_MENU: rend.renderInGameMenu(g); break;
		}
		g = (Graphics2D)this.getGraphics();
		g.drawImage(imageBuffer, 0, 0, GAME_SIZE.width, GAME_SIZE.height, 0, 0, GAME_SIZE.width, GAME_SIZE.height, null);
		g.dispose();
	}
	@Override public void stop()
	{
		System.out.println("Stopping...");
		
		pause();
		isFocused = false;

		System.out.println("Stopped");
	}
	@Override public void destroy()
	{
		System.out.println("Destroying...");
		
		this.isRunning = false;

		System.out.println("Destroyed");
	}
	public void pause() { this.isPaused = true; }
}
