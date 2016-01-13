package org.slieb.generate;


public abstract class AbstractAppendable<A extends AbstractAppendable> implements Appendable {

    public A append(Object o) {
        return this.append(String.valueOf(o));
    }

    @Override
    public A append(CharSequence csq) {
        return this.append(csq, 0, csq.length());
    }

    @Override
    public A append(CharSequence csq, int start, int end) {
        for (int i = 0; i < end; i++) {
            append(csq.charAt(i));
        }
        return (A) this;
    }


    @Override
    abstract public A append(char c);



}
