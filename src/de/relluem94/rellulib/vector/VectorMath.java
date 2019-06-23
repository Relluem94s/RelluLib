package de.relluem94.rellulib.vector;

public final class VectorMath{

	private VectorMath(){}

	public final static Vector3f add(Vector3f v1, Vector3f v2)	{
		return (new Vector3f(v1.x+ v2.x, v1.y + v2.y, v1.z + v2.z));
	}

	
	public final static Vector4f add(Vector4f v1, Vector4f v2)	{
		return (new Vector4f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z, v1.w + v2.w));
	}

    public final static Vector3f add(Vector3f v1, float x, float y, float z){    
        return (new Vector3f(v1.x + x, v1.y + y, v1.z + z  ));
    }
	
	public final static Vector4f add(Vector4f v1, float x, float y, float z, float w){
        	return (new Vector4f(v1.x + x, v1.y + y, v1.z + z, v1.w + w));
    }

	public final static Vector3f subtract(Vector3f v1, Vector3f v2){
		return (new Vector3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z));
	}

    public final static Vector3f subtract(Vector3f v1, float x, float y, float z){
        return (new Vector3f(v1.x - x, v1.y - y, v1.z - z  ));
    }

	public final static Vector4f subtract(Vector4f v1, Vector4f v2){
		return (new Vector4f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z, v1.w - v2.w));
	}

    public final static Vector4f subtract(Vector4f v1, float x, float y, float z, float w){
        return (new Vector4f(v1.x - x, v1.y - y, v1.z - z, v1.w - w));
    }
	
	public final static Vector3f multiply(Vector3f v1, float m){
		return (new Vector3f(v1.x * m, v1.y * m, v1.z * m));
	}

	public final static Vector4f multiply(Vector4f v1, float m){
		return (new Vector4f(v1.x * m, v1.y * m, v1.z * m, v1.w * m));
	}
	
	public final static Vector3f divide(Vector3f v1, float m){
		return (new Vector3f(v1.x / m, v1.y / m, v1.z / m));
	}

	public final static Vector4f divide(Vector4f v1, float m){
		return (new Vector4f(v1.x / m, v1.y / m, v1.z / m, v1.w / m));
	}
	
	public final static Vector3f cross_product(Vector3f v1, Vector3f v2){
		Vector3f v3 = new Vector3f();
		v3.x = (v1.y * v2.z) - (v1.z * v2.y);
		v3.y = (v1.z * v2.x) - (v1.x * v2.z);
		v3.z = (v1.x * v2.y) - (v1.y * v2.x);
		return v3;
	}

	public final static Vector3f normal(Vector3f triangle[]){	
		Vector3f vector1 = subtract(triangle[2], triangle[0]);
		Vector3f vector2 = subtract(triangle[1], triangle[0]);
		Vector3f normal = cross_product(vector1, vector2);		

		normalize(normal);					
		return normal;		
	}
	
	public final static float getDotProduct(Vector3f v1, Vector3f v2){
        return (v1.x*v2.x + v1.y*v2.y + v1.z*v2.z);
    }

	public final static float getDotProduct(Vector4f v1, Vector4f v2) {
        return (v1.x*v2.x + v1.y*v2.y + v1.z*v2.z + v1.w*v2.w);
    }

	public final static double angleBetweenVectors(Vector3f Vector1, Vector3f Vector2){							
		float dotProduct = getDotProduct(Vector1, Vector2);				
		float vectorsMagnitude = magnitude(Vector1) * magnitude(Vector2) ;
		double angle = Math.acos( dotProduct / vectorsMagnitude );
		if(angle == Float.NaN)
			return 0;
		return( angle );
	}

    public final static Vector4f cross_product(Vector4f v1, Vector4f v2, Vector4f v3) {
        Vector4f v4 = new Vector4f();
                
       float x = (v1.y * v2.z * v3.w) + (v1.z * v2.w * v3.y) + (v1.w * v2.y * v3.z) - 
           	 (v1.y * v2.w * v3.z) - (v1.z * v2.y * v3.w) - (v1.w * v2.z * v3.y);

       float y = (v1.x * v2.w * v3.z) + (v1.z * v2.x * v3.w) + (v1.w * v2.z * v3.x) -
   	   	 (v1.x * v2.z * v3.w) - (v1.z * v2.w * v3.x) - (v1.w * v2.x * v3.z);

       float z = (v1.x * v2.y * v3.w) + (v1.y * v2.w * v3.x) + (v1.w * v2.x * v3.y) -
                 (v1.x * v2.w * v3.y) - (v1.y * v2.x * v3.w) - (v1.w * v2.y * v3.x);

       float w = (v1.x * v2.z * v3.y) + (v1.y * v2.x * v3.z) + (v1.z * v2.y * v3.x) -
     		 (v1.x * v2.y * v3.z) - (v1.y * v2.z * v3.x) - (v1.z * v2.x * v3.y);
        
        v4.setTo(x, y, z, w);
        return v4;
        
    }
    
    public final static float distance(Vector3f v1, Vector3f v2){
    	Vector3f res = VectorMath.subtract(v2, v1);
    	float distance = VectorMath.magnitude(res);
    	return distance;
    }

	public final static float magnitude(Vector3f v1){
		float r = (float)Math.sqrt( (v1.x * v1.x) + (v1.y * v1.y) + (v1.z * v1.z) );
		if (r <= 0.00001)
			return 0f;
		else
			return r;
	}

	public final static float magnitude(Vector4f v1){
		return (float)Math.sqrt( (v1.x * v1.x) + (v1.y * v1.y) +
				    (v1.z * v1.z) + (v1.w * v1.w));
	}
	
	public final static void normalize(Vector3f v1){
		float f = magnitude(v1);

		v1.x /= f;
		v1.y /= f;
		v1.z /= f;
	}
	
	public final static void normalize(Vector4f v1){
		float f = magnitude(v1);

		v1.x /= f;
		v1.y /= f;
		v1.z /= f;
		v1.w /= f;
	}

    public final static boolean equals(Vector3f v1, float x, float y, float z) {
        return (v1.x == x && v1.y == y && v1.z == z);
    }
    
    public final static boolean equals(Vector3f v1, Vector3f v2) {
        return (v1.x == v2.x && v1.y == v2.y && v1.z == v2.z);
    }

    public final static boolean equals(Vector4f v1, Vector4f v2) {
        return (v1.x == v2.x && v1.y == v2.y && v1.z == v2.z && v1.w == v2.w);
    }

    public boolean equals(Vector4f v1, float x, float y, float z, float w) {
        return (v1.x == x && v1.y == y && v1.z == z && v1.w == w);
    }
}