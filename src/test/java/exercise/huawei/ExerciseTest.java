package exercise.huawei;

import org.junit.jupiter.api.Test;

import java.util.*;


class ExerciseTest {
    ArrayList<ArrayList<Integer>> threeNums(int[] num) {
        ArrayList<ArrayList<Integer>> ans =new ArrayList<>();
        if (num.length == 0) {
            return ans;
        }
        Arrays.sort(num);
        int len = num.length;
        for (int i = 0; i < len; i++) {
            int curr = num[i];
            if (curr > 0) {
                return ans;
            }
            if (i>0&&num[i] == num[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int target=curr + num[left] + num[right];
                if (target == 0) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(curr);
                    temp.add(num[left]);
                    temp.add(num[right]);
                    ans.add(temp);
                    while (left<right&&num[left] == num[++left]) {
                        left++;
                    }
                    while (left<right&&num[right] == num[--right]) {
                        right--;
                    }
                } else if (target > 0) {
                    right--;
                }else {
                    left++;
                }
            }
        }
        return ans;
    }
    //二维数组中的查找
    public boolean Find(int target, int [][] array) {
        if (array.length == 0 || array[0].length == 0 || array[0][0] > target) {
            return false;
        }
        int rows = array.length - 1;
        int cols = 0;

        while (rows >= 0 && cols < array[0].length) {
            int temp = array[rows][cols];
            if (temp == target) {
                return true;
            } else if (temp > target) {
                rows--;
            }else {
                cols++;
            }
        }
        return false;
    }
    //寻找峰值
    public int findPeakElement (int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return right;
    }
    //大数加法
    public String solve (String s, String t) {
        if (s.length() < t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }
        int min_len = t.length()-1;
        int max_len = s.length();
        int curry = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = max_len-1; i >=0 ; i--) {
            int a = s.charAt(i) - '0';
            int b = min_len>=0?t.charAt(min_len--) - '0':0;
            String temp = (a + b + curry) % 10 + "";
            curry = (a + b+ curry) / 10;
            ans.insert(0, temp);
            if (curry == 1 && i == 0) {
                ans.insert(0, "1");
            }
        }
        return ans.toString();
    }
    //螺旋矩阵
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }
        int left = 0, right = matrix[0].length-1, up = 0, down = matrix.length-1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[up][i]);
            }
            up++;
            if (up > down) {
                break;
            }
            for (int i = up; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            right--;
            for (int i = right; i >= left; i--) {
                ans.add(matrix[down][i]);
            }
            down--;
            for (int i = down; i >=up ; i--) {
                ans.add(matrix[i][left]);
            }
            left++;
            if (left > right) {
                break;
            }
        }
        return ans;
    }

    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(TreeNode left, int val, TreeNode right) {
            this.left=left;
            this.right=right;
            this.val=val;
        }

        public TreeNode(int val) {
            this(null, val, null);
        }
    }

    //二叉树的广度优先遍历，层序遍历
    public ArrayList<ArrayList<Integer>> bfs(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<>();
            int length = queue.size();
            for (int i = 0; i<length; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    temp.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(temp);
        }
        return ans;
    }
    //二叉树的先序遍历，深度优先遍历
    public void dfs(TreeNode root,ArrayList<ArrayList<Integer>> ans,ArrayList<Integer> temp) {
        if (root != null) {
            temp.add(root.val);
            if (root.left != null) {
                dfs(root.left, ans, temp);
                temp.remove((Integer) root.left.val);
            }
            if (root.right != null) {
                dfs(root.right, ans, temp);
                temp.remove((Integer) root.right.val);
            }
            if (root.left == null && root.right == null) {
                ans.add(new ArrayList<>(temp));
            }
        }
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int j : preorder) root = add(root, j);
        return root;
    }

    public TreeNode add(TreeNode node, int num) {
        if (node==null) return new TreeNode(num);
        if (node.val > num) {
            node.left = add(node.left, num);
        }else {
            node.right = add(node.right, num);
        }
        return node;
    }

    @Test
    void test1(){
        System.out.println(threeNums(new int[]{-10,0,10,20,-10,-40}));
        System.out.println(findPeakElement(new int[]{2,4,1,2,7,8,4}));
        System.out.println(solve("99","1"));
        //System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println(spiralOrder(new int[][]{{2,3}}));
        System.out.println(bfs(new TreeNode(new TreeNode(new TreeNode(3),2,new TreeNode(4)),1,new TreeNode(new TreeNode(6),5,null))));
        System.out.println(bstFromPreorder(new int[]{8,5,1,7,10,12}));
        System.out.println(Arrays.toString(numberOfBricks(13)));
        System.out.println(Arrays.toString(numberOfBricks(10)));
        System.out.println(qianzhuihe(2,4));
        System.out.println(Arrays.toString(firstNumGreat(new int[]{})));
        System.out.println(shiyezonghe(new int[]{}));
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{})));
        System.out.println(largestRectangleArea(new int[]{2,1,5,6,2,3}));
        TreeNode root=new TreeNode(new TreeNode(new TreeNode(3),2,new TreeNode(4)),1,new TreeNode(new TreeNode(6),5,null));
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        dfs(root,ans,new ArrayList<Integer>());
        System.out.println(ans);
        System.out.println(permute(new int[]{1,2,3}));
        System.out.println(permute2(new int[]{1,2,3}));
        System.out.println(permute2(new int[]{1,1,2}));
        int[] tes = new int[]{8, 5, 1, 7, 10, 12};
        quickSort(0,tes.length-1,tes);
        System.out.println(Arrays.toString(tes));
    }




    //numberOfBricks
    public int[] numberOfBricks(int target) {
        //John (1) and Jack (2)
        int[] ans = new int[2];
        int count = 0;
        int A = 1;
        int B = 0;
        while (count < target) {
            count += A;
            if (count >= target) {
                ans[0] = 1;
                ans[1] =  A- (count-target);
                break;
            }
            B = 2 * A;
            count += B;
            if (count >= target) {
                ans[0] = 2;
                ans[1] =  B-( count-target);
                break;
            }
            A++;
        }
        return ans;
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

    //视野总和
    public int shiyezonghe(int[] ints) {
        ints = new int[]{10,3,7,4,12,2,Integer.MAX_VALUE};
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ints.length; i++) {
            while (!stack.isEmpty() && ints[stack.peek()] < ints[i]) {
                int index = stack.pop();
                ans+=i-index-1;
            }
            stack.push(i);
        }
        return ans;
    }

    //视野总和
    public int shiyezonghe2(int[] ints) {
        int ans = 0;
        int[] num = new int[]{10,3,7,4,12,2,Integer.MAX_VALUE};
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < num.length; i++) {
            while (!stack.isEmpty() && num[stack.peek()] < num[i]) {
                Integer pop = stack.pop();
                ans+=i-pop-1;
            }
            stack.push(i);
        }
        return ans;
    }

    @Test
    void test4(){
        System.out.println(shiyezonghe(new int[]{}));
        System.out.println(shiyezonghe2(new int[]{}));
    }

    //比数组中每个数字小的第一个数字
    public int[] firstNumGreat(int[] ints) {
        ints = new int[]{5, 6, 8, 2, 7, 4};
        int[] ans = new int[ints.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ints.length; i++) {
            while (!stack.isEmpty() && ints[stack.peek()] > ints[i]) {
                ans[stack.pop()]=ints[i];
            }
            stack.push(i);
        }
        return ans;
    }

    //POJ 3250 Bad Hair Day 详解——单调栈

    //每日温度，求距离当天温度更高的温度天数
    public int[] dailyTemperatures(int[] temperatures) {
        temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] ans = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int a = stack.pop();
                ans[a]=i-a;
            }
            stack.push(i);
        }
        return ans;
    }

    //柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        int[] temp = new int[heights.length + 1];
        System.arraycopy(heights,0,temp,0,heights.length);
        temp[heights.length] = 0;
        int area = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temp.length; i++) {
            while (!stack.isEmpty() && temp[stack.peek()] > temp[i]) {
                int h = temp[stack.pop()];
                int w = !stack.isEmpty()?i - stack.peek() - 1:i;
                area = Math.max(area, h * w);
            }
            stack.push(i);
        }
        return area;
    }

    //不重复数字的全排列
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // sort just to insure output in alphabet order
        Arrays.sort(num);
        // copy sorted nums to remain
        ArrayList<Integer> remain = new ArrayList<>();
        for (int n : num) remain.add(n);

        permuteRec(remain, new ArrayList<>(),ans);
        return ans;
    }
    public void permuteRec(ArrayList<Integer> remain, ArrayList<Integer> perm,ArrayList<ArrayList<Integer>> ans) {
        // base case, i.e. no remaining elem
        if (remain.isEmpty()) {
            // 存答案时必须存copy, 不然之后的回溯会overwrite存入的答案。
            ans.add(new ArrayList<>(perm));
            return;
        }

        for (int i = 0; i < remain.size(); i++) {
            Integer a = remain.remove(i);
            perm.add(a);
            permuteRec(remain, perm,ans);

            // 复原 (a.k.a 回溯)
            remain.add(i, a);
            perm.remove(perm.size()-1);
        }
    }

    //含有重复数字的全排列
    public ArrayList<ArrayList<Integer>> permute2(int[] ints) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Arrays.sort(ints);
        ArrayList<Integer> remain = new ArrayList<>();
        for (Integer i:ints) remain.add(i);
        permute2Recursively(ans, remain,new ArrayList<>());
        return ans;
    }
    private void permute2Recursively(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> remain, ArrayList<Integer> permute) {
        if (remain.isEmpty()) {
            ans.add(new ArrayList<>(permute));
        }else {
            int last = -2;
            for (int i = 0; i < remain.size(); i++) {
                Integer temp = remain.get(i);
                if ( temp== last) continue;
                else last = temp;
                permute.add(remain.remove(i));
                permute2Recursively(ans, remain, permute);
                remain.add(i, temp);
                permute.remove(permute.size()-1);
            }
        }
    }

    //快速排序
    public void quickSort(int left, int right, int[] array) {
        if (left>right)
            return;
        int temp = array[left];
        int i = left;
        int j = right;
        while (i != j) {
            while (i < j&&temp<=array[j]) {
                j--;
            }
            while (i < j&&temp>=array[i]) {
                i++;
            }
            if (i<j) {
                int t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        array[left] = array[i];
        array[i] = temp;
        quickSort(left, i-1, array);
        quickSort(i+1, right, array);
    }
    public void quickSort2(int left, int right, int[] array){
        if (left > right) {
            return;
        }
        int curr = array[left];
        int i=left;
        int j = right;
        while (i < j) {
            while (i < j && curr <= array[j]) {
                j--;
            }
            while (i < j && curr >= array[i]) {
                i++;
            }
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        array[left] = array[i];
        array[i] = curr;
        quickSort2(left, i - 1, array);
        quickSort2(i+1, right, array);
    }

    //下一个更大元素
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack=new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums2) {
            while(!stack.isEmpty() && stack.peek()<num){
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++) nums1[i] = map.getOrDefault(nums1[i],-1);
        return nums1;
    }

    //最大子数组的和
    public int maxSubArray(int[] nums) {
        int ans=nums[0];
        int[] dp = new int[nums.length];
        dp[0] = ans;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i]+dp[i-1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
    static class Status{
        public int lSum,rSum,iSum,mSum;
        public Status(int lSum,int rSum,int iSum,int mSum){
            this.lSum=lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }

        public static Status getInfo(int[] a, int l, int r) {
            if (l == r) {
                return new Status(a[l], a[l], a[l], a[l]);
            }
            int m = (l + r) >> 1;
            Status lSub = getInfo(a, l, m);
            Status rSub = getInfo(a, m + 1, r);
            return pushUp(lSub, rSub);
        }

        public static Status pushUp(Status l, Status r) {
            int iSum = l.iSum + r.iSum;
            int lSum = Math.max(l.lSum, l.iSum + r.lSum);
            int rSum = Math.max(r.rSum, r.iSum + l.rSum);
            int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
            return new Status(lSum, rSum, mSum, iSum);
        }

        public static void main(String[] args) {
            System.out.println(getInfo(new int[]{-2,1,-3,4,-1,2,1,-5,4},0,8).mSum);
        }
    }

    public int calculator(String str) {
        Stack<Integer> stack = new Stack<>();
        int op = '+';
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                int sum = 0;
                while (i < str.length() && Character.isDigit(str.charAt(i))) {
                    sum =sum*10+str.charAt(i) - '0';
                    i++;
                }
                i--;
                if (op == '+') stack.push(sum);
                else if(op=='-') stack.push(-sum);
                else if (op=='*') stack.push(stack.pop() * sum);
                else if (op=='/') stack.push(stack.pop() / sum);
            }else if (c=='+'||c=='-'||c=='*'||c=='/'){
                op = c;
            } else if (c == '(') {
                int left = i;
                int right = left + 1;
                int count=1;
                while (right<str.length()&&count > 0) {
                    if (str.charAt(right)=='(') count++;
                    if (str.charAt(right) == ')') count--;
                    right++;
                }
                i = right-1;
                int sum = calculator(str.substring(left + 1, right - 1));
                if (op == '+') stack.push(sum);
                else if(op=='-') stack.push(-sum);
                else if (op=='*') stack.push(stack.pop() * sum);
                else if (op=='/') stack.push(stack.pop() / sum);
            }
        }
        int res = 0;
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }

    //最长连续递增子序列的长度
    public int findLengthOfLCIS(int[] nums) {
        int ans = 1;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] < nums[i + 1]) {
                ans+=1;
                res = Math.max(res, ans);
            }else {
                ans = 0;
            }
        }
        return res;
    }

    //汇总区间 0,1,2,4,5,7
    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> ans = new ArrayList<>();
        int i=0;
        int n = nums.length;
        while (i < n) {
            int low = i++;
            while (i<n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;
            StringBuilder builder = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                builder.append("->");
                builder.append(nums[high]);
            }
            ans.add(builder.toString());
        }
        return ans;
    }

    //获取字符串中abc出现的次数
    public int getABCTimes(String str) {
        int ans = 0;
        String target = "abc";
        while (str.contains(target)) {
            str = str.substring(str.indexOf(target) + target.length());
            ans += 1;
        }
        return ans;
    }
    public int getABCTimes2(String str) {
        int ans = 0;
        String target = "abc";
        if (str.length() < target.length()) {
            return ans;
        }
        for (int i = 0; i < str.length(); i++) {
            if (i + 2 < str.length() && str.charAt(i) == 'a' && str.charAt(i+1) == 'b' && str.charAt(i+2) == 'c') {
                ans += 1;
                i = i + 2;
            }
        }
        return ans;
    }

    //二叉树最大深度
    public int maxDepth (TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        maxDepthRecursive(root, list, 0);
        return list.get(0);
    }
    void maxDepthRecursive(TreeNode root,List<Integer> list,int temp) {
        if (root != null) {
            temp++;
            if (root.left != null) {
                maxDepthRecursive(root.left, list, temp);
            }
            if (root.right != null) {
                maxDepthRecursive(root.right, list, temp);
            }

            if (root.left == null && root.right == null) {
                if (list.size() == 0) {
                    list.add(temp);
                }else {
                    int v = list.get(0);
                    if (v < temp) list.add(0,temp);
                }
            }
        }
    }

    public int maxDepth2 (TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth2(root.left), maxDepth2(root.right))+1;
    }

    //二叉树中的最大值
    public int maxNumInBinaryTree (TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null&&root.right==null) {
            return root.val;
        }
        return Math.max(maxNumInBinaryTree(root.left), maxNumInBinaryTree(root.right));
    }

    //二叉树中和为某一值的路径(一)
    public boolean hasPathSum (TreeNode root, int sum) {
        // write code here
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        sum -= root.val;
        return hasPathSum(root.left,sum)||hasPathSum(root.right,sum);
    }

    //二叉树之字型打印
    public ArrayList<ArrayList<Integer>> PrintZ(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (pRoot == null) {
            return ans;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean flag = false;
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (flag) {
                Collections.reverse(temp);
            }
            flag = !flag;
            ans.add(temp);

        }
        return ans;
    }

    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this(val,null);
        }
        ListNode(int val,ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //反转链表
    public ListNode ReverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead=head;
            head = next;
        }
        return newHead;
    }


    //Max deviation among all substrings  abbbcacbcdce
    int largestVariance(String s) {
        return 0;
    }

    //无重复字符的最长字串  abcabcbb
    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个最长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    //无重复字符的最长字串  abcabcbb
    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        if (s.length()==1) return 1;
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (temp.indexOf(c)!=-1) {
                i=i-(temp.length()-temp.indexOf(c));
                ans = Math.max(ans, temp.length());
                temp = "";
            }else {
                temp+=c;
            }
        }
        return Math.max(ans,temp.length());
    }
    @Test
    void test2() {
        int[] ans = new int[4];
        Arrays.fill(ans,-1);
        System.out.println(Arrays.toString(ans));
        ans=new int[]{8, 5, 1, 7, 10, 12};
        quickSort2(0,ans.length-1,ans);
        System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(calculator("1+2/2+(4-1)*4"));
        System.out.println(findLengthOfLCIS(new int[]{1,3,5,4,7}));
        System.out.println(summaryRanges(new int[]{0,1,2,4,5,7,8,10}));
        System.out.println("times "+getABCTimes("abcdefabcabbcabc1absabc abcabcasdassfasfsdafsdaf aabc"));
        System.out.println(getABCTimes2("abcdefabcabbcabc1"));
        new Thread(new Thread()).start();
        System.out.println("Aa".hashCode()); // 2112
        System.out.println("BB".hashCode()); // 2112
        TreeNode root=new TreeNode(new TreeNode(new TreeNode(3),2,new TreeNode(4)),1,new TreeNode(new TreeNode(6),5,null));
        System.out.println(maxDepth(root));
        System.out.println(maxDepth2(root));
        System.out.println();
        System.out.println(maxNumInBinaryTree(root));
        System.out.println(hasPathSum(root,7));
        System.out.println(PrintZ(root));

        /*new AnnotationConfigApplicationContext();
        new DefaultListableBeanFactory().registerBeanDefinition("",new GenericBeanDefinition());
        new DefaultListableBeanFactory().getBean("");*/
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }



    //最长回文子串
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                //dp[l + 1][r - 1]为上一个字串的状态
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    //最长回文子串
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    //合并两个排序的链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val+" -> ");
            node = node.next;
        }
    }
    @Test
    public void test3() {
        System.out.println(longestPalindrome("babad"));
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(7)));
        print(mergeTwoLists(l1,l2));

    }

    //数组中只出现一次的两个数字
    public int[] FindTwoNumsAppearOnce (int[] array) {
        int[] ans = new int[2];
        if (array.length==0){
            return ans;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        int count=0;
        for (int j : array) {
            map.merge(j, 1, Integer::sum);
        }
        for (Integer k:map.keySet()) {
            if (map.get(k)==1){
                ans[count] = k;
                count++;
            }
        }
        return ans;
    }


    static class ThreadLocaDemo {

        private static ThreadLocal<String> localVar = new ThreadLocal<String>();

        static void print(String str) {
            //打印当前线程中本地内存中本地变量的值
            System.out.println(str + " :" + localVar.get());
            //清除本地内存中的本地变量
            localVar.remove();
        }
        public static void main(String[] args) throws InterruptedException {
            new Thread(() -> {
                ThreadLocaDemo.localVar.set("local_A");
                print("A");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());
            },"A").start();
            Thread.sleep(1000);
            new Thread(() -> {
                ThreadLocaDemo.localVar.set("local_B");
                print("B");
                System.out.println("after remove : " + localVar.get());
            },"B").start();
        }
    }
}