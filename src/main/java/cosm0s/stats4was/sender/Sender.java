package cosm0s.stats4was.sender;

public interface Sender {

    public boolean isConnected();

    public void send(String host, String metrica);

}
