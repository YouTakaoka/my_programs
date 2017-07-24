import java.util.*;

public class Main {
    public static void main(String args[]) {
        List<String> lines = new ArrayList<>();
        int numOfLines;
        numOfLines = readUntilEof(lines);
        for(int i = 0 ; i < numOfLines ; i++){
            Expression exp = new Expression(lines.get(i))
            System.out.println(exp.executeMainCalculation());
        }
    }
    
    public static int readUntilEof(List<String> ls){
        String InputText;
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            ls.add(scan.nextLine());
        }

        scan.close();

        return ls.size();
    }
}


class Expression {
    private StringBuilder sb;
    
    Expression(String s){
        sb = new StringBuilder(s);
    }
    
    public static double calculatePartOfExpression(char op, double n1, double n2){
        switch(op){
        case '*':
            return n1*n2;
        case '/':
            return n1/n2;
        case '+':
            return n1+n2;
        case '-':
            return n1-n2;
        }
        return 0;
    }

    public static void calculate_i_thPart(int i){
        double n1, n2;
        char op = sb.charAt(i);

        n1 = getPreviousNumberOf(i);
        n2 = getNextNumberOf(i);
            
        nums.set(i, calculatePartOfExpression(op, n1, n2));
        nums.remove(i+1);
        ops.remove(i);
    }

    getPreviousNumberOf(int i){
        List<Integer> listOfOperatorIndices = getListOfOperatorIndices();
        int indexOf_i = listOfOperatorIndices.indexOf(i);
        sb.subSequence(listOfOperatorIndices.get(indexOf_i-1)+1, listOfOperatorIndices.get(indexOf_i)-1);
    }

    getNextNumberOf(int i){
        
    }

    public static double executeMainCalculation(){
        // debug:
        // printParameters();
        List<Integer> listOfOperatorIndices = getListOfOperatorIndices();
        Iterator<String> opsIterator = listOfOperatorIndices.iterator();

        if(sb.length() == 1){
            return Double.parseDouble(sb.toString());
        }

        while(opsIterator.hasNext()){
            int i = opsIterator.next();
            String op = sb.charAt(i);
            if(op == '*' || op == '/'){
                calculate_i_thPart(i);
                return executeMainCalculation();
            }
        }

        calculate_i_thPart(listOfOperatorIndices.get(0));
        return executeMainCalculation();
    }

    public static List<Integer> getListOfOperatorIndices(){
        List<Integer> listOfOperatorIndices = new ArrayList<>();
        for(int i = 0 ; i < sb.length() ; i++){
            if(isOperator(sb.charAt(i)){
                    listOfOperatorIndices.add(i);
            }
        }
        return listOfOperatorIndices;
    }

    public static boolian isOperator(char c){
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


    // for debug
    public static void printParameters(){
        System.out.println(sb.length());
        System.out.println(nums);
        System.out.println(ops);
    }
}
