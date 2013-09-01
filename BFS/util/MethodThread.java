package BFS.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodThread extends Thread
{
	private Method method;
	private Object src;
	private Object[] params;
	
	public MethodThread(Object obj, Method m, Object... args)
	{
		this.src = obj;
		this.method = m;
		this.params = args;
	}
	@Override public void run()
	{
		try { method.invoke(src, params); } 
		catch (IllegalAccessException iae) { iae.printStackTrace(); }
		catch(IllegalArgumentException iae) { iae.printStackTrace(); }
		catch(InvocationTargetException ite) { ite.printStackTrace(); }
	}
}
