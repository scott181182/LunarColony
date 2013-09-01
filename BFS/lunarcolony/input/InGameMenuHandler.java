package BFS.lunarcolony.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import BFS.lunarcolony.LunarApplet;
import BFS.lunarcolony.gui.*;

public class InGameMenuHandler implements KeyListener, ActionListener
{
	public ArrayList<GuiElement> igmElements;
	
	private LunarApplet applet;
	private GuiLabel scorePanel, scoreLabel;
	private GuiButton closeButton;
	private boolean toClose = false;
	
	public InGameMenuHandler(LunarApplet la)
	{
		this.applet = la;
		
		igmElements = new ArrayList<>();
		GuiLabel gl = new GuiLabel("", true, 250, 150, 300, 300);
		gl.setColors(Color.GRAY, Color.BLACK, Color.GRAY);
		igmElements.add(gl);
		GuiButton gb = new GuiButton("RETURN", "Arial", Font.PLAIN, 260, 160, 280, 35);
		gb.setActionCommand("RETURN"); gb.addListener(this);
		igmElements.add(gb);
		GuiButton gb1 = new GuiButton("EXIT GAME", 260, 405, gb);
		gb1.setActionCommand("EXIT"); gb1.addListener(this);
		igmElements.add(gb1);
		gb1 = new GuiButton("SCORE", 260, 205, gb);
		gb1.setActionCommand("SCORE"); gb1.addListener(this);
		igmElements.add(gb1);
		scorePanel = new GuiLabel("", true, 200, 250, 400, 150);
		scorePanel.setColors(Color.GRAY, Color.BLACK, Color.GRAY); scorePanel.setVisible(false);
		igmElements.add(scorePanel);
		scoreLabel = new GuiLabel("" + (applet.world == null ? 0 : applet.world.score), false, 200, 250, 400, 50);
		scoreLabel.setVisible(false); igmElements.add(scoreLabel);
		closeButton = new GuiButton("CLOSE", 250, 325, 300, 50);
		closeButton.setVisible(false); closeButton.setActionCommand("CLOSE"); closeButton.addListener(this);
		igmElements.add(closeButton);
	}

	public void mouseMoved(int mX, int mY)
	{
		
	}
	public void mouseClicked(int mX, int mY)
	{
		for(int i = igmElements.size() - 1; i >= 0; i--)
		{
			GuiElement ge = igmElements.get(i);
			if(ge instanceof GuiButton)
			{
				if(ge.bounds.contains(mX, mY) && ge.isVisible()) { ((GuiButton)ge).click(); }
			}
		}
	}
	
	@Override public void keyTyped(KeyEvent e) {  }
	@Override public void keyPressed(KeyEvent e) 
	{
		if(LunarApplet.state == LunarApplet.GameState.IN_GAME_MENU)
		{

		}
	}
	@Override public void keyReleased(KeyEvent e) {  }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case "RETURN": 
				LunarApplet.state = LunarApplet.GameState.GAME;
				break;
			case "EXIT":
				igmElements.get(igmElements.indexOf(closeButton)).setVisible(true);
				igmElements.get(igmElements.indexOf(scorePanel)).setVisible(true);
				igmElements.get(igmElements.indexOf(scoreLabel)).setVisible(true);
				toClose = true;
				break;
			case "SCORE":
				igmElements.get(igmElements.indexOf(closeButton)).setVisible(true);
				igmElements.get(igmElements.indexOf(scorePanel)).setVisible(true);
				igmElements.get(igmElements.indexOf(scoreLabel)).setVisible(true);
				break;
			case "CLOSE":
				igmElements.get(igmElements.indexOf(closeButton)).setVisible(false);
				igmElements.get(igmElements.indexOf(scorePanel)).setVisible(false);
				igmElements.get(igmElements.indexOf(scoreLabel)).setVisible(false);
				if(toClose) { toClose = false; LunarApplet.state = LunarApplet.GameState.MAIN_MENU; }
				break;
			default: break;
		}
		
	}
}
