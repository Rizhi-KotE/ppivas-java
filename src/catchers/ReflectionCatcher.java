package catchers;

public abstract class ReflectionCatcher {

	public static void handleExeption(ReflectiveOperationException e) {
		e.printStackTrace();
	}
	
	public static void classNotFound(ReflectiveOperationException e){
		System.err.println("Класс не обнаружен");
	}
}
