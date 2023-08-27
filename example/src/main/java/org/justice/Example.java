package org.justice;

import lombok.Data;
import org.justice.annotation.ParamTypes;

@Data
public class Example {
    @ParamTypes("AddParam")
    public String name;
    @ParamTypes({"AddParam","EditParam","DeleteParam"})
    public Integer age;
}
