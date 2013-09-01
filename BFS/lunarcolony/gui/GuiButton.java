package BFS.lunarcolony.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GuiButton extends GuiClickable
{
	public String label;
	
	private int fontSize, charHeight, charWidth;
	private Font font;
	private Color bgColor = Color.GRAY, fgColor = Color.BLACK, txtColor = Color.WHITE;
	private Rectangle2D stringBounds;
	
	public GuiButton(String l, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		this.label = l;
		this.fontSize = h;
		
		this.font = new Font("Courier New", Font.BOLD, fontSize);
		
		init();
	}
	public GuiButton(String l, String f, int fs, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		this.label = l;
		this.fontSize = h;
		
		this.font = new Font(f, fs, fontSize);
		
		init();
	}
	public GuiButton(String l, int x, int y, GuiButton gb)
	{
		super(x, y, gb.bounds.width, gb.bounds.height);
		this.label = l;
		this.fontSize = gb.bounds.height;
		
		this.font = gb.getFont();
		
		init();
	}
	
	public void init()
	{
		Graphics g = img.getGraphics();
		
		charHeight = g.getFontMetrics(font).getAscent();
		charWidth = g.getFontMetrics(font).charWidth(' ');
		stringBounds = g.getFontMetrics(font).getStringBounds(label, g);
		
		if(this.bounds.width == 0) { this.bounds.width = (int)stringBounds.getWidth() + (charWidth * 4); }
		g.dispose();
	}
	@Override public void draw(Graphics2D g)
	{
		g.setColor(fgColor);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		g.setColor(bgColor);
		g.fillRect(bounds.x + 2, bounds.y + 2, bounds.width - 4, bounds.height - 4);
		
		int x = bounds.x + ((bounds.width / 2) - (int)(stringBounds.getWidth() / 2d));
		int y = bounds.y + ((bounds.height / 2) + (int)(charHeight / 2.5f));
		g.setColor(txtColor);
		g.setFont(this.font);
		g.drawString(label, x, y);
	}
	
	public Font getFont() { return this.font; }
	public Color getFG() { return this.fgColor; }
	public Color getBG() { return this.bgColor; }
	public Color getTXT() { return this.txtColor; }
	public void setColors(Color bg, Color fg, Color txt) { this.bgColor = bg; this.fgColor = fg; this.txtColor = txt; }
}
