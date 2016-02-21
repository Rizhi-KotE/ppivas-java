package catchers;

public abstract class ReflectionCatcher {

	public static void handleExeption(ReflectiveOperationException e) {
		e.printStackTrace();
	}
}
