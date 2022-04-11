package exception;

public class ExpiredCardException extends Exception{

    public void print(){
        System.out.println("User card expired.");
    }
}
