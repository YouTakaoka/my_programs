import java.util.*;

public class Main {
    public static void main(String args[]) {
        List<String> lines = new ArrayList<>();
        int numOfLines;
        numOfLines = readUntilEof(lines);
        for(int i = 0 ; i < numOfLines ; i++){
            Expression exp = new Expression(lines.get(i));
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

    public double executeMainCalculation(){
        // debug:
        // printParameters();
        List<Integer> listOfOperatorIndices = getListOfOperatorIndices();
        Iterator<Integer> opsIterator = listOfOperatorIndices.iterator();

        if(sb.length() == 1){
            return Double.parseDouble(sb.toString());
        }

        while(opsIterator.hasNext()){
            int i = opsIterator.next();
            char op = sb.charAt(i);
            if(op == '*' || op == '/'){
                calculate_i_thPart(i);
                return executeMainCalculation();
            }
        }

        calculate_i_thPart(listOfOperatorIndices.get(0));
        return executeMainCalculation();
    }

    public void calculate_i_thPart(int indexOfOperatorList){
        double n1, n2, res;
        NumberIndices num1,num2;
        char op = sb.charAt(getListOfOperatorIndices().get(indexOfOperatorList));
        int beginOfPart, endOfPart;

        num1 = getListOfNumberIndices().get(indexOfOperatorList);
        num2 = getListOfNumberIndices().get(indexOfOperatorList + 1);
        n1 = getNumber(num1);
        n2 = getNumber(num2);
        beginOfPart = num1.getBeginningIndex();
        endOfPart = num2.getEndingIndex();

        res = calculatePartOfExpression(op, n1, n2);
        sb.replace(beginOfPart, endOfPart, Double.toString(res));
    }

    public double getNumber(NumberIndices num){
        int begin = num.getBeginningIndex();
        int end = num.getEndingIndex();
        return Double.parseDouble(sb.substring(begin, end));
    }

    public double calculatePartOfExpression(char op, double n1, double n2){
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

    public List<Integer> getListOfOperatorIndices(){
        List<Integer> listOfOperatorIndices = new ArrayList<>();
        for(int i = 0 ; i < sb.length() ; i++){
            if(isOperator(sb.charAt(i))){
                    listOfOperatorIndices.add(i);
            }
        }
        return listOfOperatorIndices;
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
    }

    // for debug
    public void printParameters(){
        System.out.println(sb.length());
    }
}

class NumberIndices{
    private int begin;
    private int end;
        
    public void NumberIndices(int begin, int end){
        this.begin = begin;
        this.end = end;
    }

    public int getBeginningIndex(){
        return begin;
    }

    public int getEndingIndex(){
        return end;
    }
}
