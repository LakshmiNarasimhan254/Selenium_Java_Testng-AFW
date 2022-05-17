package java_arrays_hashMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class HashMap_example {

	public static void main(String[]args) {
		//hsm();
		hsm_intstr();
		for ( Integer i :hsm_intstr().keySet()){
			String Val = hsm_intstr().get(i);
			System.out.println("Hash Map Iteration of Key Set- For each " + Val);
		}
		
		Iterator<Entry<Integer,String>> iterIntstr = hsm_intstr().entrySet().iterator();
		while(iterIntstr.hasNext())
		{Entry<Integer, String> val_intstr = iterIntstr.next();
			System.out.println(val_intstr.getKey() + val_intstr.getValue());
		}
		
		
		
	}	




	// TODO Auto-generated method stub
	public static HashMap<String, String> hsm(){
		HashMap<String, String > hm = new HashMap<String,String>();
		hm.put("1", "Admin:test");
		hm.put("2", "customer");
		hm.put("3", "seller");
		hm.put("4", "distributor");
		hm.put(null, null);

		return hm;
	}


	public static HashMap<Integer, String> hsm_intstr(){
		HashMap<Integer, String > hm = new HashMap<Integer,String>();
		hm.put(1, "Admin:test");
		hm.put(2, "customer");
		hm.put(3, "seller");
		hm.put(4, "distributor");
		hm.put(null, null);

		return hm;
	}

	public static Hashtable<String, String>  hst(){
		Hashtable<String, String > hst = new Hashtable<String, String >();
		hst.put("1", "Admin:test");
		hst.put("2", "customer");
		hst.put("3", "seller");
		hst.put("4", "distributor");

		return hst;

	}

	public static void HashtableIteration(){
		for (String StrKey : hsm().keySet()){
			String Key = hsm().get(StrKey);
			System.out.println("Hash Map Iteration of Key Set- For each " + Key);
		}

		//Hash Map Iteration of value- For each
		for (String StrValue : hsm().values()){

			System.out.println("Hash Map Iteration of value- For each " + StrValue);
		}

		//Hash Map Iteration of Key Set- Iterator 
		Iterator<String> iterKey = hsm().keySet().iterator();
		while(iterKey.hasNext()){
			String Key = iterKey.next();
			System.out.println("Hash Map Iteration of Key Set- Iterator  " + Key);
		}

		//Hash Map Iteration of VALUE - Iterator 
		Iterator<String> iterValue = hsm().values().iterator();
		while(iterValue.hasNext()){
			String Value = iterValue.next();
			System.out.println("Hash Map Iteration of VALUE - Iterator   " + Value);
		}

		//Hash Map Iteration of Entryset - Iterator 
		Iterator<Entry<String, String>> IterEntrySet = hsm().entrySet().iterator();
		while(IterEntrySet.hasNext()){
			Entry<String, String> EntrySet = IterEntrySet.next();
			System.out.println("Hash Map Iteration of Entry - Iterator   " + EntrySet.getKey() + ":" + EntrySet.getValue());
		}
		//Hash Map Iteration of Entryset - For Each
		for(HashMap.Entry<String, String> EntrySet:
			hsm().entrySet()){
			System.out.println(EntrySet.getKey()+ EntrySet.getValue());
		}
	}

}


