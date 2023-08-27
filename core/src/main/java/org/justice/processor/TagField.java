package org.justice.processor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.justice.annotation.ParamTypes;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import java.util.*;

@SuppressWarnings({"LombokGetterMayBeUsed", "unused"})
public class TagField implements Comparable<TagField>{

    private String name;
    private String type;
    private Boolean isPrivate;
   private Element element;
   private Set<Modifier> modifiers;

    List<String> params = new ArrayList<>();

    public TagField(VariableElement element){
        if(element.getKind() == ElementKind.FIELD){
            this.name = element.getSimpleName().toString();
            this.type = element.asType().toString();
            this.modifiers = element.getModifiers();
            this.isPrivate = modifiers.contains(Modifier.PRIVATE);
            ParamTypes paramTypes = element.getAnnotation(ParamTypes.class);
            if(ObjectUtil.isNotEmpty(paramTypes))
                this.params = Arrays.asList(paramTypes.value());

            this.element = element;
        }else
            throw new RuntimeException(StrUtil.format("字段的种类错误应该{},为实际为{}",ElementKind.FIELD,element));
    }

    @Override
    public int compareTo(TagField field) {
        return name.compareTo(field.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagField tagField = (TagField) o;
        return Objects.equals(name, tagField.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Set<Modifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<Modifier> modifiers) {
        this.modifiers = modifiers;
    }
}
