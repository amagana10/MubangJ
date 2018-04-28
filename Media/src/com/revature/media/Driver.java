package com.revature.media;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.revature.media.Hawk;
import com.revature.media.Book;
import com.revature.media.Media;
import com.revature.media.Movie;

public class Driver {

	public static void main(String[] args) {
		
		List<Media> mediaList = new ArrayList<Media>();
		Book b1 = new Book("Elie Wiesel","Night",1960,"memoir");
		Book b2 = new Book("Ben Carson","Gifted Hands",1996,"autobiography");
		Movie m1 = new Movie("Steven Spielberg","Jaws",1975,"horror");
		Movie m2 = new Movie("David Michael Latt","Sharknado",2013,"science fiction");
		mediaList.add(b1);
		mediaList.add(m1);
		mediaList.add(b2);
		mediaList.add(m2);
		
		funWithReflections();
		
		System.out.println(genericReflections(mediaList));
		
		Book b4 = (Book) objectReflections(b2);
		
		
	}
	
	
	//what if we had no type safety? 
	public static Object objectReflections(Object o){
		return new Hawk();
	}
	
	public static void funWithReflections() {
		
		try {
			
			//get Book class using reflection
			Class clazz = Class.forName("com.revature.media.Book");
			System.out.println(clazz.getSimpleName());
			
			//print declared fields
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				System.out.println(f.getName()+" with a datatype of "+f.getType());
			}
			
			//new instance of Book 
			Book b3 = (Book) clazz.newInstance();
			Field author = clazz.getDeclaredField("author");
			author.setAccessible(true);
			author.set(b3, "Michael Bay");
			System.out.println(b3);
			
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Media> filterByMediaType(List<Media> mediaList, String mediaType){
		List<Media> filteredList = new ArrayList<Media>();
		try {
			checkType(mediaType);
		} catch (MediaException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		for (Media item : mediaList) {
			if(item.getClass().getName().equals("com.revature.media."+mediaType)) {
				filteredList.add(item);
			}
		}
		return filteredList;
	}
	
	public static void checkType(String type) throws MediaException {
		if (!type.equals("Book") && !type.equals("Movie")) {
			throw new MediaException();
		}
	}
	
	//return list of Books in list of objects
	public static <T> List<T> genericReflections(List<T> l) {
		List<T> newList = new ArrayList<T>();
		for (T item : l) {
			if (item.getClass().getName().equals("com.revature.media.Book")) {
				newList.add(item);
			}
		}
		return newList;
	}


}