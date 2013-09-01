package BFS.lunarcolony.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity
{
	public Rectangle bounds;
	
	private int speed;
	private Image img;
	
	public Entity()
	{
		bounds = new Rectangle(-1, -1, 0, 0);
		speed = 0;
	}
	public Entity(int x, int y, int w, int h)
	{
		bounds = new Rectangle(x, y, w, h);
		
		
	}
	public Entity(Rectangle rect)
	{
		this(rect.x, rect.y, rect.width, rect.height);
	}
	public void draw(Graphics g)
	{
		g.drawImage(img, bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, 0, 0, img.getWidth(null), img.getHeight(null), null);
	}
	public abstract void tick();
	
	public int getSpeed() { return this.speed; }
	public Image getImage() { return this.img; }
	public void setSpeed(int par1) { this.speed = par1; }
	public void setImage(Image par1) { this.img = par1; }
}
