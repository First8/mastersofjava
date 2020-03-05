public class Link  {
  private final Node source;
  private final Node destination;
  private final int length; 
  
  public Link(Node source, Node destination, int length) {
    this.source = source;
    this.destination = destination;
    this.length = length;
  }
  
  public Node getDestination() {
    return destination;
  }

  public Node getSource() {
    return source;
  }
  public int getLength() {
    return length;
  }
  
  @Override
  public String toString() {
    return source + " " + destination;
  }
  
  
} 
