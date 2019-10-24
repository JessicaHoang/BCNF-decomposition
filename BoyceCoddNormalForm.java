import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BoyceCoddNormalForm {
	
	public static Set<relation> checkBcnf(fd fdList, relation relation, Set<relation> output)
			throws CloneNotSupportedException {

		List<fd> fds = fdList.getList();

		for (int i = 0; i < fds.size(); i++) {
			if (fds.get(i).BCNFviolation(relation)) {
				bcnfDecomposition(relation, fds.get(i), fdList, output);
				break;
			}
		}

		return output;

	}
	
	public static void bcnfDecomposition(relation relation, fd violation, fd fds, Set<relation> relList)
			throws CloneNotSupportedException {

		// System.out.println("Relation : " + relation.toString());
		// System.out.println("Violation : " + violation.toString());
		// System.out.println("All Fds for the current relation:\n" + fds.toString());

		relation x = violation.getLefthand();

		relation xclosure = fds.closure(x);
		relation y = xclosure.difference(x);

		relation xy = x.union(y);
		fd xyFds = project(fds, xy, relation.difference(xy));
		relList.add(xy);

		relation xz = x.union(relation.difference(xclosure));
		fd xzFds = project(fds, xz, relation.difference(xz));
		relList.add(xz);

		relList.remove(relation);

		// System.out.println("Decomposed to " + xy.toString() + " and " + xz.toString()
		// + "\n");
		checkBcnf(xyFds, xy, relList);
		checkBcnf(xzFds, xz, relList);

	}
	
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
