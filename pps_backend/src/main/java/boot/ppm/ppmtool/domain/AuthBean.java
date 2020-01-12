package boot.ppm.ppmtool.domain;

/**
 * Created by Nick on 1/4/2020.
 */
public class AuthBean {
    private String message;

    public AuthBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
