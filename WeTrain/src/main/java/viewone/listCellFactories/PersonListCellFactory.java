package viewone.listCellFactories;

import viewone.bean.CourseEssentialBean;

public class PersonListCellFactory extends AbstractListCellFactory{

    @Override public void updateItem(CourseEssentialBean courseBean, boolean empty){
        updateWithParameter(courseBean, empty, "person");
    }
}
