module WeTrain {
    requires org.junit.jupiter.api;
    requires com.wetrain.wetrain;
    opens test to org.junit.platform.commons;
}