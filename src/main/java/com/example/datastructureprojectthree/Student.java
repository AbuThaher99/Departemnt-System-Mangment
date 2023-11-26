package com.example.datastructureprojectthree;

public class Student implements Comparable<Student> {
	
	private int id;
	private String name;
	private double average;
	private String gender;

	
	
	
	public Student( String name, int id,double average, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.average = average;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
		
	@Override 
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < name.length(); i++) {
			hash = hash + name.charAt(i);
		}
		return hash;
	}
	

	
	@Override
	public String toString() {
		return "" + name + "/" + id + "/" + average + "/" + gender + "\n";
	}



@Override
public int compareTo(Student o) {
	
	return this.name.compareTo(o.name);
}
	
	
	
	
	

}
