import java.util.*;

class Solution {
    LinkedList<Integer> systems = new LinkedList<>();
    interface Expression {
        int interpret();
    }
    class Number implements Expression {
        int number;
        public Number(int number, int system) {
            this.number = (number / 100 * system * system) + (number % 100 / 10 * system) + number % 10;
        }
        public int interpret() {
            return number;
        }
        public String toString() {return number + "";}
    }
    abstract class Operation implements Expression {
        Expression left, right;
        public Operation(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }
    }
    class Add extends Operation {
        public Add(Expression left, Expression right) {super(left, right);}
        public int interpret() {
            return this.left.interpret() + this.right.interpret();
        }
        public String toString() {return "(+)"+interpret();}
    }
    class Subtract extends Operation {
        public Subtract(Expression left, Expression right) {super(left, right);}
        public int interpret() {
            return this.left.interpret() - this.right.interpret();
        }
        public String toString() {return "(-)"+interpret();}
    }
    class Equal extends Operation {
        public Equal(Expression left, Expression right) {super(left, right);}
        public int interpret() {
            return this.left.interpret() == this.right.interpret() ? 1 : 0;
        }
    }
    class Context {
        String[] tokens;
    }
    void detect(String context) {
        String[] tokens = context.split(" ");
        
        int size = systems.size();
        for (int s = 0; s < size; s++) {
            int system = systems.pop();
            parse(tokens, system);
        }
    }
    int base2int(int num, int base) {
        int result = 0;
        for (int i = 2; i >= 0; i--) {
            num %= Math.pow(base, i+1);
            result += (int) (num / Math.pow(base, i)) * Math.pow(10, i);
        }
        return result;
    }
    String make(String context) {
        String[] cs = context.split(" ");
        String[] tokens = new String[3];
        for (int i = 0; i < 3; i++) {
            tokens[i] = cs[i];
        }
        int result = -1;
        
        int size = systems.size();
        for (int s = 0; s < size; s++) {
            int system = systems.pop();
            int temp = base2int(parse(tokens, system), system);
            systems.add(system);
            if (result == -1) {
                result = temp;
            } else if (result != temp) {
                context = context.replace("X", "?");
                break;
            }
        }
        
        context = context.replace("X", result+"");
        
        return context;
    }
    int parse(String[] tokens, int system) {
        LinkedList<Expression> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                Expression left = stack.pop();
                Expression right = new Number(Integer.parseInt(tokens[++i]), system);
                stack.push(new Add(left, right));
            } else if (tokens[i].equals("-")) {
                Expression left = stack.pop();
                Expression right = new Number(Integer.parseInt(tokens[++i]), system);
                stack.push(new Subtract(left, right));
            } else if (tokens[i].equals("=")) {
                Expression left = stack.pop();
                Expression right = new Number(Integer.parseInt(tokens[++i]), system);
                Expression result = new Equal(left, right);
                if (result.interpret() == 1) {
                    systems.add(system);
                    return 1;
                } else return 0;
            } else {
                stack.push(new Number(Integer.parseInt(tokens[i]), system));
            }
        }
        return stack.pop().interpret();
    }
    public String[] solution(String[] expressions) {
        int start = Arrays.stream(expressions)
                    .flatMapToInt(s -> s.chars()
                                  .filter(c -> c >= '0' && c <= '9').map(c -> c - '0'))
                    .max().getAsInt() + 1;
        for (int i = start; i < 10; i++) {systems.add(i);}
        
        int cnt = expressions.length;
        for (String exp : expressions) {
            if (exp.indexOf("X") != -1) continue;
            detect(exp);
            cnt--;
        }
        
        String[] answer = new String[cnt];
        int idx = 0;
        for (String exp : expressions) {
            if (exp.indexOf("X") == -1) continue;
            answer[idx++] = make(exp);
        }
        
        return answer;
    }
}
