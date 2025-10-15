import java.lang.*;
import java.util.*;
class Solution {
    public String[] solution(String[] expressions) {
        String[] answer = {};

        List<Integer> numbers = new ArrayList<>(Arrays.asList(2,3,4,5,6,7,8,9));
        List<String> unsolves = new ArrayList<>();
        String first, second, right;
        int oper;

        for(String expression : expressions) {
            first = expression.split(" ")[0];
            second = expression.split(" ")[2];
            right = expression.split(" ")[4];
            oper = "+".equals(expression.split(" ")[1]) ? 1 : -1;

            if("X".equals(right)) unsolves.add(expression);

            for(int i=2; i<=9; i++) {
                if(numbers.indexOf(i) == -1) continue;

                try {
                    if("X".equals(right)) {
                        Integer.parseInt(first, i);
                        Integer.parseInt(second, i);
                    } else if(Integer.parseInt(first, i) + Integer.parseInt(second, i) * oper
                            != Integer.parseInt(right, i)) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    numbers.remove(numbers.indexOf(i));
                }
            }
        }

        answer = new String[unsolves.size()];
        String result, curr;

        for(int i=0; i<unsolves.size(); i++) {
            result  = "";
            first    = unsolves.get(i).split(" ")[0];
            oper    = "+".equals(unsolves.get(i).split(" ")[1]) ? 1:-1;
            second    = unsolves.get(i).split(" ")[2];

            for(int n : numbers) {
                curr = Integer.toString(Integer.parseInt(first, n) + Integer.parseInt(second, n)*oper, n);

                if(!"".equals(result) && !curr.equals(result)) {
                    result = "?";
                    break;
                }

                result = curr;
            }

            answer[i] = unsolves.get(i).replace("X", result);
        }

        return answer;
    }
}