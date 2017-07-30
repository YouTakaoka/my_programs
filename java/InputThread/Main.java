import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        MyInput<String> input = new MyInput<>();
        ThreadCreator tc = new ThreadCreator();
        List<Future<Integer>> threads = input.carryOut(tc::create);
        
        for(Future<Integer> ret: threads){
            System.out.println(ret.get());
        }
    }
    
    private static class ThreadCreator {
        ExecutorService executor;
        
        public void ThreadCreator(){
            executor =  Executors.newCachedThreadPool();
        }

        public Future<Integer> create(String in) {
            Future<Integer> ret = executor.submit(new Worker(in));
            return ret;
        };
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

