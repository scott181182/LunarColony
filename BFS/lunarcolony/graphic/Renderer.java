package BFS.lunarcolony.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import BFS.lunarcolony.LunarApplet;
import BFS.lunarcolony.gui.GuiElement;
import BFS.lunarcolony.input.MainMenuHandler;

import static BFS.lunarcolony.LunarApplet.GAME_SIZE;

public class Renderer 
{
	public LunarApplet applet;
	
	public Renderer(LunarApplet la)
	{
		this.applet = la;
	}
	
	public void renderMainMenu(Graphics2D g)
	{
		g.drawImage(MainMenuHandler.bgImage, 0, 0, LunarApplet.GAME_SIZE.width, LunarApplet.GAME_SIZE.height, 0, 0, MainMenuHandler.bgImage.getWidth(), MainMenuHandler.bgImage.getHeight(), null);
		if(MainMenuHandler.state == MainMenuHandler.MenuState.MAIN)
		{
			for(GuiElement ge : MainMenuHandler.mainElements)
			{
				ge.draw(g);
			}
		}
		else if(MainMenuHandler.state == MainMenuHandler.MenuState.CREDITS)
		{
			for(GuiElement ge : MainMenuHandler.creditElements)
			{
				ge.draw(g);
			}
		}
	}
	public void renderIntro(Graphics2D g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, GAME_SIZE.width, GAME_SIZE.height);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Courier New", Font.BOLD, 64));
		int x = (GAME_SIZE.width / 2) - (int)((float)getFontWidth(g) * 7.5f);
		int y = (GAME_SIZE.height / 2) + (getFontHeight(g) / 2);
		g.drawString("BixFord Studios", x, y);
	}
	public void renderLoadingScreen(Graphics2D g)
	{
		
	}
	public void renderGame(Graphics2D g)
	{
		applet.mouseHandle.game.renderGameWorld(g);
		applet.mouseHandle.game.renderGameInterface(g);
	}
	public void renderInGameMenu(Graphics2D g)
	{
		this.renderGame(g);
		
		for(GuiElement ge : applet.mouseHandle.igm.igmElements)
		{
			if(ge.isVisible()) { ge.draw(g); }
		}
	}
	public static int getFontWidth(Graphics2D g)
	{
		FontMetrics fm = g.getFontMetrics();
		return fm.charWidth(' ');
	}
	public static int getFontHeight(Graphics2D g)
	{
		FontMetrics fm = g.getFontMetrics();
		return fm.getAscent();
	}
}
