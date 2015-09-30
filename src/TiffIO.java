import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class TiffIO {
	public static byte[] readTiff(Path path) throws IOException{
		return Files.readAllBytes(path);
	}
	
	public static byte[] writeTiff(String path){
		return null;
	}
}
