package Assignment.sample.Common;

public class CustomException extends RuntimeException {

    CustomException() {
    }

    public CustomException(String message) {
        super(message); // RuntimeException 클래스의 생성자를 호출합니다.
    }
}
