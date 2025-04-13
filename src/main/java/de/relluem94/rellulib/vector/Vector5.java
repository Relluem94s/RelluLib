package de.relluem94.rellulib.vector;

public class Vector5<T extends Number> extends Vector4<T> implements IVector<T> {

    private T v;

    public Vector5(T x, T y, T z, T w, T v){
        super(x,y,z,w);
        this.v = v;
    }

    @Override
    public T get(){
        return v;
    }

    @Override
    public void set(T v){
        this.v = v;
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
        return super.toString() + ", w: " + get();
    }
}
