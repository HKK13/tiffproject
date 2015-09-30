import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TiffImage {
	private byte[] tiffData,
		tiffHeader;
	private long tiffOffset;
	public TiffImage(byte[] data){
		tiffData = data;
		tiffHeader =  Arrays.copyOfRange(tiffData, 0,8);
		tiffOffset = getNumber(Arrays.copyOfRange(tiffHeader, 4, 8));
	}
	
	public String getByteOrder(){
		return (new String(Arrays.copyOfRange(tiffHeader, 0, 2)));
	}
	
	public long getTiffVersion(){
		return getNumber(Arrays.copyOfRange(tiffHeader, 2, 4));
	}
	
	public long getIFDOffset(){
		return tiffOffset;
	}
	
	public byte[] getImage(int offset){
		return (tiffHeader = Arrays.copyOfRange(tiffData, offset, (tiffData.length - offset)));
	}
	
	public long getIFD(){
		return getNumber(Arrays.copyOfRange(tiffData, (int)tiffOffset, ((int)tiffOffset+2)));
	}
	
	public ArrayList<Object> getDirectoryEntries(){
		ArrayList<Object> directoryList = new ArrayList<>();
		byte[] allDirectories = Arrays.copyOfRange(tiffData, ((int)tiffOffset+2), ((int)tiffOffset+2) + (12*((int)getIFD())));
		int size = 0;
		for(int i = 0; i < (int)getIFD(); i++){
			directoryList.add(Arrays.copyOfRange(allDirectories, size, size+12));
			size += 12;
		}
		return directoryList;
	}
	
	private StringBuilder getHex(byte[] bytes){
		StringBuilder sb = new StringBuilder();
	    for (byte b : bytes) {
	        sb.append(String.format("%02X", b));
	    }
	    return sb;
	}
	
	public static long getNumber(byte[] bytes){ //First byte is the least significant byte. 
		long value = 0;
		for (int i = 0; i < bytes.length; i++)
		{
		   value += ((long) bytes[i] & 0xffL) << (8 * i);
		}
		return value;
	}
}
