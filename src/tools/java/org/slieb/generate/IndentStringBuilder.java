package org.slieb.generate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class IndentStringBuilder extends AbstractAppendable<IndentStringBuilder> {

    private final AtomicInteger atomicInteger;

    private final StringBuilder stringBuilder;

    private final Set<Class<?>> imports;

    private String packageName;

    IndentStringBuilder() {
        atomicInteger = new AtomicInteger(0);
        stringBuilder = new StringBuilder();
        imports = new HashSet<>();
    }

    public IndentStringBuilder incrementIndent() {
        atomicInteger.incrementAndGet();
        atomicInteger.incrementAndGet();
        return this;
    }

    public IndentStringBuilder decrementIndent() {
        atomicInteger.decrementAndGet();
        atomicInteger.decrementAndGet();
        return this;
    }

    void setIndent(Integer indent) {
        atomicInteger.set(indent);
    }

    IndentStringBuilder openComment() {
        stringBuilder.append("/**");
        return this;
    }

    private IndentStringBuilder appendComment(String string) {
        stringBuilder.append(" * ").append(string);
        return this;
    }

    IndentStringBuilder appendParam(String paramName, String description) {
        this.appendComment("@param").append(" ").append(paramName).append(" ").append(description);
        return this;
    }

    IndentStringBuilder appendReturn(String description) {
        this.appendComment("@return").append(" ").append(description);
        return this;
    }

    IndentStringBuilder closeComment() {
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

    IndentStringBuilder appendClass(Class classType) {
        this.stringBuilder.append(this.getClassContent(classType));
        return this;
    }

    @Override
    public IndentStringBuilder append(char c) {
        this.stringBuilder.append(c);
        return this;
    }

    IndentStringBuilder append(int integer) {
        stringBuilder.append(integer);
        return this;
    }

    IndentStringBuilder newlines(int count) {
        return printRepeated("\n", count);
    }

    private IndentStringBuilder printRepeated(CharSequence sequence, Integer repeats) {
        for (int i = 0; i < repeats; i++) {
            stringBuilder.append(sequence);
        }
        return this;
    }

    IndentStringBuilder indent() {
        return this.indents(atomicInteger.get());
    }

    IndentStringBuilder indents(Integer indent) {
        return printRepeated(" ", indent);
    }

    IndentStringBuilder newline() {
        return newlines(1);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    IndentStringBuilder appendImport(Class<?> classMethod) {
        imports.add(classMethod);
        if (!classMethod.getPackage().getName().equals(packageName)) {
            return append("import ").append(classMethod.getCanonicalName()).append(";").newline();
        } else {
            return this;
        }
    }

    IndentStringBuilder appendImports(Class<?>... classesToImport) {
        Arrays.stream(classesToImport).sorted(Comparator.comparing(Class::getCanonicalName)).forEach(this::appendImport);
        return this;
    }

    IndentStringBuilder appendPackage(String packageName) {
        this.packageName = packageName;
        return this.append("package ").append(packageName).append(";").newlines(2);
    }

    String getClassContent(Class classType) {
        return imports.contains(classType) ? classType.getSimpleName() : classType.getCanonicalName();
    }

    IndentStringBuilder annotate(final Class<?> annotationClass) {
        return this.annotate(annotationClass, null);
    }

    IndentStringBuilder annotate(final Class<?> annotationClass, String content) {
        this.append("@").appendClass(annotationClass);

        if (content != null) {
            this.append("(").append(content).append(")");
        }

        return this.newline();
    }


}
