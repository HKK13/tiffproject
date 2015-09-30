import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class main {

	public static void main(String[] args) {
		try{
		    byte[] imageData = TiffIO.readTiff(Paths.get("src/asdasdasd.tiff"));
		    TiffImage image = new TiffImage(imageData);
		    System.out.println(image.getByteOrder() + " " + image.getTiffVersion() + " " + image.getIFDOffset());
		    //RandomAccessFile randFile = new RandomAccessFile("src/surf.tiff", "r");
		    //randFile.seek(image.getIFDOffset());
		    System.out.println(image.getIFD());
		    ArrayList<Object> list = image.getDirectoryEntries();
		    for(int i = 0; i < list.size(); i++){
		    	byte[] entry = (byte[]) list.get(i);
			    System.out.println(TiffImage.getNumber(Arrays.copyOfRange(entry, 0, 2)) + "\t" + 
			    		TiffImage.getNumber(Arrays.copyOfRange(entry, 2, 4)) + "\t" + 
			    		TiffImage.getNumber(Arrays.copyOfRange(entry, 4, 8)) + "\t" + 
			    		TiffImage.getNumber(Arrays.copyOfRange(entry, 8, 12)) + "\t" + (i+1));
		    }
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
