package BFS.lunarcolony.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GuiElement
{
	public Rectangle bounds;
	
	protected static Image img = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
	
	private boolean visible = true;
	
	public GuiElement(int x, int y, int w, int h)
	{
		this.bounds = new Rectangle(x, y, w, h);
	}
	
	public abstract void draw(Graphics2D g);
	
	public void setVisible(boolean par1) { this.visible = par1; }
	public boolean isVisible() { return this.visible; }
}
