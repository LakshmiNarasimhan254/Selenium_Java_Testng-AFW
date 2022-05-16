package test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.poi.hpsf.Array;

public class HashMap_example {

	public static void main(String[]args) {
		System.out.println(hsm().get("1").split(":")[0]);
		System.out.println(hsm().size());
		Iterator<Entry<String, String>> s = hsm().entrySet().iterator();
		while(s.hasNext()){
			Entry<String, String> map = s.next();
		}
		
//		for(HashMap.Entry<String, String> set:
//			hsm().entrySet()){
//			System.out.println(set.getKey()+ set.getValue());
		
		hsm().get("1").split(":");
			char s1= 'C';
			System.out.println(String.valueOf(s1));
//		
//	}
	
		Iterator<Entry<String,String>> new_Itr = hsm().entrySet().iterator();
		while(new_Itr.hasNext()){
            Entry<String, String> new_Map  =new_Itr.next();
            System.out.println(new_Map.getKey() + " = "
                    + new_Map.getValue());}

		System.out.println(hst().get("1").split(":")[0]);		
				
	}				
	// TODO Auto-generated method stub
	public static HashMap<String, String> hsm(){
		HashMap<String, String > hm = new HashMap<String,String>();
		hm.put("1", "Admin:test");
		hm.put("2", "customer");
		hm.put("3", "seller");
		hm.put("4", "distributor");

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
	
}


