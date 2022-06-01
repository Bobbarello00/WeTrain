module WeTrain {
    requires org.junit.jupiter.api;
    requires com.wetrain.wetrain;
    requires java.sql;
    opens test to org.junit.platform.commons;
}