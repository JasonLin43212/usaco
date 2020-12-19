import java.io.*;
import java.util.*;

public class notlast {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("notlast.in"));
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Integer> cowToMilk = new HashMap<>();
		String[] cows = new String[] {
		        "Bessie", "Elsie", "Daisy", "Gertie", "Annabelle", "Maggie", "Henrietta"
		};
		for (String cow : cows) {
		    cowToMilk.put(cow, 0);
		}
		
		for (int i = 0; i < N; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    String cowName = st.nextToken();
		    int milkProduced = Integer.parseInt(st.nextToken());
		    cowToMilk.put(cowName, cowToMilk.get(cowName) + milkProduced);
		}
		
		Map<Integer, List<String>> milkToCow = new HashMap<>();
		for (String cow : cowToMilk.keySet()) {
		    int milk = cowToMilk.get(cow);
		    if (milkToCow.containsKey(milk)) {
		        milkToCow.get(milk).add(cow);
		    } else {
		        List<String> cowNames = new ArrayList<>();
		        cowNames.add(cow);
		        milkToCow.put(milk, cowNames);
		    }
		}
		
		List<Integer> cowMilkOrder = new ArrayList<>(milkToCow.keySet());
		cowMilkOrder.sort((a, b) -> a - b);
		
		String solution = "";
		
		if (cowMilkOrder.size() >= 2) {
		    List<String> secondCows = milkToCow.get(cowMilkOrder.get(1));
		    if (secondCows.size() == 1) {
		        solution = secondCows.get(0);
		    } else {
		        solution = "Tie";
		    }
		} else {
		    solution = "Tie";
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));
		pw.println(solution);
		pw.close();
	}
}