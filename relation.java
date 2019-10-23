
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class relation {
	
	private  String[] attr = new String[2];
	private static String[] bttr = new String[2];
	private static String in_r,in_r2;
	
	public relation() {
		
	}
	
	/*Constructor*/
	public relation(String in_r) {
		
		/*Splits string into individual elements*/
		String[] s = in_r.split(",");
		
		/*puts all attributes into attr[] array*/
		for(int i = 0; i<s.length; i++) {
			attr[i] = s[i]; 
		}
	}

/*Puts all elements from array into a hashset*/
private static Set<Character> getAttributeSet(String[] arr) {
	if(arr == null) {
		return null;
	}
	Set<Character> attrSet = new HashSet<>();
	for(int i = 0; i<arr.length; i++) {
		if(arr[i]!=null){
			attrSet.add((char)i);
		}
	}
	return attrSet;
}
/*returns whether the relations are a subset of each other.*/
public boolean subset(relation r2) {
	if(getAttributeSet(this.attr).containsAll(getAttributeSet(r2.attr))) {
		return true;
	}
	return false;
}

/*returns union*/
public relation union(relation r2) {
	if(r2 == null) {
		return this;
	}
	Set<Character> currentSet = getAttributeSet(attr);
	Set<Character> otherSet = getAttributeSet(r2.attr);
	currentSet.addAll(otherSet);
	relation newRelation = new relation();
	List<Character> list = new ArrayList<>(currentSet);
	if(!list.isEmpty()) {
		for(int i = 0; i<list.size(); i++) {
			newRelation.attr[list.get(i)];
		}
		return newRelation;
	}
	return null;
}


/*TEST*/
	public static void main(String[] args) {
		in_r = "1,2";
		String[] s = in_r.split(","); //separates string elements by ,
		for(int i = 0; i<s.length; i++) {
			attr[i] = s[i]; //puts all attributes into attr[] array
//			System.out.println(Arrays.toString(attr));
		}
		
		in_r2 = "1,3";
		String[] s2 = in_r2.split(",");
		for(int i = 0; i<s2.length; i++) {
			bttr[i] = s[i];
		}
		
		System.out.println(Arrays.toString(attr));
		
		System.out.println("Hashset that contains what's in the array: ");
		System.out.print(getAttributeSet(attr));
		
		//relation r2 = r2(in_r2);
		System.out.print(r2);
		
	}
}
