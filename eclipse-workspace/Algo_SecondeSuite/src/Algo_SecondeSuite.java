
public class Algo_SecondeSuite {
	public static void main(String[] args) {
		int[] tab = getSuite(6);
		System.out.println(intTabToString(tab));
	}
	
	public static int[] getSuite(int etage) {
		if (etage == 1) {
			return new int[] {1,2};
		} else {
			int[] lastRt = getSuite(etage-1);
			int[] rt = new int[lastRt.length+1];
			for (int i = 0; i<lastRt.length; i++)
				rt[i] = lastRt[i];
			rt[lastRt.length] = lastRt[lastRt.length-2] + lastRt[lastRt.length-1];
			return rt;
		}
	}
	
	public static String intTabToString(int[] tab) {
		String str = "";
		for (int i : tab) {
			str+=i;
		}
		return str;
	}
}
