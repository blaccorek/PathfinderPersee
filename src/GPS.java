import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author jtand
 *
 */

public class GPS {
	/**
	 * Position projetée actuelle
	 */
	private Point	CurrentPosition;
	
	/**
	 * Liste des prochains points à parcourir
	 */
	private List<Point>	OpenList;
	
	/**
	 * Liste des points parcourues
	 */
	private List<Point>	ClosedList;
	
	/**
	 * Représente le labyrinthe
	 * 	Caractéristiques :
	 * <ul>
	 *	<li>true  -> mur</li>
	 * 	<li>false -> chemin</li>
	 * </ul>
	 */
	private boolean[][] Maze;
	
	/**
	 * Constructeur
	 */
	public GPS() {
		this.OpenList = new LinkedList<Point>();
		this.ClosedList = new ArrayList<Point>();
	}
	
	/**
	 * Change le membre <i>Maze</i> par <b>other<b>
	 * 
	 * @see GPS#Maze
	 * @param other
	 */
	public void updateMaze(boolean[][] other) {
		this.Maze = other;
	}
	
	/**
	 * Retourne la case située à <b>direction</b> par rapport à la position actuelle.<br>
	 * Les valeurs de <b>direction</b> sont :
	 * <ul>
	 * <li>0 -> droite</li>
	 * <li>1 -> gauche</li>
	 * <li>2 -> bas</li>
	 * <li>3 -> haut</li>
	 * </ul>
	 * 
	 * @param direction
	 * 
	 * @return Le case voulue, sous la forme d'un Point.
	 * *
	 * @see Point
	 */
	private Point getNeighbour(int direction) {
		int deltaX, deltaY;
		if ((direction & 2) == 2) {
			deltaX = 0;
			deltaY = ((direction & 1) == 1 ? -1 : 1);
		} else {
			deltaX = ((direction & 1) == 1 ? -1 : 1);
			deltaY = 0;
		}
		Point nPoint = new Point(CurrentPosition.x + deltaX, CurrentPosition.y + deltaY);
		if ( nPoint.y >= 0 && nPoint.y < Maze.length
				&& nPoint.x >= 0 && nPoint.x < Maze.length) {
			// Recherche si le nouveau Point n'a pas déjà été répertorié dans la liste des Points à Parcourir
			for(Iterator<Point> i = OpenList.iterator(); i.hasNext();) {
				Point tmp = i.next();
				if (tmp.equals(nPoint)){
					nPoint = null;
					return tmp;
				}
			}
			// --f
			// Recherche si le nouveau Point n'a pas déjà été vérifié
			for(Iterator<Point> i = ClosedList.iterator(); i.hasNext();) {
				Point tmp = i.next();
				if (tmp.equals(nPoint)){
					nPoint = null;
					return tmp;
				}
			}
			// --f
		}
		else {
			nPoint = null;
		}
		return nPoint;
	}
	
	/**
	 * Retourne le chemin le plus court vers la sortie de <b>labyrinthe</b>.<br>
	 * <br>
	 * Soit N la taille du labyrinthe.<br>
	 * Position de la sortie : [0][N -1]<br>
	 * Position de départ : [N -1][0]
	 * 
	 * @param labyrinthe
	 * 
	 * @return Le chemin le plus court vers la sortie du labyrinthe, sous la forme d'une liste de Points.
	 * 
	 * @see Point
	 * @see GPS#rebuildWay()
	 * @see GPS#OpenList
	 * @see GPS#ClosedList
	 */
	public List<Point> findShortestWay(Point start, Point goal){
		Point neighbour = null;
		List<Point> neighboursList;
		OpenList.add(start);
		while (OpenList.size() > 0)
		{
			CurrentPosition = OpenList.get(OpenList.size() -1);
			OpenList.remove(OpenList.size() -1);
			if (CurrentPosition.equals(goal)){
				ClosedList.add(CurrentPosition);
				return rebuildWay();
			}
			neighboursList = new ArrayList<Point>();
			// Parcourt les 4 directions autour de CurrentPosition pour trouver les voisins
			for (int i = 0; i < 4; i++) {					
				neighbour = getNeighbour(i);				
				if (neighbour != null) {
					neighboursList.add(neighbour);
				}
			}
			// --f
			while(neighboursList.size() > 0) {
				if(neighbour == null) {
					neighbour = neighboursList.get(neighboursList.size() - 1);
				}
				if (neighbour.heuristic >= CurrentPosition.heuristic || (neighbour.count == 0 && neighbour.heuristic == 0)) {
					neighbour.count = CurrentPosition.count + 1;
					neighbour.heuristic = neighbour.count + neighbour.getDistance(goal);
					OpenList.add(neighbour);
				}
				ClosedList.add(CurrentPosition);
				neighboursList.remove(neighboursList.size() - 1);
				neighbour = null;
			}
		}
		return null;
	}

	/**
	 * Reconstruit le chemin le plus court pour sortir du labyrinthe
	 * 
	 * @return Le chemin le plus court pour sortir du labyrinthe, sous la forme d'une liste de Points
	 * @see Point
	 */
	private List<Point> rebuildWay() {
			List<Point> way = new ArrayList<Point>();
			way.add(ClosedList.get(ClosedList.size() -1));
			for (int i = ClosedList.size() - 2; i >= 0; i--) {
				if (ClosedList.get(i).getDistance(way.get(0)) == 1) {
					way.add(0, ClosedList.get(i));
				}
			}
			return way;
	}
}
