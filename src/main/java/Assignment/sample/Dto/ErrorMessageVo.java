package Assignment.sample.Dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
public class ErrorMessageVo {
    public Long status;
    public String code;
    public String message;
    public String link;
    public String developerMessage;
    public Integer total;
}



