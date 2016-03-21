package catchers;

import java.lang.reflect.InvocationTargetException;

public abstract class ReflectionCatcher {

	public static void handleExeption(ReflectiveOperationException e) {
		e.printStackTrace();
	}

	public static void classNotFound(ReflectiveOperationException e) {
		String message = e.getClass().getName() + " " + e.getMessage();
		System.err.println(message);
		e.printStackTrace();
	}

	public static void instantiationException(InstantiationException e) {
		String message = e.getClass().getName() + " " + e.getMessage();
		System.err.println(message);
		e.printStackTrace();
	}

	public static void illegalAccessException(IllegalAccessException e) {
		String message = e.getClass().getName() + " " + e.getMessage();
		System.err.println(message);
		e.printStackTrace();
	}

	public static void illegalArgumentException(IllegalArgumentException e) {
		String message = e.getClass().getName() + " " + e.getMessage();
		System.err.println(message);
		e.printStackTrace();
	}

	public static void invocationTargetException(InvocationTargetException e) {
		Throwable cause = e.getCause();
		String message = cause.getClass().getName() + " " + cause.getMessage();
		System.err.println(message);
		cause.printStackTrace();
	}

	public static void noSuchMethodException(NoSuchMethodException e) {
		String message = e.getClass().getName() + " " + e.getMessage();
		System.err.println(message);
		e.printStackTrace();
	}

	public static void securityException(SecurityException e) {
		String message = e.getClass().getName() + " " + e.getMessage();
		System.err.println(message);
		e.printStackTrace();
	}
}
