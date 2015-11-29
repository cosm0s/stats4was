package cosm0s.stats4was.sender;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LoadSenders {

    private List<Sender> sendersClass;

    public LoadSenders(String senders){
        try {
            getAllSenders(senders);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void getAllSenders(String senders) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.sendersClass = new LinkedList<Sender>();
        List<String> senderArray = Arrays.asList(senders.split(";"));
        for(String senderName:senderArray){
            Class senderClass = Class.forName(senderName);
            Sender sender = (Sender) senderClass.newInstance();
            sender.init();
            this.sendersClass.add(sender);
        }
    }

    public List<Sender> getSendersClass() {
        return sendersClass;
    }
}
