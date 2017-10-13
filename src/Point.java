/**
 * 
 * @author jtand
 *
 */

public class Point {
	/**
	 * La valeur en abscisse du point
	 * 
	 * @see	Point#getX()
	 */
	public int x;
	
	/**
	 * La valeur en ordonnée du point
	 * 
	 * @see	Point#getY()
	 */
	public int y;
	
	/**
	 * 
	 */
	public int count;
	
	/**
	 * 
	 */
	public int heuristic;
	
	/**
	 * Constructeur Point.
	 * 
	 * @param x
	 * 		La valeur en abscisse.
	 * @param y
	 * 		La valeur de ordonnée.
	 * 
	 * @see	Point#x
	 * @see	Point#y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.count = 0;
		this.heuristic = 0;
	}
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public boolean equals(Point other) {
		return (other.x == this.x && other.y == this.y);
	}
	
	/**
	 * Retourne une chaine de charactères représentant le Point
	 */
	public String toString() {
		return new String("x=" +  this.x +" y=" + this.y);
	}
	
	/**
	 * Retourne la distance entre le Point et <b>other</b>
	 * 
	 * @param other
	 * @return La distance entre le Point et other, sous la forme d'un entier
	 */
	public int getDistance(Point other) {
		return (int) Math.sqrt(Math.pow((this.x - other.x), 2)+Math.pow((this.y - other.y), 2));
	}
}
