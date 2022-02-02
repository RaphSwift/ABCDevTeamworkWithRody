package utils;

public interface Command extends Cloneable, java.io.Serializable{
	public boolean exec();
	public String toString();
	public Object clone() throws CloneNotSupportedException;
}
