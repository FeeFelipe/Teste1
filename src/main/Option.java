package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Option {
	
	public static final String NOT_FOUND = "NO SUCH ROUTE";
	
	private String grafico = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
	
	private Rota rota;
	
	public Option() throws Exception {
		scanner();
	}
	
	private void scanner() throws Exception {
		rota = new Rota(grafico);
		
		System.out.print("\n   Exercicios de 1 ao 5:\n");
		q1a5(Arrays.asList("A-B-C", "A-D", "A-D-C", "A-E-B-C-D", "A-E-D"));
		
		System.out.print("\n\n   Exercicios 6:\n");
		q6("C", "C", 3);
		
		System.out.print("\n\n   Exercicios 7:\n");
		q7("A", "C", 4);
		
		System.out.print("\n\n   Exercicios 8:\n");
		q8_9("A", "C", 5, "8");
		
		System.out.print("\n\n   Exercicios 9:\n");
		q8_9("B", "B", 5, "9");
		
		System.out.print("\n\n   Exercicios 10:\n");
		q10("C", "C");
	}
	
	private void q1a5(List<String> asList) throws Exception {
		List<Result> results = q1a5_aux(asList);
		
		int i = 0;
        for (Result result : results) {
            i++;
			if (result.getValue() != null)
				System.out.print("(Q" + i + ") Resultado " + result.getKey() + ": " + result.getValue() + "\n");
			else
				System.out.print("(Q" + i + ") Resultado " + result.getKey() + ": " + result.getMessage() + "\n");
        }
	}
	
	private List<Result> q1a5_aux(List<String> asList) throws Exception {
		List<String> points = new ArrayList<String>(asList);
		List<Result> results = new ArrayList<Result>();
	
		for (int i=0, j=points.size(); i<j; i++) {
			RotaPadrao rotaPadrao = new RotaPadrao(points.get(i), rota);
			Integer result = rotaPadrao.getDistance();
			
			Result r = new Result();
			if (result > 0) {
				r.setKey(points.get(i));
				r.setValue(rotaPadrao.getDistance());
			} else {
				r.setKey(points.get(i));
				r.setMessage(NOT_FOUND);
			}
			
			results.add(r);
		}
		
		return results;
	}
	
	private void q6(String init, String end, Integer max) throws Exception {
		if (max < 3)
			throw new Exception("\n\nValor incorreto para: " + max);
		
		List<String> point = new ArrayList<String>();
		List<Result> results = new ArrayList<Result>();

		for (int i=0, j=max-2; i<=j; i++) {
			point = new ArrayList<String>();
			q6_7_aux(i, point, init, end);	
			results.addAll(q1a5_aux(point));
		}

		int i = 0;
		for (Result result : results) {
			if (result.getValue() != null) {
		    	i++;
				System.out.print("Resultado " + result.getKey() + ": " + result.getValue() + "\n");
		    }
		}
		
		System.out.print("\n(Q6) Total: " + i);
	}
	
	private void q7(String init, String end, Integer max) throws Exception {
		if (max < 3)
			throw new Exception("\n\nValor incorreto para: " + max);
		
		List<String> point = new ArrayList<String>();
		
		point = new ArrayList<String>();
		q6_7_aux(max-2, point, init, end);	
		List<Result> results = q1a5_aux(point);

		int i = 0;
		for (Result result : results) {
			if (result.getValue() != null) {
		    	i++;
				System.out.print("Resultado " + result.getKey() + ": " + result.getValue() + "\n");
		    }
		}
		
		System.out.print("\n(Q7) Total: " + i);
	}

	private List<String> pointsAccepted = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E"));
	private void q6_7_aux(Integer n, List<String> point, String line, String lineEnd) {
		for (int i=0, j=pointsAccepted.size(); i<j; i++) {
			String line1 = line.concat("-").concat(pointsAccepted.get(i));
			
			if (n > 0) {
				q6_7_aux(n-1, point, line1, lineEnd);
			} else {
				point.add(line1.concat("-").concat(lineEnd));
			}
		}
	}
	
	private void q8_9(String init, String end, Integer max, String q) throws Exception {
		if (max < 3)
			throw new Exception("\n\nValor incorreto para: " + max);
		
		List<String> point = new ArrayList<String>();
		List<Result> results = new ArrayList<Result>();

		for (int i=0, j=max-2; i<=j; i++) {
			point = new ArrayList<String>();
			q6_7_aux(i, point, init, end);	
			results.addAll(q1a5_aux(point));
		}
		
		Integer i = null;
		String r = null;
		for (Result result : results) {
			if (result.getValue() != null) {
				if (i == null || result.getValue() < i) {
		    		i = result.getValue();
		    		r = "(Q" + q + ") Resultado " + result.getKey() + ": " + i + "\n";
		    	}
		    }
		}
		
		System.out.print(r);
	}
	
	private void q10(String init, String end) throws Exception {
		List<String> point = new ArrayList<String>();
		List<Result> results = new ArrayList<Result>();

		int c = 0, i=0;
		while (i != 9) {
			point = new ArrayList<String>();

			q6_7_aux(i, point, init, end);	
			results = q1a5_aux(point);
			
			for(Result result : results) {
			    if (result.getValue() != null && result.getValue() < 30) {
					c++;
			    	System.out.print("Resultado " + result.getKey() + ": " + result.getValue() + "\n");
			    }
			}

			i++;
		}
		
		System.out.print("\n(Q10) Total:  " + c);
	}
}