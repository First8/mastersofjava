import java.util.Collections;
import java.util.HashSet;
import java.util.Set;




	public  class WayPoint {
		
		private boolean sealed=false;
		
		private final int x,y,z;
		
		private final Set<WayPoint> neighbors = new HashSet<>();

		public WayPoint(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
		/**
		 * Prevents further changes to {@link #neighbors}
		 */
		public void seal(){
			this.sealed=true;
		}

		/**
		 * @return whether or not {@link #neighbors} can be manipulated
		 */
		public boolean isSealed(){
			return sealed;
		}
		
		/**
		 * @return {@code this}' X-coordinate
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return {@code this}' Y-coordinate
		 */
		public int getY() {
			return y;
		}

		/**
		 * @return {@code this}' Z-coordinate
		 */
		public int getZ() {
			return z;
		}

		/**
		 * Returns {@code this}' direct neighbors.
		 * <strong>Note</strong>: by definition we have that no {@code WayPoint} is its own neighbor.
		 * @return {@code this}' direct neighbors.
		 */
		public Set<WayPoint> getNeighbors() {
			return sealed ? Collections.unmodifiableSet(neighbors) : neighbors;
		}
		
		/**
		 * Returns the absolute slope when traveling from {@code this} to {@code wp} in a straight line for different {@code wp}.
		 * @param wp another {@link WayPoint}, such that its X or Y-coordinates are different from {@code this}'s
		 * @return the absolute slope when traveling from {@code this} to {@code wp} in a straight line
		 */
		public double absoluteSlope(WayPoint wp){
			//determine the Euclidean distance in the X/Y plain
			final double plainDistance = Math.sqrt(
					Math.pow( wp.getX()-getX(), 2 ) + 
					Math.pow( wp.getY()-getY(), 2 )
			);
			//determine the Z-distance
			final double zDistance = Math.abs( wp.getZ()-getZ() );
			
			//return the ratio
			return (100*zDistance)/plainDistance;
		}


		
		public String toString(){
			StringBuilder sb = new StringBuilder("WayPoint[");
			final char[] 	vars = {'x','y','z'};
			final double[] 	vals = {x,y,z};
			for(int i=0; i<vars.length; i++){
				sb.append(vars[i]).append('=').append(vals[i]).append(i==vars.length-1 ? ']' : ',');
			}
			return sb.toString();
		}
		
		@Override
		public int hashCode() {
			return toString().hashCode();
		}
		
		
		@Override
		public boolean equals(Object obj) {
			return this==obj;
		}


		
		
	}
