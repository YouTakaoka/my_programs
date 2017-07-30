import java.util.*;

public class Main {
    public static void main(String args[]) {
        MyInput<Double> input = new MyInput<>();
        List<Double> list = input.carryOut(s -> {
                Expression ex = new Expression(s);
                return ex.exec();
            });

        for(double r: list){
            System.out.println(r);
        }
    }
}

class Expression {
    private int length;
    private List<Operator> ops = new ArrayList<>();
    private List<Double> nums = new ArrayList<>();
    private List<Integer> indicesOfFirstOps = new ArrayList<>();

    private static Operator plus     = (n,m) -> n+m;
    private static Operator minus    = (n,m) -> n-m;
    private static Operator multiply = (n,m) -> n*m;
    private static Operator divide   = (n,m) -> n/m;    
    
    public Expression(String s){
        StringBuilder sb = new StringBuilder(s);
        int i;
        
        while((i = getFirstIndexOfOperator(sb.toString())) > 0){
            char op = sb.charAt(i);
            nums.add(Double.parseDouble(sb.substring(0,i)));

            try{
                ops.add(OperatorClass.charToOp(op));
            } catch(ExpectedOperatorException e) {
                e.printStackTrace();
            }
            
            if(OperatorClass.isFirstOp(op)){
                indicesOfFirstOps.add(ops.size() - 1);
            }
            sb.delete(0,i+1);
        }
        nums.add(Double.parseDouble(sb.toString()));
    }

    public double exec(){
        /* for debug: */
        // printParameters();

        // calculate first operators(*,/)
        // sweep from the end of list
        ListIterator<Integer> it = indicesOfFirstOps.listIterator(indicesOfFirstOps.size());
        while(it.hasPrevious()){
            int i = it.previous();
            calculate_i_thPart(i);
        }

        // calculate second operators(+,-)
        while(ops.size() > 0){
            calculate_i_thPart(0);
        }

        return nums.get(0);
    }

    public void calculate_i_thPart(int i){
        double n1, n2, res;
        Operator op = ops.get(i);
        n1 = nums.get(i);
        n2 = nums.get(i+1);
        res = op.exec(n1, n2);

        nums.set(i, res);
        nums.remove(i+1);
        ops.remove(i);
    }

    public int getFirstIndexOfOperator(String s){
        for(int i = 0 ; i < s.length() ; i++){
            if(OperatorClass.isOperator(s.charAt(i))){
                    return i;
            }
        }
        return -1;
    }

    private static class OperatorClass {
        public static boolean isOperator(char c){
            switch(c){
            case '*':
            case '/':
            case '+':
            case '-':
                return true;
            default:
                return false;
            }
        }

        public static boolean isFirstOp(char c){
            switch(c){
            case '*':
            case '/':
                return true;
            default:
                return false;
            }
        }

        public static Operator charToOp(char c) throws ExpectedOperatorException {
            switch(c){
            case '*':
                return multiply;
            case '/':
                return divide;
            case '+':
                return plus;
            case '-':
                return minus;
            }
            throw new ExpectedOperatorException("Got irregular character: " + Character.toString(c));
        }
    }

    public static class ExpectedOperatorException extends Exception {
        public ExpectedOperatorException(){
            super();
        }

        public ExpectedOperatorException(String mes){
            super(mes);
        }

        public ExpectedOperatorException(Throwable cause){
            super(cause);
        }

        public ExpectedOperatorException(String mes, Throwable cause){
            super(mes, cause);
        }
    }

    /* for debug */
    // public void printParameters(){
    //     System.out.println(sb.length());
    // }
}

@FunctionalInterface
interface Operator {
    double exec(double n, double m);
}
