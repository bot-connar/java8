package java8.test;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AmazonTest {


    @Test
    void test() {
        System.out.println(problem1(6, new int[]{1, 2, 3, 4, 5, 6}));
    }

    public Map<Integer, Integer> problem1(int x,int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> ans = new HashMap<>();
        for (int num : nums) {
            System.out.print(x/num);
            if (map.containsKey(x/num)){
                ans.put(num, x / num);
            }
            map.put(num,1);
        }
        return ans;
    }

}