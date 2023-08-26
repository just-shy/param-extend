package org.justice.processor;

import java.util.ArrayList;
import java.util.List;

public class TagField implements Comparable<TagField>{

    private String name;
    private String type;

    List<String> params = new ArrayList<>();

    public String getName() {
        return name;
    }

    public TagField setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public TagField setType(String type) {
        this.type = type;
        return this;
    }


    public List<String> getParams() {
        return params;
    }

    public TagField setParams(List<String> params){
        this.params = params;
        return this;
    }


    @Override
    public int compareTo(TagField field) {
        return name.compareTo(field.name);
    }
}
