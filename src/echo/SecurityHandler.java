package echo;

import java.util.HashMap;

public class SecurityHandler extends ProxyHandler{

    static HashMap<String, String> securityMap = new HashMap<String, String>();
    private boolean loggedin = false;

    @Override
    public synchronized String response(String msg) throws IndexOutOfBoundsException{

        if(loggedin){
            this.peer.send(msg);
            String peerResponse = peer.receive();
            return peerResponse;
        }

        System.out.println("respone");
        String[] tokens = msg.split("\\s+");
        String cmd = tokens[0];//new or login
        String username = tokens[1];
        String password = tokens[2];

        System.out.println(cmd + " " + username + " " + password);

        if(cmd.equalsIgnoreCase("new")){
            Boolean checkExistingUser = securityMap.containsKey(username);
            if(!checkExistingUser){
                securityMap.put(username,password);
                return "Account created!";
            }
            else
                return "Username already exists";
        } else if(cmd.equalsIgnoreCase("login")){
            Boolean checkExistingUser = securityMap.containsKey(username);
            if(!checkExistingUser){
                return "Not an existing user";
            }
            else {
                if(securityMap.get(username).equals(password)){
                    loggedin = true;
                    return "Logged in";
                } else
                    return "Incorrect password";
            }

        } else
            return "You must login to continue";

//        String checkCache = cache.search(msg);
//        if(checkCache != null) {
//            return checkCache;
//        }else {
//            this.peer.send(msg);
//            String peerResponse = peer.receive();
//            cache.update(msg, peerResponse);
//            return peerResponse;
//        }
    }
}
