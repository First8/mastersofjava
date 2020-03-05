import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RouteplannerImpl extends Network {

	// Bekeken nodes in het berekenalgoritme
	private Set<Node> settledNodes;
	// Onbekeken nodes in het berekenalgoritme
	private Set<Node> unSettledNodes;
	// Deze variable slaat voor iedere node de voorlaatste node in het pad vanaf het beginpunt op
	private Map<Node, Node> predecessors;
	// Deze variable heeft van iedere node de afstand vanaf het opgegeven beginpunt
	private Map<Node, Integer> distance;

	public void execute(Node source) {
		settledNodes = new HashSet<Node>();
		unSettledNodes = new HashSet<Node>();
		distance = new HashMap<Node, Integer>();
		predecessors = new HashMap<Node, Node>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Node node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Node node) {
		List<Node> adjacentNodes = getNeighbors(node);
		for (Node target : adjacentNodes) {
			//
			// Implementeer algoritme. Vergeet niet afstanden in de distance-variable
			// te zetten, en ook het predecessors-pad te vullen.
			//
		}
	}

	private int getDistanceBetweenNeighbors(Node node, Node target) {
		for (Link link : getLinks()) {
			//
			// Bereken hier de afstand tussen 2 buren
			//
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		for (Link link : getLinks()) {
			//
			// Haal hier de buren op die nog niet berekend zijn.
			//
		}
		return neighbors;
	}

	private Node getMinimum(Set<Node> nodes) {
		Node minimum = null;
		for (Node node : nodes) {
			if (minimum == null) {
				minimum = node;
			} else {
				if (getShortestDistance(node) < getShortestDistance(minimum)) {
					minimum = node;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Node node) {
		return settledNodes.contains(node);
	}

	public int getShortestDistance(Node destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * Deze methode geeft het pad terug van het begin naar het opgegeven eindpunt (destination).
	 * Als er geen pad is geeft deze NULL terug.
	 */
	public LinkedList<Node> getPath(Node destination) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = destination;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// We hebben nu het pad van het eindpunt naar het beginpunt. Dus nog even omdraaien
		Collections.reverse(path);
		return path;
	}

}
