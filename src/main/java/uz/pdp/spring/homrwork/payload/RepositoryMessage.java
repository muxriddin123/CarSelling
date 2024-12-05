package uz.pdp.spring.homrwork.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryMessage {
    //bu class out,post recvestlarida malum amal bajarilgandan keyin qaytadiga massage
    private String message;
    private Boolean success;
    private Object data;
}
