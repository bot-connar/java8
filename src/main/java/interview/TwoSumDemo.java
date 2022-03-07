package interview;

import java.util.HashMap;
import java.util.Map;

public class TwoSumDemo {

    public static void main(String[] args) {
        int[] nums=new int[]{2,1,7,5,15,6};
        Map<Integer, Integer> map = new HashMap<>();
        //两数之和，可以先把一个数放进map中（数字作为键，下标作为值），再用和与该数的差作为key在map中查找
        //如果找得到，说明map中已经存在一个数字（键），刚好是和与当前数字的差，满足了两数之和刚好是当前的和。

        int sum = 8;
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(sum-nums[i])){
                System.out.println(i+" "+map.get(sum-nums[i]));
            }
            map.put(nums[i],i);
        }

    }
}
