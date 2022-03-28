package id.co.nds.shop.exceptions;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class CustomClientException extends MethodArgumentNotValidException {

    public CustomClientException(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
        //TODO Auto-generated constructor stub
    }

}
