import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoyceCoddNormalForm {
	private List<fd> scanFD(String txtFile) throws FileNotFoundException {
		List<fd> fds = new ArrayList<>();
		try (Scanner fs = new Scanner(new File(txtFile))) {
			while (fs.hasNext()) {
				String l = fs.nextLine().trim();
				fds.add(new fd(l));
			}
		return fds;	
		}
		
	public static void main(String[] args) {
			
		}
		
		
	}
}
