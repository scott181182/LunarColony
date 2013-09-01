package BFS.lunarcolony.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class GuiClickable extends GuiElement
{
	private ArrayList<ActionListener> listeners;
	private String actionCommand;
	
	public GuiClickable(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		listeners = new ArrayList<>();
	}
	public boolean addListener(ActionListener al) { return listeners.add(al); }
	public boolean removeListener(ActionListener al) { return listeners.remove(al); }
	public void setActionCommand(String par1) { this.actionCommand = par1; }
	
	public void click()
	{
		for(ActionListener al : listeners)
		{
			al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, actionCommand));
		}
	}
}
