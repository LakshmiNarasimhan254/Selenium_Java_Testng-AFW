package java_arrays_hashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class ArrayList_exp {
	
	
	public static void main(String[] args) {
		Integer Val= arl().get(2);
		//System.out.println(Val);
		
		
		
		
		//IterationMethods();
	}
	public static ArrayList<Integer> arl(){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(0, 3);
		numbers.add(2);
		numbers.add(1);
		return numbers;
	}
	public static void IterationMethods(){
		Iterator<Integer> itrInt = arl().iterator();
			while(itrInt.hasNext()){
				Integer i = itrInt.next();
				System.out.println(i);
				
			}
		
	}


}