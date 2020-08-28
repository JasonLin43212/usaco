import java.io.*;
import java.util.*;

class powerset {

    public static ArrayList<ArrayList<Integer>> getPowerset(int[] nums) {
        ArrayList<ArrayList<Integer>> powerset = new ArrayList<>();

        ArrayList<Integer> empty = new ArrayList<>();
        powerset.add(empty);

        for (int i=0; i<nums.length; i++) {
            ArrayList<ArrayList<Integer>> newPowerset = new ArrayList<>();
            for (ArrayList<Integer> set: powerset) {
                newPowerset.add(set);
                ArrayList<Integer> addSet = new ArrayList<>(set);
                addSet.add(nums[i]);
                newPowerset.add(addSet);
            }
            powerset = newPowerset;
        }

        powerset.sort((a, b) -> a.size() - b.size());

        return powerset;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> pSet = getPowerset(new int[]{2, 5, 6, 10});
        System.out.println(pSet.toString());
    }
}
