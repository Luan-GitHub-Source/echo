package echo;

import java.net.Socket;

public class MathHandler extends RequestHandler{
    public MathHandler(){
        super();
    }
    public MathHandler(Socket s){
        super(s);
    }
    private String calculate(String operator, double[]nums){
        double result = 0;
        if(operator.equalsIgnoreCase("add")){
            for(double num: nums){
                result += num;
            }
        } else if(operator.equalsIgnoreCase("mul")){
            result = 1;
            for(double num: nums){
                result *= num;
            }
        } else if(operator.equalsIgnoreCase("sub")){
            result = nums[0];
            for(int i = 1; i < nums.length; i++){
                result -= nums[i];
            }
        } else if(operator.equalsIgnoreCase("div")){
            result = nums[0];
            for(int i = 1; i < nums.length; i++){
                result /= nums[i];
            }
        } else {
            return "Invalid math operator";
        }
        return String.valueOf(result);
    }

    protected String response(String request) throws Exception {
        String[] tokens = request.split("\\s+");
        double[] nums = new double[tokens.length - 1];
        try {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Double.parseDouble(tokens[i+1]);
            }
        } catch(NumberFormatException e) {
            return "Invalid Number(s)";
        }
        return calculate(tokens[0], nums);
    }
}
