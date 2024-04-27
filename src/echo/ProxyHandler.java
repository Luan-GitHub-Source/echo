package echo;

public class ProxyHandler extends RequestHandler {

    protected Correspondent peer;

    public void initPeer(String host, int port) {
        peer = new Correspondent();
        peer.requestConnection(host, port);
    }

    // etc.
}