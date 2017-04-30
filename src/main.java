import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class main {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Nimsys testSys = new Nimsys();
		
		
		//Method m1 = Nimsys.class.getMethod("addplayer", String[].class);
		//m1.invoke(testSys);
		testSys.run();
		
		
	}
	
	
	
}
