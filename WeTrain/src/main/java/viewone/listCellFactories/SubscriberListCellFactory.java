package viewone.listCellFactories;

import viewone.bean.CourseEssentialBean;

public class SubscriberListCellFactory extends AbstractListCellFactory{

    @Override public void updateItem(CourseEssentialBean courseBean, boolean empty){
        updateWithParameter(courseBean, empty, "subscriber");
    }
}
