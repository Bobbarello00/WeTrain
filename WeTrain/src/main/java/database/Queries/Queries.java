package database.queries;

public abstract class Queries {

    protected static final String SELECT_ALL = "SELECT * ";
    protected static final String WHERE_USER = "WHERE User = ?";
    protected static final String LIMIT_10 = "LIMIT 10";
    protected static final String LIMIT_30 = "LIMIT 30";
    protected static final String UPDATE_MYDB_ATHLETE = "UPDATE mydb.Athlete ";
    protected static final String FROM_MYDB_COURSE = "FROM mydb.Course ";
    protected static final String WHERE_ID_COURSE = "WHERE idCourse = ?";
    protected static final String UPDATE_MYDB_COURSE = "UPDATE mydb.Course ";
    protected static final String WHERE_COURSE = "WHERE Course = ?";
    protected static final String FROM_MYDB_ATHLETE = "FROM mydb.Athlete ";
    protected static final String WHERE_TRAINER = "WHERE Trainer = ?";
    protected static final String FROM_MYDB_LESSON = "FROM mydb.Lesson ";
}
