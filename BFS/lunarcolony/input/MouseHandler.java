package BFS.lunarcolony.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import BFS.lunarcolony.LunarApplet;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener
{
	public MainMenuHandler mainMenu;
	public GameHandler game;
	public InGameMenuHandler igm;
	
	@SuppressWarnings("unused")
	private LunarApplet applet;
	
	public MouseHandler(LunarApplet la)
	{
		this.applet = la;
		
		mainMenu = new MainMenuHandler(la);
		game = new GameHandler(la);
		igm = new InGameMenuHandler(la);
	}

	@Override public void mouseDragged(MouseEvent e) {  }
	@Override public void mouseMoved(MouseEvent e) 
	{
		switch(LunarApplet.state)
		{
			case MAIN_MENU:
				mainMenu.mouseMoved(e.getX(), e.getY());
				break;
			case GAME:
				game.mouseMoved(e.getX(), e.getY());
				break;
			case IN_GAME_MENU:
				igm.mouseMoved(e.getX(), e.getY());
				break;
			default: break;
		}
	}
	
	@Override public void mouseClicked(MouseEvent e) {  }
	@Override public void mousePressed(MouseEvent e) 
	{
		switch(LunarApplet.state)
		{
			case MAIN_MENU:
				mainMenu.mousePressed(e.getX(), e.getY());
				break;
			case GAME:
				game.mouseClicked(e.getX(), e.getY());
				break;
			case IN_GAME_MENU:
				igm.mouseClicked(e.getX(), e.getY());
				break;
			default: break;
		} 
	}
	@Override public void mouseReleased(MouseEvent e) {  }

	@Override public void mouseWheelMoved(MouseWheelEvent e) {  }
	@Override public void mouseEntered(MouseEvent e) {  }
	@Override public void mouseExited(MouseEvent e) {  }
}
