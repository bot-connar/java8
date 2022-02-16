package interview;

import java.util.HashMap;

public class TwoSumDemo {

    public static void main(String[] args) {
        int[] nums=new int[]{2,1,7,5,15,6};
        int target=7;
        for (int i=0;i<nums.length;i++){
            for (int j = i+1; j <nums.length ; j++) {
                if (target-nums[i]==nums[j]){
                    System.out.println(i);
                    System.out.println(j);
                }
            }
        }
        System.out.println("----------");
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                System.out.println(map.get(nums[i]));
                System.out.println(i);
            }
            map.put(target-nums[i],i);
        }

    }
}
