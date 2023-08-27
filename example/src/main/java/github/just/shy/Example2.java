package github.just.shy;

import github.just.shy.annotation.ParamTypes;
import lombok.Data;

@Data
public class Example2 {
    @ParamTypes("Delete")
    public String name;
    @ParamTypes({"Vo","Delete"})
    public Integer age;
}
