import java.util.Scanner;

class Stdin {
    public static void main(String args[]) {
        String[] lines = new String[100];
        int numOfLines;
        numOfLines = eof(lines);
        for(int i = 0 ; i < numOfLines ; i++){
            System.out.println(lines[i]);
        }
    }
    
    public static void inputLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input > ");

        String input = scanner.nextLine();

        System.out.println(input);
        scanner.close();
    }

    public static int eof(String[] ls){
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
