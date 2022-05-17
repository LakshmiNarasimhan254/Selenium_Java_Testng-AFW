package java_arrays_hashMap;
public class Lambda {
	public static void main(String[] args) {
		MyInterface myInterface = (a,b) -> a * b; //assign the expression to SAM
		int output = myInterface.Addmethods(5, 10); //returns 25
		System.out.println(output);
	}
}