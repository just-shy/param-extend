package org.justice;

import lombok.Data;
import org.justice.annotation.ParamTypes;

@Data
public class Example2 {
    @ParamTypes("Delete")
    public String name;
    @ParamTypes({"Vo","Delete"})
    public Integer age;
}
