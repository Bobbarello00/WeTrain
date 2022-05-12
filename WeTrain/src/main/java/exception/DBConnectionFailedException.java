package exception;

import database.DatabaseConnectionSingleton;

public class DBConnectionFailedException extends Throwable{

    public void deleteDatabaseConn() {
        DatabaseConnectionSingleton.deleteInstance();
    }
}
