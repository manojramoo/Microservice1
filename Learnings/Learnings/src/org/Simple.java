package org;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Simple {
	 
	public static void main(String[] args) {
		
		
		Set<Integer> a=new TreeSet<Integer>();
		a.add(15);
		a.add(11);
		List<Object> b=List.of(a.toArray());
		System.out.println(b.get((b.size()-1)));
	}
}
