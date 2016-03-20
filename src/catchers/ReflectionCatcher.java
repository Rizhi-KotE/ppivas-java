package catchers;

import java.lang.reflect.InvocationTargetException;

public abstract class ReflectionCatcher {

	public static void handleExeption(ReflectiveOperationException e) {
		e.printStackTrace();
	}

	public static void classNotFound(ReflectiveOperationException e) {
		String message = e.getMessage();
		System.err.println(message);
	}

	public static void instantiationException(InstantiationException e) {
		System.err.println(e.getMessage());
	}

	public static void illegalAccessException(IllegalAccessException e) {
		System.err.println(e.getMessage());
	}

	public static void illegalArgumentException(IllegalArgumentException e) {
		System.err.println(e.getMessage());
	}

	public static void invocationTargetException(InvocationTargetException e) {
		System.err.println(e.getMessage());
	}

	public static void noSuchMethodException(NoSuchMethodException e) {
		System.err.println(e.getMessage());
	}

	public static void securityException(SecurityException e) {
		System.err.println(e.getMessage());
	}
}
