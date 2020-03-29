package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rota {

	private List<String> pointsAccepted = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E"));
	
	private List<String> points = new ArrayList<String>();
	
	private Map<String, Integer> distance = new HashMap<String, Integer>();
	
	public Rota(String grafico) throws Exception {
		createPoints(grafico);
	}
	
	public List<String> getPoints() {
		return points;
	}
	
	public Integer getPointDistance(String point) {
		return distance.get(point);
	}
	
	private void createPoints(String line) throws Exception {
		String[] lineSplited = line.split(",");
		
		String point;
		String pointChar;
		for (int i=0, j=lineSplited.length; i<j; i++) {
			point = lineSplited[i].trim().toUpperCase();
			
			validate(point);
			
			for (int i2=0, j2=point.length()-1; i2<j2; i2++) {
				pointChar = Character.toString(point.charAt(i2));
				
				if (!points.contains(pointChar))
					points.add(pointChar);
			}
			
			distance.put(point.substring(0,2), Integer.parseInt(point.substring(2)));	
		}
	}

	private void validate(String point) throws Exception {
		if (
			point.length() != 3 ||
			pointsAccepted.indexOf(Character.toString(point.charAt(0))) == -1 ||
			pointsAccepted.indexOf(Character.toString(point.charAt(1))) == -1 ||
			point.charAt(0) == point.charAt(1)
		)
			throw new Exception("\n\nValor incorreto para: " + point);
	}
}
