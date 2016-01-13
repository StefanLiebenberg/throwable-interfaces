package org.slieb.generate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class IndentStringBuilder extends AbstractAppendable<IndentStringBuilder> {

    private final AtomicInteger atomicInteger;
    private final StringBuilder stringBuilder;
    private final Set<Class<?>> imports;
    private String packageName;

    public IndentStringBuilder() {
        atomicInteger = new AtomicInteger(0);
        stringBuilder = new StringBuilder();
        imports = new HashSet<>();
    }

    public Integer getIndent() {
        return atomicInteger.get();
    }

    public IndentStringBuilder incrementIndent() {
        atomicInteger.incrementAndGet();
        return this;
    }

    public IndentStringBuilder decrementIndent() {
        atomicInteger.decrementAndGet();
        return this;
    }

    public void setIndent(Integer indent) {
        atomicInteger.set(indent);
    }


    public IndentStringBuilder openComment() {
        stringBuilder.append("/**");
        return this;
    }

    public IndentStringBuilder appendComment(String string) {
        stringBuilder.append(" * ").append(string);
        return this;
    }

    public IndentStringBuilder appendParam(String paramName, String description) {
        this.appendComment("@param").append(" ").append(paramName).append(" ").append(description);
        return this;
    }

    public IndentStringBuilder appendReturn(String description) {
        this.appendComment("@return").append(" ").append(description);
        return this;
    }

    public IndentStringBuilder closeComment() {
        stringBuilder.append(" */");
        return this;
    }

    @Override
    public IndentStringBuilder append(CharSequence charSequence) {
        stringBuilder.append(charSequence);
        return this;
    }

    public IndentStringBuilder appendType(Type type) {
        stringBuilder.append(type.getTypeName());
        return this;
    }

    public IndentStringBuilder appendClass(Class classType) {
        if (imports.contains(classType)) {
            stringBuilder.append(classType.getSimpleName());
        } else {
            stringBuilder.append(classType.getCanonicalName());
        }
        return this;
    }

    @Override
    public IndentStringBuilder append(char c) {
        this.stringBuilder.append(c);
        return this;
    }

    public IndentStringBuilder append(int integer) {
        stringBuilder.append(integer);
        return this;
    }

    public IndentStringBuilder newlines(int count) {
        return printRepeated("\n", count);
    }

    public IndentStringBuilder printRepeated(CharSequence sequence, Integer repeats) {
        for (int i = 0; i < repeats; i++) {
            stringBuilder.append(sequence);
        }
        return this;
    }


    public IndentStringBuilder indent() {
        return this.indents(atomicInteger.get());
    }

    public IndentStringBuilder indents(Integer indent) {
        return printRepeated(" ", indent);
    }

    public IndentStringBuilder newline() {
        return newlines(1);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public IndentStringBuilder appendImport(Class<?> classMethod) {
        imports.add(classMethod);
        if (!classMethod.getPackage().getName().equals(packageName)) {
            return append("import ").append(classMethod.getCanonicalName()).append(";").newline();
        } else {
            return this;
        }
    }

    public IndentStringBuilder appendImports(Class<?>... classesToImport) {
        Arrays.stream(classesToImport).sorted(Comparator.comparing(Class::getCanonicalName)).forEach(this::appendImport);
        return this;
    }

    public IndentStringBuilder appendPackage(String packageName) {
        this.packageName = packageName;
        return this.append("package ").append(packageName).append(";").newlines(2);
    }
}
