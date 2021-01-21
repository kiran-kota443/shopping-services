package com.shopping.kart.exception;

import java.nio.file.AccessDeniedException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.shopping.kart.core.ApplicationResponse;
import com.shopping.kart.error.Error;
import com.shopping.kart.error.ErrorDetails;
import com.shopping.kart.error.ValidationError;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = ShoppingKartException.class)
	public ApplicationResponse<ErrorDetails> handle(HttpServletResponse response, ShoppingKartException e) {
		LOGGER.error("Exception received in DcCustDirectExceptionHandler", e);
		
		ErrorDetails errorDetails = new ErrorDetails();
		Error error = new Error();
		List<Error> errorList = new ArrayList<Error>();
		error.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
		if (e.getMessage() != null && e.getErrorCd() != 0) {
			error.setMessage(e.getMessage());
		} else {
			error.setMessage("Application Exception. Please contact system administrator.");
		}
		//TODO change this
		if (e.getErrorCd() != HttpStatus.OK.value()) {
			response.setStatus(e.getErrorCd());
			error.setCode(Integer.toString(e.getErrorCd()));
		} else {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			error.setCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}

		errorList.add(error);
		errorDetails.setErrors(errorList);
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ApplicationResponse<ErrorDetails> handleGenericException(Exception e) {
		LOGGER.error("Exception received in GenericExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = ShoppingKartUnhandledException.class)
	public ApplicationResponse<ErrorDetails> handleUnhandledException(Exception e) {
		LOGGER.error("Exception received in DcCustDirectUnhandledExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApplicationResponse<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		LOGGER.error("Exception received in MethodArgumentNotValidExceptionHandler", e);
		BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();        
        List<ValidationError> validationErrorList = processFieldErrors(fieldErrors);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.BAD_REQUEST.value()), "Input data not valid. Check validationErrors field.");
		errorDetails.setValidationErrors(validationErrorList);
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}

	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	public ApplicationResponse<ErrorDetails> handleUnsupportedMediaException(Exception e) {
		LOGGER.error("Exception received in UnsupportedMediaExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
				e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ApplicationResponse<ErrorDetails> handleValidationError(Exception e) {
		LOGGER.error("Exception received in ValidationErrorHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ApplicationResponse<ErrorDetails> handleHttpRequestMethodNotSupportedException(Exception e) {
		LOGGER.error("Exception received in HttpRequestMethodNotSupportedExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.BAD_REQUEST.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = AccessDeniedException.class)
	public ApplicationResponse<ErrorDetails> handleAccessDeniedException(Exception e) {
		LOGGER.error("Exception received in AccessDeniedExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.FORBIDDEN.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public ApplicationResponse<ErrorDetails> handleNoHandlerFoundException(Exception e) {
		LOGGER.error("Exception received in NoHandlerFoundExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.NOT_FOUND.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = HttpServerErrorException.class)
	public ApplicationResponse<ErrorDetails> handleHttpServerErrorException(Exception e) {
		LOGGER.error("Exception received in NoHandlerFoundExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = HttpClientErrorException.class)
	public ApplicationResponse<ErrorDetails> handleHttpClientErrorException(Exception e) {
		LOGGER.error("Exception received in NoHandlerFoundExceptionHandler", e);
		ErrorDetails errorDetails = populateErrorDetails(Integer.toString(HttpStatus.NOT_FOUND.value()), e.getMessage());
		ApplicationResponse<ErrorDetails> appResponse = new ApplicationResponse<ErrorDetails>();
		appResponse.setServiceResponse(errorDetails);
		return appResponse;
	}
	
	
	
	private ErrorDetails populateErrorDetails(String errorCode, String exceptionMsg) {
		ErrorDetails errorDetails = new ErrorDetails();
		Error error = new Error();
		List<Error> errorList = new ArrayList<Error>();
		error.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());

		error.setCode(errorCode);
		if (exceptionMsg != null) {
			error.setMessage(exceptionMsg);
		} else {
			error.setMessage("Application Exception. Please contact system administrator.");
		}

		errorList.add(error);
		errorDetails.setErrors(errorList);

		return errorDetails;
	}
	
    private List<ValidationError> processFieldErrors(List<FieldError> fieldErrors) {
		List<ValidationError> validationErrorList = new ArrayList<ValidationError>();
        for (FieldError fieldError: fieldErrors) {
        	ValidationError validationError = new ValidationError();
        	validationError.setObjectName(fieldError.getObjectName());
        	validationError.setField(fieldError.getField());
        	validationError.setFieldMessage(fieldError.getDefaultMessage());
        	validationErrorList.add(validationError);
        }
        return validationErrorList;
    }
}
