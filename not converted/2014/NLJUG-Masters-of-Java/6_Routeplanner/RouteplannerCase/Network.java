import java.util.ArrayList;
import java.util.List;

public class Network {
  private final List<Node> nodes;
  private final List<Link> links;

  public Network() {
	this.nodes = new ArrayList<Node>();
	this.links = new ArrayList<Link>();
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public List<Link> getLinks() {
    return links;
  }
  
  public boolean addNode(String name) {
	  if (name == null)
		  return false;
	  
	  Node node = new Node(name);
	  if (!nodes.contains(node)) {
		  nodes.add(node);
		  return true;
	  }
	  return false;
  }
 
  public boolean addLink(String source, String destination, int length) {
	  if (length <= 0) {
		  return false;
	  }
	  Node sourceNode = new Node(source);
	  Node destinationNode = new Node(destination);
	  if (!nodes.contains(sourceNode) || !nodes.contains(destinationNode))
		  return false;
	  this.links.add(new Link(sourceNode, destinationNode, length));
	  return true;
  }
  
}