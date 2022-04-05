package viewone.listCellFactories;

import viewone.bean.CourseEssentialBean;

public class CourseListCellFactory extends AbstractListCellFactory{

    @Override public void updateItem(CourseEssentialBean courseBean, boolean empty){
        updateWithParameter(courseBean,empty,"\uD83D\uDC68");
    }
}
