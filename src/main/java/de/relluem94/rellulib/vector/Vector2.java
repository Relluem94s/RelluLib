package de.relluem94.rellulib.vector;

public class Vector2<T extends Number> extends Vector1<T> implements IVector<T> {

    private T y;

    public Vector2(T x, T y){
        super(x);
        this.y = y;
    }

    @Override
    public T get(){
        return y;
    }

    @Override
    public void set(T y){
        this.y = y;
    }

    /**
     * @return String of Vector in Short Form
     */
    @Override
    public String toShortString() {
        return super.toShortString() + ", " + get();
    }

    /**
     * @return String of Vector
     */
    @Override
    public String toString() {
        return super.toString() + " , y: " + get();
    }
}
