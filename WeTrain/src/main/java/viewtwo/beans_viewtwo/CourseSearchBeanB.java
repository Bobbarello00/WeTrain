package viewtwo.beans_viewtwo;

import beans.CourseSearchBean;

import java.util.Arrays;
import java.util.List;

public class CourseSearchBeanB implements CourseSearchBean {
    private final int fitnessLevel;
    private final List<String> days;
    private final String name;

    public CourseSearchBeanB(String name, int fitnessLevel, List<String> days) {
        this.fitnessLevel = fitnessLevel;
        this.name = name;
        this.days = days;
    }

    public String getFitnessLevel() {
        switch (fitnessLevel){
            case 2 -> {
                return "Advanced";
            } case 1 -> {
                return "Intermediate";
            } default -> {
                return "Base";
            }
        }
    }

    public Boolean[] getDays() {
        Boolean[] days = new Boolean[7];
        Arrays.fill(days, Boolean.FALSE);
        List<String> stringList = Arrays.asList("mo","tu","we","th","fr","sa","su");
        for(int i=0; i<6; i++){
            days[i] = this.days.contains(stringList.get(i));
        }
        return days;
    }

    public String getName() {
        return name;
    }

}
