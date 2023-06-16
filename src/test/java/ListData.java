import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListData {
    public int id;
    public String name;
    public int year;
    public String color;
    public String pantone_value;
}
