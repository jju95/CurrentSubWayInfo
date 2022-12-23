package Assignment.sample.Dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {

    private T data;
    private String result;
    private String message;

    public static final Response<String> OK = new Response<>("", "200", "OK");

    public static <T> Response<T> success(T data) {
        return new Response<>(data, "200", "OK");
    }
}
