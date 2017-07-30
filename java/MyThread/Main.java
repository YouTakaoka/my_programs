import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        MyInput<Future<Integer>> input = new MyInput<>();

        // create thread pool
        ExecutorService executor = Executors.newCachedThreadPool();
        
        MyExec<String, Future<Integer>> createThread = in -> { return executor.submit(new Worker(in)); };
        List<Future<Integer>> threads = input.carryOut(createThread);
        
        for(Future<Integer> ret: threads){
            System.out.println(ret.get());
        }
    }
    
    private static class Worker implements Callable<Integer> {
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
}

