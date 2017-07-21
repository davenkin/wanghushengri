package davenkin.wanghushengri.exception;

/**
 * Created by yteng on 11/27/16.
 */
public class ErrorResponse {
    private int httpStatusCode;
    private String errorMessage;

    public ErrorResponse(int httpStatusCode, String errorMessage) {
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;
    }

    public static ErrorResponse of(int httpStatusCode, String errorMessage) {
        return new ErrorResponse(httpStatusCode, errorMessage);
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
