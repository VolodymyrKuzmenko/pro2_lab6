package pro2_lab6;

public class CalculateUtils {

 	public static Vector inputVector(int n, int value) {
 		Vector vector = new Vector(n); 
  		for(int i = 0; i < n; i++) {
 			vector.set(i, value);
 		}
 		return vector;
 	}
 	
 	
 	public static Matrix inputMatrix(int n, int value) {
 		Matrix matrix = new Matrix(n);
 		for(int i = 0; i < n; i++) {
 			for(int j = 0; j < n; j++) {
 				matrix.set(i, j, value);	
 			}
 		}
 		return matrix;
 	}
 	
 	
 	public static void outputVector(Vector vector) {
 		if(vector.size() <= 12) {
 			System.out.print(vector.toString());
 		}
 	}
 	
 	
 	public static void outputMatrix(Matrix matrix) {
 		if(matrix.size() <= 12) {
 			System.out.print(matrix.toString());
 		}
 	}
 	
 	
 	public static Matrix add(final Matrix left, final Matrix right,
 			final int id) {
 		int l = (id - 1) * Executor.H;
 		int r = id * Executor.H;
 		Matrix result = new Matrix(left.size());
 		for(int i = 0; i < left.size(); i++) {
 			for (int j = l; j < r; j++) {
 				result.set(i,j, left.get(i,j) + right.get(i,j));
			}
 			
 		}
 		return result;
 	}
 	
 	
 	public static Matrix mult(final int left, final Matrix right,
 			final int id) {
 		int l = (id - 1) * Executor.H;
 		int r = id * Executor.H;
 		Matrix result = new Matrix(right.size());
 		for (int i = l; i < r; i++) {
 			for (int j = 0; j < right.size(); j++) {
 				result.set(i,j, left * right.get(i, j));
 			}
 		}
 		return result;
 	}
 	
 	public static Matrix mult(final Matrix left, final Matrix right,
 			final int id) {
 		int l = (id - 1) * Executor.H;
 		int r = id * Executor.H;
 		Matrix result = new Matrix(left.size());
 		for (int i = 0; i < left.size(); i++) {
 			for (int j = l; j < r; j++) {
 				result.set(i, j, 0);
 				for (int y = 0; y < left.size(); y++) {
 					result.set(i, j, result.get(i, j) + left.get(i, y)
 							* right.get(y, j));
 				}
 			}
 		}
 		return result;
 	}
 	public static int mult(final Vector left, final Vector right, final int id){
 		
 		int l = (id - 1) * Executor.H;
 		int r = id * Executor.H;
 		int result =  0;
 		for (int i = l; i < r; i++) {
			result += left.get(i)*right.get(i);
		}
 		return result;
 	}
 
 	
}
