package boundaries;

import exceptions.BrowsingNotSupportedException;
import viewone.beans.EmailBean;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class EmailSystemBoundary {

    public void sendEmail(EmailBean emailBean) throws BrowsingNotSupportedException, URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        if(desktop.isSupported(Desktop.Action.BROWSE)){
            String body = emailBean.getBody().replaceAll(" ", "%20");
            String object = emailBean.getObject().replaceAll(" ", "%20");
            desktop.mail(new URI(String.format("mailto:%s?subject=%s&body=%s",
                    emailBean.getReceiver().getEmail(),
                    object,
                    body)));
        }else{
            throw new BrowsingNotSupportedException();
        }
    }
}