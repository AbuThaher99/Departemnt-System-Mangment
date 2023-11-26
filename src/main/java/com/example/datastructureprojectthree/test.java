package com.example.datastructureprojectthree;

public class test {

	public static void main(String[] args) {
		Hash_Quadratic<Integer> mo = new Hash_Quadratic<>();
		mo.insert(22);
		mo.insert(1);
		mo.insert(13);
		mo.insert(11);
		mo.insert(24);
		mo.insert(33);
//		mo.insert(18);
//		mo.insert(42);
//		mo.insert(31);
	System.out.println(mo.toString());
		System.out.println(mo.getTableSize());
		
		

	}

}
