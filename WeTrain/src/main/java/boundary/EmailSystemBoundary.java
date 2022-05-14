package boundary;

import exception.BrowsingNotSupportedException;
import viewone.bean.EmailBean;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class EmailSystemBoundary {
    
    public void sendEmail(EmailBean emailBean) throws BrowsingNotSupportedException, URISyntaxException, IOException {
        Desktop desktop = Desktop.getDesktop();
        if(desktop.isSupported(Desktop.Action.BROWSE)){
            desktop.mail(new URI(String.format("mailto:%s?subject=%s&body=%s",
                    emailBean.getReceiver().getEmail(),
                    emailBean.getObject(),
                    emailBean.getBody())));
        }else{
            throw new BrowsingNotSupportedException();
        }
    }
}
