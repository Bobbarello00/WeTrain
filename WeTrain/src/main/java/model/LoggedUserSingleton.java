package model;

public class LoggedUserSingleton {

    private static User user;

    private LoggedUserSingleton() {}

    //TODO caricare l'istanza direttamente dal database?
    public static User getInstance(){
        return user;
    }

    public static void setInstance(User user1){
        user = user1;
    }
}
