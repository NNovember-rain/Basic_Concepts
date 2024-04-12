package CustomException;

public class IdExistException extends  RuntimeException{
    public IdExistException(String s){
        super(s);
    }
}
