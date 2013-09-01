package BFS.lunarcolony.gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GuiLabel extends GuiElement
{
	public String label;
	
	@SuppressWarnings("unused")
	private int fontSize, charWidth, charHeight;
	private boolean isFramed;
	private Rectangle2D stringBounds;
	private Color bgColor, fgColor, txtColor = Color.WHITE;
	private Font font;
	
	public GuiLabel(String label, boolean frame, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		this.label = label;
		fontSize = h;
		isFramed = frame;
		
		this.font = new Font("Courier New", Font.BOLD, h);
		
		init();
	}
	public GuiLabel(String label, String f, int fs, boolean frame, int x, int y, int w, int h)
	{
		super(x, y, w, h);
		this.label = label;
		fontSize = h;
		isFramed = frame;
		
		this.font = new Font(f, fs, h);
		
		init();
	}
	public GuiLabel(String l, int x, int y, GuiLabel gl)
	{
		super(x, y, gl.bounds.width, gl.bounds.height);
		this.label = l;
		this.fontSize = gl.bounds.height;
		
		this.font = gl.getFont();
		
		init();
	}
	
	public void init()
	{
		Graphics g = img.getGraphics();
		
		charWidth = g.getFontMetrics(font).charWidth(' ');
		charHeight = g.getFontMetrics(font).getAscent();
		stringBounds = g.getFontMetrics(font).getStringBounds(label, g);
		
		if(this.bounds.width == 0) { this.bounds.width = (int)stringBounds.getWidth() + (charWidth * 4); }
		g.dispose();
	}
	@Override public void draw(Graphics2D g)
	{
		if(isFramed)
		{
			g.setColor(fgColor);
			g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			g.setColor(bgColor);
			g.fillRect(bounds.x + 2, bounds.y + 2, bounds.width - 4, bounds.height - 4);
		}
		int x = bounds.x + ((bounds.width / 2) - (int)(stringBounds.getWidth() / 2d));
		int y = bounds.y + ((bounds.height / 2) + (int)(charHeight / 2.5f));
		g.setColor(txtColor); g.setFont(font);
		g.drawString(label, x, y);
	}
	
	public Font getFont() { return this.font; }
	public void setColors(Color bg, Color fg, Color txt) { this.bgColor = bg; this.fgColor = fg; this.txtColor = txt; }
}
