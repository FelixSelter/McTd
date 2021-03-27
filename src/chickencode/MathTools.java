package chickencode;

public class MathTools {

	public static int ggt(int a, int b) {
		int h = (a > b) ? b : a;
		for (int i = h; i > 1; i--) {
			if ((a % i) == 0 && (b % i) == 0) {
				return i;
			}
		}
		return 1;
	}
}
