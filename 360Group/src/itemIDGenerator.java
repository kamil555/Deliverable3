public class itemIDGenerator {

	private static int id = 0;
	
	public static int getCurrentID() {
		return id;
	}

	public static int nextID() {
		id = id + 1;
		return id;
	}
}
