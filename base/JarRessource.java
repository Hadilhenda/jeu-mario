package base;

import java.net.URL;

public class JarRessource {
	public JarRessource() {
		
	}
	
	public URL getFile(String filename) {
		return getClass().getResource(filename);
	}
}
