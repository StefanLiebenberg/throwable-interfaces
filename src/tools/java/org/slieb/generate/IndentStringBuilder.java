package org.slieb.generate;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class IndentStringBuilder implements Appendable {

    private final AtomicInteger atomicInteger;
    private final StringBuilder stringBuilder;

    public IndentStringBuilder() {
        atomicInteger = new AtomicInteger(0);
        stringBuilder = new StringBuilder();
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


    @Override
    public IndentStringBuilder append(CharSequence charSequence) {
        stringBuilder.append(charSequence);
        return this;
    }


    @Override
    public Appendable append(CharSequence charSequence, int start, int end) throws IOException {
        stringBuilder.append(charSequence, start, end);
        return this;
    }


    @Override
    public Appendable append(char c) throws IOException {
        stringBuilder.append(c);
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
}
