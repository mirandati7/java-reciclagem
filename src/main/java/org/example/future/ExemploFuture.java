package org.example.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * Classe que cria um pool de threads e cria uma thread do tipo GerarNumeroAleatorio
 que apenas gera um numero e o retorna para a classe que
 * criou a thread.
 *
 * @author Eduardo Santana
 *
 */
public class ExemploFuture {

    private static final ExecutorService threadpool =
            Executors.newFixedThreadPool(3);

    public static void main(String args[]) throws InterruptedException,
            ExecutionException {
        GerarNumeroAleatorio task = new GerarNumeroAleatorio();
        System.out.println("Processando a tarefa ...");
        Future<Integer> future = threadpool.submit(task);
        while (!future.isDone()) {
            System.out.println("A tarefa ainda não foi processada!");
            Thread.sleep(1); // sleep for 1 millisecond
            //before checking again
        }
        System.out.println("Tarefa completa!");
        long factorial = (long) future.get();
        System.out.println("O número gerado foi: " + factorial);
        threadpool.shutdown();
    }

    // classe que implementa a interface Callable e retorna um numero aleatorio
    private static class GerarNumeroAleatorio implements Callable<Integer> {

        @Override
        public Integer call() {
            Random rand = new Random();
            Integer number = rand.nextInt(100);
            return number;
        }

    }
}