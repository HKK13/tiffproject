import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class main {

	public static void main(String[] args) {
		try{
		    byte[] imageData = TiffIO.readTiff(Paths.get("src/surf.tiff"));
		    TiffProcess process = new TiffProcess(imageData);
		    byte[] header = process.getHeader();
		    String asd = new String(Arrays.copyOfRange(header, 0, 2));
		    System.out.println(asd);
		    byte[] dsa = Arrays.copyOfRange(header, 4, 8);
		    for(int i = 0; i < 4; i++){
		    	System.out.println(dsa[i]);
		    }
		    StringBuilder sb = new StringBuilder();
		    for (byte b : dsa) {
		        sb.append(String.format("%02X", b));
		    }
		    System.out.println(sb);
		    RandomAccessFile raf = new RandomAccessFile("src/surf.tiff", "r");
		    
		    //long val = Long.parseLong("0x" + sb);
		    raf.seek(0x0600);
		    int value = raf.read();
		    System.out.println(value);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
