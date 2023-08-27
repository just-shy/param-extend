package org.justice.processor;

import javax.lang.model.element.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings({"LombokGetterMayBeUsed", "unused"})
public class TagClass implements Comparable<TagClass>{

    private String className;

    private String packageName;
    private Boolean isAbstract;

    private Set<Modifier> modifiers;
    private Element element;


    private TreeSet<TagField> fields = new TreeSet<>();
    public TagClass(Element element){
        element = getClassElement(element);
        if(element.getKind().equals(ElementKind.CLASS)){
            this.packageName = ProcessorContext.elements.getPackageOf(element).toString();
            this.className = element.getSimpleName().toString();
            this.isAbstract = element.getModifiers().contains(Modifier.ABSTRACT);
            this.modifiers = element.getModifiers();
            this.element = element;
        }
    }

    public String getAllName(){
         return packageName + "." + className;
    }


    private Element getClassElement(Element element){
        while (!element.getKind().equals(ElementKind.PACKAGE)){
            if(element.getKind().equals(ElementKind.CLASS))
                return  element;
            element = element.getEnclosingElement();
        }
        throw new RuntimeException("注解处理出错,找不到目标类");
    }

    @Override
    public int compareTo(TagClass other) {
        return getAllName().compareTo(other.getAllName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagClass tagClass = (TagClass) o;
        return Objects.equals(this.getAllName(),tagClass.getAllName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAllName());
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Boolean isAbstract() {
        return isAbstract;
    }

    public TreeSet<TagField> getFields() {
        return fields;
    }

    public void setFields(TreeSet<TagField> fields) {
        this.fields = fields;
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
