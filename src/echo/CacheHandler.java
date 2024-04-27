package echo;

public class CacheHandler extends ProxyHandler {

    private SafeTable cache;

    public CacheHandler() {
        cache = new SafeTable();
    }

    @Override
    public synchronized String response(String msg){
        String checkCache = cache.search(msg);
        if(checkCache != null) {
            return checkCache;
        }else {
            this.peer.send(msg);
            String peerResponse = peer.receive();
            cache.update(msg, peerResponse);
            return peerResponse;
        }
    }
}