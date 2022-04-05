package viewone.listCellFactories;

import viewone.bean.CourseEssentialBean;

public class NotificationListCellFactory extends AbstractListCellFactory{

    @Override public void updateItem(CourseEssentialBean courseBean, boolean empty){
        updateWithParameter(courseBean, empty, "notification");
    }
}
