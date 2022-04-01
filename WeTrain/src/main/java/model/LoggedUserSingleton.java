package model;

public class LoggedUserSingleton {

    private static User user;

    private LoggedUserSingleton() {}

    public User getInstance(){
        return user;
    }

    public static void setInstance(User user1){
        user = user1;
    }
}
