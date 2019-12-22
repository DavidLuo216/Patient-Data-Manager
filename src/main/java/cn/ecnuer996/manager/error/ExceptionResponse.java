package cn.ecnuer996.manager.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class ExceptionResponse {

    @ExceptionHandler(ProdProcessOrderException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public SystemMessage prodProcessOrderException(ProdProcessOrderException e) {
        return new SystemMessage(500, e.getMessage());
    }

//    @ExceptionHandler(ProdProcessOrderException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public SystemMessage notFoundException(ProdProcessOrderException e){
//        return new SystemMessage(404,e.getMessage());
//    }

}
