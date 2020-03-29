package main;

public class RotaPadrao {
	
	private Rota rota;
	
	private Integer distance = 0;
	
	public RotaPadrao(String point, Rota rota) throws Exception {
		this.rota = rota;
		
		calculePoints(point);
	}
	
	public Integer getDistance() {
		return distance;
	}
	
	private void calculePoints(String line) throws Exception {
		String[] lineSplited = line.split("-");
		
		String pointPrev;
		String pointCurr;
		for (int i=1, j=lineSplited.length; i<j; i++) {
			pointPrev = lineSplited[i-1].trim().toUpperCase();
			pointCurr = lineSplited[i].trim().toUpperCase();
			
			validate(pointPrev, pointCurr);
			
			try {
				distance += rota.getPointDistance(pointPrev+pointCurr);
			} catch (Exception e) {
				distance = 0;
				break;
			}
		}
	}

	private void validate(String pointPrev, String pointCurr) throws Exception {
		if (pointPrev.length() != 1)
			throw new Exception("\n\nValor incorreto para: " + pointPrev);
		
		if (pointCurr.length() != 1)
			throw new Exception("\n\nValor incorreto para: " + pointCurr);
	}
}
