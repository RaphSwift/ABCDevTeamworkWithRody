package utils;

public interface Command extends Cloneable{
	public boolean exec();
	public String toString();
	public Object clone() throws CloneNotSupportedException;
}
