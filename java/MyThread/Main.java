import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future<Integer> ret = executor.submit(new Worker("123"));
      
        System.out.println(ret.get());

    }

    private static class Worker implements Callable<Integer> {
        String s;
            
        Worker(String s){
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

