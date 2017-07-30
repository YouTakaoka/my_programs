import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        MyInput<String,Future<Integer>> input = new MyInput<>();
        ThreadCreator tc = new ThreadCreator();
        List<Future<Integer>> threads = input.carryOut(tc::create);
        
        for(Future<Integer> ret: threads){
            System.out.println(ret.get());
        }
    }
}

    
class ThreadCreator {
    ExecutorService executor;
        
    public void ThreadCreator(){
        executor =  Executors.newCachedThreadPool();
    }

    public Future<Integer> create(String in) {
        Future<Integer> ret = executor.submit(new Worker(in));
        return ret;
    };
}

class Worker implements Callable<Integer> {
    String s;
            
    public Worker(String s){
        this.s = s;
    }
            
    @Override
    public Integer call(){
        int i;
        for(i = 0 ; i < Integer.parseInt(s) ; i++){}
        return i;
    }
}


class MyInput<S,T> {
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
