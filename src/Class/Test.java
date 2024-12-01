package Class;

public class Test {
	public static void main(String[] args) {
		String x = "Minh2: Hay quá   Vào lúc 19/23/30 23:39:34";

		// Tìm vị trí của dấu hai chấm đầu tiên
		int firstColonIndex = x.indexOf(":");
		if (firstColonIndex != -1) {
		    // Cắt chuỗi để lấy Name
		    String name = x.substring(0, firstColonIndex).trim();
		    String otherString = x.substring(firstColonIndex + 2);
		    System.out.println(name);
		    System.out.println(otherString);
		    String []split = otherString.split("\\s{3,}");
		    if(split.length > 0) {
		    	String content = split[0];
		    	System.out.println(content);
		    	String other = split[1];
		    	int indexOfdate = other.indexOf("c");
		    	if(indexOfdate !=-1) {
		    		String date = other.substring(indexOfdate + 2);
		    		System.out.println(date);
		    	}
		    }
		}
	}
}
