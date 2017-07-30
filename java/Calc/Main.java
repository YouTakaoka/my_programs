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

    private Operator plus     = (n,m) -> n+m;
    private Operator minus    = (n,m) -> n-m;
    private Operator multiply = (n,m) -> n*m;
    private Operator divide   = (n,m) -> n/m;    
    
    public void Expression(String s){
        StringBuilder sb = new StringBuilder(s);
        
        while((int i = getFirstIndexOfOperator(sb.toString())) > 0){
            char op = sb.charAt(i);
            nums.add(Double.parseDouble(sb.substring(0,i-1)));
            ops.add(OperatorClass.charToOp(op));
            if(OperatorClass.isFirstOp(op)){
                indicesOfFirstOps.add(i);
            }
            sb.delete(0,i);
        }
        nums.add(Double.parseDoubles(b.toString()));
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
        for(int i = 0 ; i < ops.size() ; i++){
            calculate_i_thPart(i);
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

    public List<NumberIndices> getListOfNumberIndices(){
        List<Integer> listOfOperatorIndices = getListOfOperatorIndices();
        Iterator it = listOfOperatorIndices.iterator();
        List<NumberIndices> listOfNumberIndices = new ArrayList<>();
        int begin = 0;
        int end;

        while(it.hasNext()){
            end = it.next() - 1;
            listOfNumberIndices.add(new NumberIndices(begin, end));
            begin = end + 2;
        }

        end = sb.length() - 1;
        listOfNumberIndices.add(new NumberIndices(begin, end));

        return listOfNumberIndices;
    }

    private static class OperatorClass {
        public boolean isOperator(char c){
            switch(c){
            case '*':
            case '/':
            case '+':
            case '-':
                return true;
            default:
                return false;
            }
            return false;
        }

        public boolean isFirstOp(char c){
            switch(c){
            case '*':
            case '/':
                return true;
            default:
                return false;
            }
            return false;
        }

        Operator charToOp(char c) throws ExpectedOperatorException {
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
            throw new ExpectedOperatorException(c);
        }

        private static class ExpectedOperatorException extends Exception {
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
    }

    // for debug
    public void printParameters(){
        System.out.println(sb.length());
    }
}

@FunctionalInterface
Interface Operator {
    double exec(double n, double m)
}
