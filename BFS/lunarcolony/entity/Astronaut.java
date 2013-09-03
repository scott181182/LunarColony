package BFS.lunarcolony.entity;

import java.awt.Point;

import BFS.lunarcolony.graphic.Renderer;
import BFS.lunarcolony.world.World;

public class Astronaut extends Entity
{
	private Point goal = null;
	
	public Astronaut(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		
		this.setSpeed(4);
		this.setImage(World.entImg[0]);
	}
	@Override public void tick()
	{
		if(goal != null)
		{
			int x = this.goal.x - (this.bounds.x + this.bounds.width); boolean xN = x < 0; 
			int y = this.goal.y - (this.bounds.y + this.bounds.height); boolean yN = y < 0;
			double c2 = Math.sqrt(x * x + y * y);
			
		}
	}
	
	public void goToward(int x, int y)
	{
		double distance = Math.sqrt((x - this.bounds.x) * (x - this.bounds.x) + (y - this.bounds.y) * (y - this.bounds.y));
		double theta = Math.atan2(y - this.bounds.y, x - this.bounds.x);
		if(distance < this.getSpeed())
		{
			this.bounds.x = x;
			this.bounds.y = y;
		}
		else
		{
			this.bounds.x += Math.round(this.getSpeed() * Math.cos(theta));
			this.bounds.y += Math.round(this.getSpeed() * Math.sin(theta));	
		}
	}
	
	public void setGoal(Point p) { this.goal = p; }
}
