import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.relation.Relation;
import javax.swing.text.html.HTML.Attribute;

public class fd {
	
	private relation lhs,rhs;

	
	/*Constructor*/
	public fd(relation in_lhs, relation in_rhs) {
		super();
		this.lhs = in_lhs;
		this.rhs = in_rhs;
		
	}
	
	public relation getRighthand() {
		return rhs;
	}
	
	public relation getLefthand() {
		return lhs;
	}
	
	public String toString() {
		return lhs.toString()+ "->"+rhs.toString();
	}
	
	public boolean BCNFviolation(relation s) {
		relation u = lhs.union(rhs);
		if(s.subset(lhs) && s.intersect(rhs) != null && !u.subset(s)) {
			return true;
		}
		return false;
	}
	
	
	/*inner class*/
	class fdList{
		private List<fd> fdList;
		private Iterator<fd> it;
		
		/*inner class constructor*/
		public fdList() {
			fdList = new ArrayList<>();
		}
		
		public void insert(fd FD) {
			fdList.add(FD);
			it = fdList.iterator();
		}
		
		public fd getFirst() {
			if (!fdList.isEmpty() || fdList != null) {
				return fdList.get(0);
			}
			return null;
		}
		
		public fd getNext() {
			if (it.hasNext()) {
				return it.next();
			}
			return null;
		}

		/*Closure*/

		public relation closure(relation r) throws CloneNotSupportedException {

			relation closure = (relation) r.clone();
			relation prevClosure = null;
			while (true) {
				for (fd fd : fdList) {
					if (closure.subset(fd.getLefthand())) {
						closure = closure.union(fd.getRighthand());
					}
				}
				if (closure.equals(prevClosure)) {
					return closure;
				}
				prevClosure = closure;
			}
		}
		public boolean hasNext() {
			return it.hasNext();
		}

		public Iterator<fd> getIterator() {
			return fdList.iterator();
		}

		public List<fd> getList() {
			return fdList;
		}
		public String toString() {
			StringBuilder output = new StringBuilder();
			for (fd rel : fdList) {
				output.append(rel.toString() + "\n");
			}
			return output.toString();
		}

		public void resetIterator() {
			it = fdList.iterator();
		}
		
	}
}

