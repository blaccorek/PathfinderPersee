/**
 * 
 * @author jtand
 *
 */

import java.util.List;

public class Persee {
	
	/**
	 * Objet utilisé par Persée pour s'échapper d'un labyrinthe
	 * 
	 * @see Persee#echappeToi
	 */
	GPS	gps;
	
	/**
	 * Constructeur
	 */
	public Persee()
	{
		this.gps = new GPS();
	}
	
	/**
	 * Renvoi le chemin le plus court pour atteindre la sortie de <b>labyrinthe</b> 
	 * 
	 * @param labyrinthe
	 * 		Le labyrinthe avec la position de Persée en [0][N-1] et la sortie en [N-1][0].<br>
	 * 		Caractéristiques :
	 * 		<ul>
	 * 			<li>true  -> mur</li>
	 * 			<li>false -> chemin</li>
	 * 		</ul>
	 * 
	 * @return Le chemin le plus court vers la sortie, sous la forme d'une liste de Point.
	 * 
	 * @see	Point 
	 */
	public List<Point> echappeToi(boolean[][] labyrinthe) {
		gps.updateMaze(labyrinthe);
		List<Point> chemin = gps.findShortestWay(new Point(0, labyrinthe.length - 1), new Point(labyrinthe.length - 1,0));
		return chemin;
	}
}
