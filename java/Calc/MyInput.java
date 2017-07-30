import java.util.*;

class MyInput<T> {
    public List<T> carryOut(MyExec<String,T> method){
        List<T> res = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s = sc.nextLine();
            res.add(method.exec(s));
        }
        return res;
    }
}

@FunctionalInterface
interface MyExec<S,T> {
    T exec(S in);
}
