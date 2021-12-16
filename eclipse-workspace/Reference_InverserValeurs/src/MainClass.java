

import reference.RefObject;

public class MainClass {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		reference.RefObject<Integer> a = new reference.RefObject<Integer>(1);
		reference.RefObject<Integer> b = new reference.RefObject<Integer>(2);
		inverserValeur(a,b);
		System.out.println("a:"+a.argValue + " b:"+b.argValue);
	}
	
	public static void inverserValeur(reference.RefObject<Integer> a, reference.RefObject<Integer> b) {
		Integer c = a.argValue;
		a.argValue = b.argValue;
		b.argValue = c;
	}
	
	

}
