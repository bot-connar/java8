package exercise.huawei;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TTTestTest {

    //比数组中每个数字小的第一个数字
    public int[] firstNumGreat(int[] ints) {
        int[] ans = new int[ints.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ints.length; i++) {
            while (!stack.isEmpty() && ints[stack.peek()] > ints[i]) {
                Integer pop = stack.pop();
                ans[pop] = ints[i];
            }
            stack.push(i);
        }
        return ans;
    }
    //柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        int[] temp=new int[heights.length + 1];
        System.arraycopy(heights, 0, temp, 0, heights.length);
        temp[heights.length] = 0;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temp.length; i++) {
            while (!stack.isEmpty() && temp[stack.peek()] > temp[i]) {
                int h = temp[stack.pop()];
                int w=!stack.isEmpty()?i-stack.peek()-1:i;
                ans = Math.max(ans, w * h);
            }
            stack.push(i);
        }
        return ans;
    }

    //最大子序和
    public int maxSubArray(int[] nums) {
        return 0;
    }

    //子数组的最小值之和
    int MOD = (int)1e9+7;
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l, -1); Arrays.fill(r, n);
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!d.isEmpty() && arr[d.peekLast()] >= arr[i]) r[d.pollLast()] = i;
            d.addLast(i);
        }
        d.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!d.isEmpty() && arr[d.peekLast()] > arr[i]) l[d.pollLast()] = i;
            d.addLast(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = l[i], b = r[i];
            ans += (long) (i - a) * (b - i) % MOD * arr[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }

    public int sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) r[stack.pop()] = i;
            stack.push(i);
        }
        stack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) l[stack.pop()] = i;
            stack.push(i);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = l[i], b = r[i];
            ans += (long) (i - a) * (b - i) % MOD * arr[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }
    //子数组范围和
    public long subArrayRanges(int[] nums) {
        int n = nums.length;
        int[] minL = new int[n], minR = new int[n];
        Arrays.fill(minL,-1);Arrays.fill(minR,n);
        int[] maxL = new int[n],maxR = new int[n];
        Arrays.fill(maxL,-1);Arrays.fill(maxR,n);
        Deque<Integer> minStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!minStack.isEmpty() && nums[minStack.peek()] > nums[i]) minR[minStack.pop()]=i;
            minStack.push(i);
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) maxR[maxStack.pop()]=i;
            maxStack.push(i);
        }
        minStack.clear();
        maxStack.clear();
        for (int i = n - 1; i >= 0; i--) {

            while (!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) minL[minStack.pop()]=i;
            minStack.push(i);

            while (!maxStack.isEmpty() && nums[maxStack.peek()] < nums[i]) maxL[maxStack.pop()]=i;
            maxStack.push(i);
        }

        long sumMax = 0, sumMin = 0;
        for (int i = 0; i < n; i++) {
            sumMax += (long) (maxR[i] - i) * (i - maxL[i]) * nums[i];
            sumMin += (long) (minR[i] - i) * (i - minL[i]) * nums[i];
        }
        return (sumMax - sumMin);
    }

    //巫师的总力量和
    public int totalStrength(int[] strength) {
        int n = strength.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] left = new int[n],right = new int[n];
        Arrays.fill(left,-1);
        Arrays.fill(right,n);
        for(int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) right[stack.pop()] = i;
            stack.push(i);
        }
        stack.clear();
        for(int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && strength[stack.peek()] > strength[i]) left[stack.pop()] = i;
            stack.push(i);
        }
        // 前缀和
        long[] preSum1 = new long[n + 1];
        for(int i = 0; i < n; ++i) {
            preSum1[i + 1] += preSum1[i] + strength[i];
            preSum1[i + 1] %= MOD;
        }
        // 前缀和的前缀和
        long[] preSum2 = new long[n + 2];
        for(int i = 0; i <= n; ++i) {
            preSum2[i + 1] += preSum2[i] + preSum1[i];
            preSum2[i + 1] %= MOD;
        }

        // 求出每个 i 在他的 最小值区间 的贡献(乘以子数组和的和)
        long ans = 0;
        for(int i = 0; i < n; ++i) {
            int a = left[i];
            int b = right[i];
            // 使用 前缀和的和 去求 子数组和的和
            long sum1 = (long) (i - a) * (preSum2[b+1] - preSum2[i + 1])
                    - (long) (b - i) * (preSum2[i + 1] - preSum2[a+1]);
            sum1 %= MOD;
            ans += (long) strength[i] * sum1;
            ans %= MOD;
        }
        // 防止前面的减法产生负数
        return (int) ((ans + MOD) % MOD);
    }

    //巫师的总力量和
    public int totalStrength2(int[] strength) {
        int n = strength.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l,-1);
        Arrays.fill(r, n);

        Stack<Integer> stack = new Stack<>();
        //右端点
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) r[stack.pop()] = i;
            stack.push(i);
        }
        stack.clear();
        //左端点
        for (int i = n-1; i >=0; i--) {
            while (!stack.isEmpty() && strength[stack.peek()] > strength[i]) l[stack.pop()] = i;
            stack.push(i);
        }

        //前缀和
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + strength[i];
            pre[i + 1] %= MOD;
        }
        //前前缀和
        long[] prepre = new long[n + 2];
        for (int i = 0; i <= n; i++) {
            prepre[i+1] = prepre[i] + pre[i];
            prepre[i + 1] %= MOD;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = l[i]+1;
            int b = r[i]-1;
            long sum = (long) (i - l[i]) * (prepre[r[i]+1] - prepre[i + 1]) - (long) (r[i] - i) * (prepre[i + 1] - prepre[l[i]+1]);
            long sum2 = (long) (i - a + 1) * (prepre[b+2] - prepre[i + 1]) - (long) (b - i + 1) * (prepre[i + 1] - prepre[a]);
            sum %= MOD;
            ans += sum * strength[i];
            ans %= MOD;
        }
        return (int)(ans + MOD) % MOD;
    }



    //下一个更大元素 II
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2*n-1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]){
                ans[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ans;
    }

    //子数组最小乘积的最大值（最小乘积=子数组的和*子数组中的最小值）
    public int maxSumMinProduct2(int[] nums) {
        int n=nums.length;
        int[] r = new int[n], l = new int[n];
        Arrays.fill(r,n);
        Arrays.fill(l,-1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) r[stack.pop()] = i;
            stack.push(i);
        }
        stack.clear();
        for (int i = n-1; i >=0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) l[stack.pop()] = i;
            stack.push(i);
        }
        long[] pre = new long[n + 1];
        pre[0] = nums[0];
        for (int i = 1; i <= n; i++) {
            pre[i] = nums[i-1] + pre[i-1];
        }
        long ans=0;
        for (int i = 0; i < n; ++i) {
            int left = l[i] + 1;
            int right = r[i];
            long t = pre[right] - pre[left];
            ans = Math.max(ans, t * nums[i]);
        }
        long mod = (long) (1e9 + 7);
        return (int) (ans % mod);
    }

    //子数组最小乘积的最大值（最小乘积=子数组的和*子数组中的最小值）
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        // 单递增调栈
        Stack<Integer> stack = new Stack<>();
        // 求元素右边第一个比其小的
        int[] rightLower = new int[n];
        Arrays.fill(rightLower, n);  // 默认为n，即没发现
        for (int i = 0; i < n; i++) {
            // 单调递增栈
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) rightLower[stack.pop()] = i;
            stack.push(i);
        }
        // 求元素左边第一个比其小的
        int[] leftLower = new int[n];
        Arrays.fill(leftLower, -1);  // 默认为-1，即没发现
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) leftLower[stack.pop()] = i;
            stack.push(i);
        }

        // 数组前缀和
        long[] pre = new long[n + 1];  // 存储下标“之前”的元素和
        pre[0] = nums[0];
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        // 在前缀和及单调栈基础上，求最终解
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int r = rightLower[i];
            int l = leftLower[i] + 1;
            long t = pre[r] - pre[l];
            ans = Math.max(ans, t * nums[i]);
        }
        long mod = (long) 1e9 + 7;
        return (int) (ans % mod);
    }


    /*
        前缀数组sum，sum[i]表示前i个元素的和。
        子数组nums[i..j]的和 subNum = sum[j+1]-sum[i];
        ※同余定理： subNum % k == 0，等价于 sum[j+1] % k == sum[i] % k ！！！(j>i)
    */
    public boolean checkSubarraySum(int[] nums, int k) {
        //23,2,4,6,7
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            int mod = pre[i] % k;
            if (map.containsKey(mod)&&i>map.get(mod)+1) return true;
            else if (!map.containsKey(mod)) map.put(mod, i);
        }
        return false;
    }

    //前缀和
    public int qianzhuihe(int left, int right) {
        int[] ints = new int[]{2, 1, 3, 6, 4};
        int[] S = new int[ints.length+1];
        for (int i = 0; i < ints.length; i++) {
            S[i + 1] = ints[i] + S[i];
        }
        return S[right] - S[left-1];
    }


    public int totalStrength3(int[] strength) {
        int n = strength.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l,-1);
        Arrays.fill(r, n);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty()&&strength[stack.peek()]>=strength[i]) r[stack.pop()] = i;
            stack.push(i);
        }
        stack.clear();
        for (int i = n-1; i >=0; i--) {
            while (!stack.isEmpty()&&strength[stack.peek()]>strength[i]) l[stack.pop()] = i;
            stack.push(i);
        }

        long[] pre = new long[n+1];
        long[] ppre = new long[n+2];
        // 前缀和的前缀和
        for(int i = 0; i < n; ++i) {
            pre[i + 1] = pre[i] + strength[i];
            pre[i + 1] %= MOD;
        }
        for(int i = 0; i <= n; ++i) {
            ppre[i + 1] = ppre[i] + pre[i];
            ppre[i + 1] %= MOD;
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int a = l[i];
            int b = r[i];
            long sum = (long) (i - a) * (ppre[b + 1] - ppre[i + 1]) - (long) (b - i) * (ppre[i + 1] - ppre[a + 1]);
            sum %= MOD;
            ans+= sum *strength[i];
            ans %= MOD;
        }
        return (int)ans;
    }


    @Test
    void test1() {
        System.out.println(Arrays.toString(firstNumGreat(new int[]{5, 6, 8, 2, 7, 4})));
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(subArrayRanges(new int[]{4,-2,-3,4,1}));
        System.out.println(sumSubarrayMins(new int[]{3,1,2,4}));
        System.out.println(sumSubarrayMaxs(new int[]{3,1,2,4}));
        System.out.println(maxSumMinProduct(new int[]{1,2,3,2}));
        System.out.println(maxSumMinProduct2(new int[]{1,2,3,2}));
        System.out.println(qianzhuihe(2,4));
        System.out.println(checkSubarraySum(new int[]{23,2,4,6,7},6));
        System.out.println(totalStrength2(new int[]{1,3,1,2}));
        System.out.println();
        System.out.println(totalStrength3(new int[]{1,3,1,2}));
        System.out.println(subarraySum(new int[]{-3, 1, 2, -3, 4}));
    }



    public int getNumbers() {
        List<Integer> buildingCount = new ArrayList<>();
        buildingCount.add(2);
        buildingCount.add(1);
        buildingCount.add(1);
        buildingCount.add(3);
        List<Integer> routerLocation = new ArrayList<>();
        routerLocation.add(1);
        routerLocation.add(2);
        List<Integer> routerRange = new ArrayList<>();
        routerRange.add(2);
        routerRange.add(0);
        List<Integer> temp = new ArrayList<>();
        for (Integer a:buildingCount) {
            temp.add(0);
        }

        for (int i = 0; i < routerLocation.size(); i++) {
            Integer location = routerLocation.get(i);
            for (int j = i; j < routerRange.size(); j++) {
                int range=routerRange.get(j);
                int diff=(location-range)<=0?1:(location-range);
                int size=buildingCount.size();
                int sum= Math.min((location + range), size);
                for (int k = diff; k <= sum; k++) {
                    temp.set(k-1,temp.get(k-1)+1);
                }
            }
        }
        int ans=0;
        for (int i = 0; i < buildingCount.size(); i++) {
            if (buildingCount.get(i)<temp.get(i)){
            }else {
                ans+=1;
            }
        }
        return ans;
    }


    public List<Long> bestCombo(List<Integer> popularity, int k) {
        return null;
    }

    //子数组之和为0
    public List<Integer> subarraySum(int[] nums) {
        // write your code here
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)){
                ans.add(map.get(sum)+1);
                ans.add(i);
                break;
            }else {
                map.put(sum, i);
            }
        }
        return ans;
    }
    static class ListNode implements Cloneable{
        int val;

        public ListNode(int val) {
            this.val = val;
        }
        @Override
        protected ListNode clone() throws CloneNotSupportedException {
            //浅拷贝
            return (ListNode) super.clone();
        }
    }
    static class Student implements Cloneable{
        int age;
        String name;
        ListNode node;

        public Student(int age,String name,ListNode node) {
            this.age = age;
            this.name = name;
            this.node = node;
        }

        @Override
        protected Student clone() throws CloneNotSupportedException {
            //深拷贝
            Student s=(Student) super.clone();
            s.node = this.node.clone();
            return s;
        }
    }


    @Test
    public void test2() {
        miniMaxSum(new ArrayList<>());
        System.out.println();
        System.out.println(timeConversion("12:40:22AM"));
    }

    public void fast_fail() throws CloneNotSupportedException {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        for (int i = 1; i <= map.size(); i++) {
            if (map.get(i)==3) {
                map.remove(i);
                map.put(4, 4);
            }
            System.out.println(i);
        }
        //forEach中如果删除会报错   ConcurrentModificationException
        for (Integer i: map.keySet()) {
            if (map.get(i)==2) {
                map.put(4, 4);
            }
            System.out.println(i);
        }


        Student s1 = new Student(1, "1",new ListNode(1));
        Student s2 = s1.clone();
        System.out.println(s1==s2);
        System.out.println(s1.hashCode()+"     "+s2.hashCode());
        System.out.println(s1.name.equals(s2.name));
        System.out.println(s1.age+"   "+s2.age);
        System.out.println(s1.node+"  "+s2.node);
        System.out.println(s1.node.hashCode()+"     "+s2.node.hashCode());


        System.out.printf("%.6f",2/4.0);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        long sum=list.stream().mapToInt(Integer::intValue).sum();
    }

    public void miniMaxSum(List<Integer> arr) {
        // Write your code here
        arr.add(256741038);
        arr.add(623958417);
        arr.add(467905213);
        arr.add(714532089);
        arr.add(938071625);
        Collections.sort(arr);
        AtomicInteger count= new AtomicInteger();
        arr.stream().mapToInt(Integer::valueOf).max().ifPresent(o-> count.set((int) arr.stream().filter(a -> a == o).count()));
        long sum=arr.stream().mapToLong(Long::valueOf).sum();
        System.out.print(sum-arr.get(arr.size()-1));
        System.out.print(" ");
        System.out.print(sum-arr.get(0));
    }

    public static String timeConversion(String s) {
        // Write your code here
        String a=s.substring(0,2);
        String b=s.substring(s.length()-2);
        String c=s.substring(2,s.length()-2);
        if (Integer.parseInt(a)<=12&&b.equals("PM")) {
            return Integer.parseInt(a)+12+c;
        }else if (Integer.parseInt(a)>=12&&b.equals("AM")) {
            return ((Integer.parseInt(a)-12)==0?"00":(Integer.parseInt(a)-12))+c;
        }
        return a+c;

    }




}

abstract class ABS{
    public ABS(){

    }
    static Object aa(){
        return null;
    }
    abstract String bb();
}
interface ABT{
    default void test(){

    }
    static Object aa(){
        System.out.println("static method in an interface!");
        return null;
    }

    String bb();
}

class ABU extends ABS implements ABT {

    @Override
    public void test() {
        ABT abt = () -> "abt";
        ABT.super.test();
    }

    String cc() {
        ABS.aa();
        aa();
        return null;
    }

    @Override
    public String bb() {
        ABT.aa();
        return null;
    }
}