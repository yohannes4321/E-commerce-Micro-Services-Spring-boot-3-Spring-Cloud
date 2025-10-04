package microservices.Customer.ExceptionHandler;

import java.util.Map;

public record ErrorResponse (Map<String,String> errors){

}
