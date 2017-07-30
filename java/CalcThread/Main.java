import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws InterruptedException, ExecutionException {
        MyInput<Future<Double>> input = new MyInput<>();

        // create thread pool
        ExecutorService executor = Executors.newCachedThreadPool();
        
        List<Future<Double>> threads = input.carryOut(in -> {
                return executor.submit(new Worker(in));
            });
        
        for(Future<Double> ret: threads){
            System.out.println(ret.get());
        }
    }
    
    private static class Worker implements Callable<Double> {
        String s;
            
        public Worker(String s){
            this.s = s;
        }
            
        @Override
        public Double call(){
            Expression ex = new Expression(s);
            return ex.exec();
        }
    }
}
