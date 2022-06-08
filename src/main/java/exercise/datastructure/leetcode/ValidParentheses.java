package exercise.datastructure.leetcode;

import java.util.Stack;
import java.util.stream.Stream;

public class ValidParentheses {
    public static void main(String[] args) {
        String a = "ab";
        System.out.println(new ValidParentheses().isValid(a));
        System.out.println(new ValidParentheses().isValid("[][]"));
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr=='('||curr=='{'||curr=='['){
                stack.push(curr);
            }else {
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if (curr==')'&&top!='(')
                    return false;
                if (curr=='}'&&top!='{')
                    return false;
                if (curr==']'&&top!='[')
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
