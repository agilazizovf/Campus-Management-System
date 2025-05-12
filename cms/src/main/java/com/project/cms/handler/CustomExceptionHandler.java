package com.project.cms.handler;

import com.project.cms.dto.response.ExceptionResponseModel;
import com.project.cms.dto.response.ValidationMessageResponse;
import com.project.cms.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionResponseModel handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

		ExceptionResponseModel error = new ExceptionResponseModel();

		error.setStatus(400);

		error.setMessage("Məlumat oxunmadı. Daxil edilən məlumatların dəqiqliyini yoxlayın.");

		error.setTimestamp(LocalDateTime.now());

		error.setInternalMessage(exception.getMessage());

		error.setSuccess(false);

		error.setType("HttpMessageNotReadableException");

		return error;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionResponseModel handleOurRuntimeException(CustomException exception) {

		ExceptionResponseModel error = new ExceptionResponseModel();

		error.setStatus(exception.getStatus());

		error.setMessage(exception.getMessage());

		error.setTimestamp(LocalDateTime.now());

		error.setInternalMessage(exception.getInternalMessage());

		error.setSuccess(false);

		error.setType(exception.getType());

		if (exception.getResult() != null) {
			BindingResult result = exception.getResult();
			List<FieldError> errors = result.getFieldErrors();

			List<ValidationMessageResponse> validations = new ArrayList<ValidationMessageResponse>();

			for (FieldError e : errors) {
				validations.add(new ValidationMessageResponse(e.getField(), e.getDefaultMessage()));
			}
			error.setValidations(validations);
		}

		return error;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ExceptionResponseModel handleAccessDeniedException(AccessDeniedException exception) {
		ExceptionResponseModel resp = new ExceptionResponseModel();
		resp.setStatus(HttpStatus.FORBIDDEN.value());
		resp.setInternalMessage(exception.getMessage());
		resp.setMessage("Giriş icazəsi yoxdur!");
		resp.setSuccess(false);
		resp.setType("FORBIDDEN");
		resp.setTimestamp(LocalDateTime.now());
		return resp;
	}

}