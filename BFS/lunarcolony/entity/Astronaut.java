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
	
	public void goToward(int x, int y) throws InterruptedException
	{
		while(this.bounds.x != x || this.bounds.y != y)
		{		
			int tempY = y - this.bounds.y;
			int tempX = x - this.bounds.x;
			
			if(Math.abs(tempY) >= Math.abs(tempX) && Math.abs(tempX) >= this.getSpeed())
			{ 
				if (x > this.bounds.x) { this.bounds.x = this.bounds.x + (this.getSpeed() * tempX) / tempY; }
				else if (x < this.bounds.x) { this.bounds.x = this.bounds.x - (this.getSpeed() * tempX) / tempY; }
					
				if (y > this.bounds.y) { this.bounds.y = this.bounds.y + this.getSpeed(); } 
				else if (y < this.bounds.y) { this.bounds.y = this.bounds.y - this.getSpeed(); }
			}
			else if(Math.abs(tempY) >= Math.abs(tempX) && Math.abs(tempX) < this.getSpeed())
			{
				if (x > this.bounds.x) { this.bounds.x = this.bounds.x + Math.abs(tempX); }
				else if (x < this.bounds.x) { this.bounds.x = this.bounds.x - Math.abs(tempX); }

				if (Math.abs(tempY) < this.getSpeed())
				{
					if (y > this.bounds.y) { this.bounds.y = this.bounds.y + Math.abs(tempY); } 
					else if (y < this.bounds.y) { this.bounds.y = this.bounds.y - Math.abs(tempY); }
				}
				else if (y > this.bounds.y) { this.bounds.y = this.bounds.y + this.getSpeed(); } 
				else if (y < this.bounds.y) { this.bounds.y = this.bounds.y - this.getSpeed(); }
			}
			else if(Math.abs(tempY) <= Math.abs(tempX) && Math.abs(tempY) >= this.getSpeed())
			{
				if (x > this.bounds.x) { this.bounds.x = this.bounds.x + this.getSpeed(); }
				else if (x < this.bounds.x) { this.bounds.x = this.bounds.x - this.getSpeed(); }
					
				if (y > this.bounds.y) { this.bounds.y = this.bounds.y + (this.getSpeed() * tempY) / tempX; } 
				else if (y < this.bounds.y) { this.bounds.y = this.bounds.y - (this.getSpeed() * tempY) / tempX; }
			}
			else if(Math.abs(tempY) <= Math.abs(tempX) && Math.abs(tempY) < this.getSpeed())
			{	
				if (Math.abs(tempX) < this.getSpeed())
				{
					if (x > this.bounds.x) { this.bounds.x = this.bounds.x + Math.abs(tempX); } 
					else if (x < this.bounds.x) { this.bounds.x = this.bounds.x - Math.abs(tempX); }
				}
				else if (x > this.bounds.x) { this.bounds.x = this.bounds.x + this.getSpeed(); }
				else if (x < this.bounds.x) { this.bounds.x = this.bounds.x - this.getSpeed(); }
					
				if (y > this.bounds.y) { this.bounds.y = this.bounds.y + Math.abs(tempY); } 
				else if (y < this.bounds.y) { this.bounds.y = this.bounds.y - Math.abs(tempY); }
			}
			Thread.sleep(50);
		}
	}
	
	public void setGoal(Point p) { this.goal = p; }
}
