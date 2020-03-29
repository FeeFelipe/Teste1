package main;

public class Application {
	
	static public void main(String[] args) {
		try {
			new Option();
			
			System.out.print("\n\nFIM");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
