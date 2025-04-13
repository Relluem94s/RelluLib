package de.relluem94.rellulib.vector;

public class Vector4<T extends Number> extends Vector3<T> implements IVector<T> {

    private T w;

    public Vector4(T x, T y, T z, T w){
        super(x,y,z);
        this.w = w;
    }

    @Override
    public T get(){
        return w;
    }

    @Override
    public void set(T w){
        this.w = w;
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
