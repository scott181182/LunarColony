package BFS.lunarcolony.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import BFS.lunarcolony.LunarApplet;
import BFS.lunarcolony.gui.*;
import BFS.lunarcolony.world.World;

public class MainMenuHandler implements ActionListener
{
	//public static MainMenuHandler instance = new MainMenuHandler();
	
	private final String PLAY = "PLAY", HOW_TO = "HOW TO PLAY", CREDITS = "CREDITS", BACK = "BACK";
	
	public static MenuState state = MenuState.MAIN;
	public static ArrayList<GuiElement> mainElements;
	public static ArrayList<GuiElement> creditElements;
	public static BufferedImage bgImage;
	
	private LunarApplet applet;
	
	public static enum MenuState { MAIN, CREDITS }
	public MainMenuHandler(LunarApplet la)
	{
		this.applet = la;
		try { bgImage = ImageIO.read(this.getClass().getResource("/BFS/lunarcolony/res/mainMenu.jpg")); }
		catch (IOException ioe) { ioe.printStackTrace(); }
		
		mainElements = new ArrayList<>();
		GuiButton gb = new GuiButton(HOW_TO, "Ariel", Font.PLAIN, 0, 0, 0, 75);
		gb.setActionCommand(HOW_TO); gb.addListener(this);
		int x = (LunarApplet.GAME_SIZE.width / 2) - (gb.bounds.width / 2);
		
		GuiButton gb2 = new GuiButton(PLAY, x, 300, gb);
		gb2.setActionCommand(PLAY); gb2.addListener(this);
		mainElements.add(gb2);
		gb2 = new GuiButton(HOW_TO, x, 400, gb);
		gb2.setActionCommand(HOW_TO); gb2.addListener(this);
		mainElements.add(gb2);
		gb2 = new GuiButton(CREDITS, x, 500, gb);
		gb2.setActionCommand(CREDITS); gb2.addListener(this);
		mainElements.add(gb2);
		GuiLabel gl = new GuiLabel("Lunar Colony", "Futura", Font.PLAIN, false, 0, 100, 800, 100);
		gl.setColors(null, null, Color.WHITE);
		mainElements.add(gl);
		
		creditElements = new ArrayList<>();
		gl = new GuiLabel("Scott Fasone - Lead Programmer", "Ariel", Font.BOLD, true, 0, 0, 0, 35);

		x = (LunarApplet.GAME_SIZE.width / 2) - (gl.bounds.width / 2);
		GuiLabel gl2 = new GuiLabel("Scott Fasone - Lead Programmer", x, 100, gl);
		gl2.setColors(null, null, Color.WHITE);
		gb = new GuiButton(BACK, 250, 500, 300, 50);
		gb.setActionCommand(BACK); gb.addListener(this);
		creditElements.add(gl2); creditElements.add(gb);
	}
	public void mouseMoved(int mX, int mY)
	{
		
	}
	public void mousePressed(int mX, int mY)
	{
		if(state == MenuState.MAIN)
		{
			for(GuiElement ge : mainElements)
			{
				if(ge.bounds.contains(mX, mY))
				{
					if(ge instanceof GuiClickable)
					{
						((GuiClickable)ge).click();
					}
				}
			}
		}
		else if(state == MenuState.CREDITS)
		{
			for(GuiElement ge : creditElements)
			{
				if(ge.bounds.contains(mX, mY))
				{
					if(ge instanceof GuiClickable)
					{
						((GuiClickable)ge).click();
					}
				}
			}
		}
	}
	
	@Override public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case PLAY:
				applet.world = new World();
				applet.world.worldImage = applet.createImage(World.WORLD_SIZE.width * World.TILE_SIZE, World.WORLD_SIZE.height * World.TILE_SIZE);
				applet.mouseHandle.game.init();
				LunarApplet.state = LunarApplet.GameState.GAME;
				break;
			case HOW_TO:
				System.out.println(HOW_TO);
				break;
			case CREDITS:
				state = MenuState.CREDITS;
				break;
			case BACK:
				state = MenuState.MAIN;
				break;
			default: break;
		}
	}
}
