package exceptions;

import database.DatabaseConnectionSingleton;

public class DBConnectionFailedException extends Exception{

    public void deleteDatabaseConn() {
        DatabaseConnectionSingleton.deleteInstance();
    }
}
