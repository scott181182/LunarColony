package BFS.lunarcolony.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GuiProgressBar extends GuiElement
{
	public int lvl, max;
	
	private int fontSize, charWidth, charHeight;
	private boolean displayText;
	private Rectangle2D stringBounds;
	private Font font;
	private Color bgColor, fgColor, barColor, txtColor;
	
	public GuiProgressBar(boolean txt, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		displayText = txt;
		fontSize = h;
		
		this.font = new Font("Courier New", Font.BOLD, fontSize);
		
		init();
	}
	public void init()
	{
		Graphics g = img.getGraphics();
		
		charWidth = g.getFontMetrics(font).charWidth(' ');
		charHeight = g.getFontMetrics(font).getAscent();
		stringBounds = g.getFontMetrics(font).getStringBounds(lvl + "/" + max, g);
		
		if(this.bounds.width == 0) { this.bounds.width = (int)stringBounds.getWidth() + (charWidth * 4); }
		g.dispose();
	}
	public void update()
	{
		Graphics g = img.getGraphics();
		stringBounds = g.getFontMetrics(font).getStringBounds(lvl + "/" + max, g);
		g.dispose();
	}
	
	@Override public void draw(Graphics2D g)
	{
		update();
		
		g.setColor(fgColor);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(bgColor);
		g.fillRect(bounds.x + 2, bounds.y + 2, bounds.width - 4, bounds.height - 4);
		int w;
		if(max <= lvl) { w = bounds.width - 4; }
		else { w = (int)((float)(bounds.width - 4) / (float)max * (float)lvl); }
		g.setColor(barColor);
		g.fillRect(bounds.x + 2, bounds.y + 2, w, bounds.height - 4);
		if(displayText)
		{
			int x = bounds.x + ((bounds.width / 2) - (int)(stringBounds.getWidth() / 2d));
			int y = bounds.y + ((bounds.height / 2) + (int)(charHeight / 2.5f));
			g.setColor(txtColor);
			g.setFont(font);
			g.drawString(lvl + "/" + max, x, y);
		}
	}
	
	public void setColors(Color bg, Color fg, Color bar, Color txt)
	{
		this.bgColor = bg; this.fgColor = fg;
		this.barColor = bar; this.txtColor = txt;
	}
}
