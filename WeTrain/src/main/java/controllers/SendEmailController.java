package controllers;

import boundaries.EmailSystemBoundary;
import exceptions.BrowsingNotSupportedException;
import exceptions.DBUnreachableException;
import beans.EmailBean;
import beans.UserBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class SendEmailController {

    private final NotificationsController notificationsController = new NotificationsController();

    public void sendEmail(UserBean sender, UserBean receiver, String object, String content) throws BrowsingNotSupportedException, URISyntaxException, IOException, DBUnreachableException, SQLException {
        new EmailSystemBoundary().sendEmail(new EmailBean(
                sender,
                receiver,
                object,
                content
        ));
        notificationsController.sendEmailReceivedNotification(
                sender.getFiscalCode(),
                receiver.getFiscalCode()
        );
    }
}
