package com.mycompany.a2;

public interface ICollection {

	void add(GameObject newObject);
	
	IIterator getIterator();
}