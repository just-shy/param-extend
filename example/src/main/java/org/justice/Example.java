package org.justice;

import lombok.Data;
import org.justice.annotation.ParamTypes;

@Data
public class Example {
    @ParamTypes("AddParam")
    String name;
    Integer age;

}
