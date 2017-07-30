import java.util.*;

public class Main {
    public static void main(String args[]) {
        MyInput<Integer> input = new MyInput<>();
        List<Integer> list = input.carryOut(s -> s.length());

        for(int i: list){
            System.out.println(i);
        }
    }
}
