package de.relluem94.rellulib.vector;

public class Vector3<T extends Number> extends Vector2<T> implements IVector<T> {

    private T z;

    public Vector3(T x, T y, T z){
        super(x,y);
        this.z = z;
    }

    @Override
    public T get(){
        return z;
    }

    @Override
    public void set(T z){
        this.z = z;
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
        return super.toString() + ", z: " + get();
    }
}
