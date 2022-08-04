package engine;

public class Vector2 {
	public float x;
	public float y;
	
	
	//CONSTRUCTORS
	
	public Vector2 (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public Vector2 (Vector2 v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	
	//NON-STATIC
	public void AddTo(Vector2 v) {
		
		this.x += v.x;
		this.y += v.y;
	}
	
	public Vector2 clone() {
		return new Vector2(x, y);
	}
	
	public float length() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vector2 direction() {
		return new Vector2(x/length(), y/length());
	}
	
	public Vector2 neg() {
		return new Vector2(-x, -y);
	}
	
	
	
	//STATIC
	
	public static float Distance(Vector2 v1, Vector2 v2) {
		return Add(v1, v2.neg()).length();
	}
	public static Vector2 Add(Vector2 v1, Vector2 v2) {
		
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}
	
	public static Vector2 Multiply(Vector2 v1, float f) {
		
		return new Vector2(v1.x *f, v1.y *f);
	}
	

	
}
