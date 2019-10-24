
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.relation.Relation;

public class relation implements Cloneable{
	
	private String line;
	Set<Integer> lhs,rhs, attributes;
	
	private static int[] attr = new int[2];
	private static String[] bttr = new String[2];
	private static String in_r,in_r2;
	
	public relation() {
		String[] s = line.split(";");
		lhs = splitFD(s[0]);
		rhs = splitFD(s[1]);
	}
	
	/*Constructor*/
	public relation(Set<Integer> lhs, Set<Integer> attributes, Integer rAttr) {
		this.lhs = new HashSet<>(lhs);
		this.rhs = new HashSet<>();
		this.rhs.add(rAttr);
		this.attributes = new HashSet<>(attributes);
		}


private Set<Integer> splitFD(String attr){
	Set<Integer> r = new HashSet<>();
	String[] attribute = attr.split(",");
	for(String a: attribute) {
		r.add(Integer.parseInt(a));
	}
	return r;
	
}

public boolean isTrivial() {
	return lhs.containsAll(rhs);
}


/*returns whether the relations are a subset of each other.*/
public boolean subset(relation r2) {
	if(lhs.containsAll(r2.lhs) && rhs.containsAll(r2.rhs)) {
		return true;
	}
	return false;
}

/*returns relation of union*/
public relation union(relation r2) {
	if(r2 == null) {
		return this;
	}
	Set<Integer> currentSet = attributes;
	Set<Integer> otherSet = r2.attributes;
	currentSet.addAll(otherSet);
	relation newRelation = new relation();
	List<Integer> list = new ArrayList<>(currentSet);
	if(!list.isEmpty()) {
		for(int i = 0; i<list.size(); i++) {
			newRelation.attr[list.get(i)] = 1;
		}
		return newRelation;
	}
	return null;
}

/*intersect method*/
public relation intersect(relation r2) {
	if(r2 == null) {
		return null;
	}
	Set<Integer> currentSet = attributes;
	Set<Integer> otherSet = r2.attributes;
	currentSet.retainAll(otherSet);
	relation newRelation = new relation();
	List<Integer> list = new ArrayList<>(currentSet);
	if(!list.isEmpty()) {
		for(int i = 0; i<list.size(); i++) {
			newRelation.attr[list.get(i)] = 1;
		}
		return newRelation;
	}
	return null;
}

/*Difference*/
public relation difference(relation r2) {
	if(r2 == null) {
		return null;
	}
	Set<Integer> currentSet = attributes;
	Set<Integer> otherSet = r2.attributes;
	currentSet.removeAll(otherSet);
	relation newRelation = new relation();
	List<Integer> list = new ArrayList<>(currentSet);
	if(!list.isEmpty()) {
		for(int i = 0; i<list.size(); i++) {
			newRelation.attr[list.get(i)] = 1;
		}
		return newRelation;
	}
	return null;
}

@Override
protected Object clone() throws CloneNotSupportedException {

    return super.clone();
}

class RelList {
	private List<Relation> relList;

	private Iterator<Relation> it;

	public RelList() {
		relList = new ArrayList<>();
	}

	public void insert(Relation r) {
		relList.add(r);
	}
	
	public Relation getFirst() {
		if (!relList.isEmpty() || relList != null) {
			return relList.get(0);
		}
		return null;
	}

	public Relation getNext() {
		it = relList.iterator();
		if (it.hasNext()) {
			return it.next();
		}
		return null;
	}

	public void resetIterator() {
		it = relList.iterator();
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public Iterator<Relation> getIterator() {
		return relList.iterator();
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		for (Relation rel : relList) {
			output.append(rel.toString() + "\n");
		}
		return output.toString();
	}

	public void remove(Relation t) {
		relList.remove(t);
	}

	public List<Relation> getList() {
		return relList;
	}
	
} // end of RelList class


} // end of relation class
