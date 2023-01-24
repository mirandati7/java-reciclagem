package org.example.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExemploThreadParalela {

    private static final ExecutorService threadpool =
            Executors.newFixedThreadPool(3);

    public static void main(String args[]) throws  InterruptedException, ExecutionException {

        ForSequencial tarefa1 = new ForSequencial();
        ForSequencial tarefa2 = new ForSequencial();
        ForSequencial tarefa3 = new ForSequencial();
        ForSequencial tarefa4 = new ForSequencial();
        ForSequencial tarefa5 = new ForSequencial();

        System.out.println("Processando a tarefa ...");
        Future<Integer> futureT1 = threadpool.submit(tarefa1);
        Future<Integer> futureT2 = threadpool.submit(tarefa2);
        Future<Integer> futureT3 = threadpool.submit(tarefa3);
        Future<Integer> futureT4 = threadpool.submit(tarefa4);
        Future<Integer> futureT5 = threadpool.submit(tarefa5);

        while (!futureT1.isDone() && futureT2.isDone() && futureT3.isDone()
                                  && futureT4.isDone() && futureT5.isDone()) {
            System.out.println("As tarefas ainda não foram processadas!");
            Thread.sleep(1); // sleep for 1 millisecond //before checking again
        }

        System.out.println("Tarefa completa!");
        long valor = futureT1.get();
        valor = valor + futureT2.get() + futureT3.get() + futureT4.get() + futureT5.get();
        System.out.println("A soma dos valores gerados são: " + valor);
        threadpool.shutdown();
    }

    private static class ForSequencial implements Callable<Integer> {
        @Override
        public Integer call() {
            Integer number = 0;
            for (int i = 0; i <= 1000; i++) {
                number =+i;
                System.out.println("For Gerado: " + number);
            }
            return number;
        }
    }

    private static class GerarNumeroAleatorio implements Callable<Integer> {
        @Override
        public Integer call() {
            Random rand = new Random();
            Integer number = rand.nextInt(100);
            System.out.println("Valor Gerado: " + number);
            return number;
        }
    }

}