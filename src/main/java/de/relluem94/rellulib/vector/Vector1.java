package de.relluem94.rellulib.vector;

public class Vector1<T extends Number> implements IVector<T> {

    private T x;

    public Vector1(T x){
        this.x = x;
    }

    @Override
    public T get(){
        return x;
    }

    @Override
    public void set(T x){
        this.x = x;
    }

    /**
     * @return String of Vector in Short Form
     */
    @Override
    public String toShortString() {
        return get() + "";
    }



    /**
     * @return String of Vector
     */
    @Override
    public String toString() {
        return "x: " + get();
    }
}
