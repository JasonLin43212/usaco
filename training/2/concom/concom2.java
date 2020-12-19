/*
ID: jasonli7
LANG: JAVA
TASK: concom
*/
import java.io.*;
import java.util.*;

public class concom {

	public static int n;
	public static Map<Integer, Map<Integer, Integer>> percents = new HashMap<>();
	public static Map<Integer, Set<Integer>> owns = new HashMap<>();

	public static void main (String [] args) throws IOException {
		String fileName = "concom";
		BufferedReader br = new BufferedReader(new FileReader(fileName + ".in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			if (percents.containsKey(a)) {
				percents.get(a).put(b, p);
			} else {
				Map<Integer, Integer> bpMap = new HashMap<>();
				bpMap.put(b, p);
				percents.put(a, bpMap);
			}
			// Owns themselves
			Set<Integer> aSet = new HashSet<>();
			aSet.add(a);
			Set<Integer> bSet = new HashSet<>();
			bSet.add(b);
			if (!owns.containsKey(a)) {
				owns.put(a, aSet);
			}
			if (!owns.containsKey(b)) {
				owns.put(b, bSet);
			}
		}

		boolean hasUpdated2 = false;

		for (int a : percents.keySet()) {
			for (int b : percents.get(a).keySet()) {
				if (percents.get(a).get(b) > 50) {
					owns.get(a).add(b);
				}
			}
		}


		for (int i = 0; i < 100; i++) {
			boolean hasUpdated = false;

			for (int a : owns.keySet()) {
				Set<Integer> aOwns = owns.get(a);
				Set<Integer> newAOwns = new HashSet<>(aOwns);
				for (int k : aOwns) {
					Set<Integer> kOwns = owns.get(k);
					for (int b : kOwns) {
						if (percents.containsKey(k) && percents.get(k).containsKey(b) &&
							percents.get(k).get(b) > 50) {
								newAOwns.add(b);
								hasUpdated = true;
						}
					}
				}
				owns.put(a, newAOwns);

				for (int b : owns.keySet()) {
					if (!aOwns.contains(b)) {
						int sumAOwnB = 0;
						for (int k : aOwns) {
							if (percents.containsKey(k)) {
								Map<Integer, Integer> kPercents = percents.get(k);
								if (kPercents.containsKey(b)) {
									sumAOwnB += kPercents.get(b);
								}
							}

						}
						if (sumAOwnB > 50) {
							owns.get(a).add(b);
						}
					}
				}
			}

			if (!hasUpdated) {
				break;
			}
		}

		List<List<Integer>> solution = new ArrayList<>();
		for (int a : owns.keySet()) {
			for (int b : owns.get(a)) {
				if (a != b) {
					List<Integer> add = new ArrayList<>();
					add.add(a);
					add.add(b);
					solution.add(add);
				}
			}
		}

		solution.sort((a, b) -> {
			if (a.get(0) == b.get(0)) {
				return a.get(1) - b.get(1);
			} else {
				return a.get(0) - b.get(0);
			}
		});

		for (List<Integer> entry: solution) {
			pw.println(entry.get(0) + " " + entry.get(1));
		}

		pw.close();
	}
}
