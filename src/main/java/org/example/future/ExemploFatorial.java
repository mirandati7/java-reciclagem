package org.example.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExemploFatorial {

    private static final ExecutorService threadpool =
            Executors.newFixedThreadPool(3);

    public static void main(String args[]) throws
            InterruptedException, ExecutionException {

        Fatorial task = new Fatorial(20);
        System.out.println("Enviando a tarefa...");
        Future<Long> future = threadpool.submit(task);
        System.out.println("Task is submitted");
        while (!future.isDone()) {
            System.out.println("Tarefa não terminada ainda...");
            Thread.sleep(1); // espera para tentar novamente
        }
        System.out.println("Tarefa finalizada!");
        long factorial = (long) future.get();
        System.out.println("Fatorial de 10 é: " + factorial);
        threadpool.shutdown();

    }

    private static class Fatorial implements Callable<Long> {
        private final int number;

        public Fatorial(int number) {
            this.number = number;
        }

        @Override
        public Long call() {
            long output = 0;
            try {
                output = factorial(number);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExemploFatorial.class.getName()).log(Level.SEVERE, null, ex);
            }
            return output;
        }

        private long factorial(int number) throws
                InterruptedException {
            if (number < 0) {
                throw new IllegalArgumentException
                        ("Number must be greater than zero");
            }
            long result = 1;
            while (number > 0) {
                result = result * number;
                number--;
            }
            return result;
        }
    }
}