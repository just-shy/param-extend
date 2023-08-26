package org.justice.processor;

import java.util.Objects;

public class TagClass {

    private String className;
    private String packageName;

    public String getClassName() {
        return className;
    }

    public TagClass setClassName(String className) {
        this.className = className;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public TagClass setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagClass tagClass = (TagClass) o;
        return Objects.equals(className, tagClass.className) && Objects.equals(packageName, tagClass.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, packageName);
    }
}
