import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        String[] lines = new String[100];
        int numOfLines;
        numOfLines = readUntilEof(lines);
        for(int i = 0 ; i < numOfLines ; i++){
            System.out.println(lines[i]);
        }
    }

    public static double calculatePartOfExpression(String op, double n1, double n2){
        switch(op){
        case '*':
            return n1*n2;
            break;
        case '/':
            return n1/n2;
            break;
        case '+':
            return n1+n2;
            break;
        case '-':
            return n1-n2;
            break;
        }
    }
    
    public static int calculateFromArray(int lengthOfExpression, double[] nums, char[] ops){
        for(int i=0 ; i < lengthOfExpression\2 ; i++){
            if(ops[i] == '*' || ops[i] == '/'){}
        }
    }
    
    public static void devideExpressionIntoArray(int lengthOfExpression, String ex, double[] nums, char[] ops){
        char[] arrayOfChars = ex.split("");
        for(int i=0 ; i <= l/2 ; i++){
            nums[i] = Integer.parseInt(arrayOfChars[2*i]);
            ops[i] = arrayOfChars[2*i+1];
        }
    }

    public static double executeMainCalculation(String ex){
        int l = ex.length();
        double[] nums = new double[l/2+1];
        char[] ops = new char[l/2];
        devideExpressionIntoArray(l, ex, nums, ops);
        return calculateFromArray(l, nums, ops)
    }
    
    public static int readUntilEof(String[] ls){
        int l = 0;
        String InputText;
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            ls[l] = scan.nextLine();
            l++;
        }

        scan.close();

        return l;
    }
}
