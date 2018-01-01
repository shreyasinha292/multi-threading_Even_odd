package multithreading_oddeven;


public class Even_Odd {

    public static void main(String[] args) {
        Printer print = new Printer();
        TaskEvenOdd t1 = new TaskEvenOdd(print, 100, false);
        TaskEvenOdd t2 = new TaskEvenOdd(print, 100, true);
        t1.start();
        t2.start();
    }

}

class TaskEvenOdd extends Thread {

    private int n;
    private Printer print;
    private boolean isEvenNumber;

    TaskEvenOdd(Printer print, int n, boolean isEvenNumber) {
        this.print = print;
        this.n = n;
        this.isEvenNumber = isEvenNumber;
    }

    @Override
    public void run() {

        
        int number = isEvenNumber == true ? 2 : 1;
        while (number <= n) {

            if (isEvenNumber) {
                
                print.printEven(number);
                
            } else {
                
                print.printOdd(number);
               
            }
            number += 2;
        }

    }

}

class Printer {

    boolean isOdd = false;

    synchronized void printEven(int number) {

        while (isOdd == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            	System.out.println(e);
            }
        }
        System.out.println("Even:" + number);
        isOdd = false;
        notifyAll();
    }

    synchronized void printOdd(int number) {
        while (isOdd == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println("Odd:" + number);
        isOdd = true;
        notifyAll();
    }

}
