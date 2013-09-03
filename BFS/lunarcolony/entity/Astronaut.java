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
		double distance = Math.sqrt(Math.pow(x - this.bounds.x, 2) + Math.pow(y - this.bounds.y, 2));
		double theta = Math.atan2(y - this.bounds.y, x - this.bounds.x);
		if(distance < this.getSpeed())
		{
			this.bounds.x = this.bounds.x + (int) Math.round(distance * Math.cos(theta));
			this.bounds.y = this.bounds.y + (int) Math.round(distance * Math.sin(theta));
		}
		else
		{
			this.bounds.x = this.bounds.x + (int) Math.round(this.getSpeed() * Math.cos(theta));
			this.bounds.y = this.bounds.y + (int) Math.round(this.getSpeed() * Math.sin(theta));	
		}
	}
	
	public void setGoal(Point p) { this.goal = p; }
}
