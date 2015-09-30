import java.util.Arrays;


public class TiffProcess {
	byte[] tiffData,
		tiffHeader,
		tiffImage;
	public TiffProcess(byte[] data){
		tiffData = data;
	}
	
	public byte[] getHeader(){
		return (tiffHeader =  Arrays.copyOfRange(tiffData, 0,8));
	}
	
	public byte[] getImage(int offset){
		return (tiffHeader = Arrays.copyOfRange(tiffData, offset, (tiffData.length - offset)));
	}
}
