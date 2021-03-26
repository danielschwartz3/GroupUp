package ca.mcgill.ecse428.groupup;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<Object> invalidInputError(Exception ex){
    HttpStatus status = HttpStatus.BAD_REQUEST;
    HashMap<String,String> body = new HashMap<>();
    String errMsg = ex.getLocalizedMessage();
    body.put("Error Type", "Illegal arguement");
    body.put("detail", errMsg);
    return ResponseEntity.status(status).body(body);
  }
  
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> genericError(Exception ex){
    HttpStatus status = HttpStatus.BAD_REQUEST;
    HashMap<String,String> body = new HashMap<>();
    ex.printStackTrace();
    String errMsg = ex.getLocalizedMessage();
    body.put("Error Type", "Unknown Error");
    body.put("detail", errMsg==null?"No Message": errMsg);
    return ResponseEntity.status(status).body(body);
  }
  
}
