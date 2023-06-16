import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {
    public Integer id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;

}
