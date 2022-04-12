package viewone.list_cell_factories;

import viewone.bean.CourseEssentialBean;

public class ExerciseListCellFactory extends AbstractListCellFactory{

    @Override public void updateItem(CourseEssentialBean courseBean, boolean empty){
        updateWithParameter(courseBean, empty, "exercise");
    }

}
