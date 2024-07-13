package pro.abnjava.examcloud.exam.rest.except

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

//    @ExceptionHandler(UnauthorizedException::class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    fun handleUnauthorizedException(ex: UnauthorizedException): ResponseEntity<ErrorResponse> {
//        return ResponseEntity(ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.message), HttpStatus.UNAUTHORIZED)
//    }

//    @ExceptionHandler(ResourceNotFoundException::class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
//        return ResponseEntity(ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.message), HttpStatus.NOT_FOUND)
//    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error. " + ex.message
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException(ex: Exception): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        return ResponseEntity(
            ErrorResponse(status.value(), "Bad Request. " + ex.message),
            status
        )
    }

}

/**
 * ErrorResponse DTO.
 */
data class ErrorResponse(val status: Int, val message: String?)
