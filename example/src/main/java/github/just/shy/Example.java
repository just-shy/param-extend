package github.just.shy;

import lombok.Data;
import github.just.shy.annotation.ParamTypes;

@Data
public class Example {
    @ParamTypes("AddParam")
    public String name;
    @ParamTypes({"AddParam","EditParam","DeleteParam"})
    public Integer age;
}
