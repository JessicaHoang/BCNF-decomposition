import javax.management.relation.Relation;

public class fd {
	private Relation l, r;
	
	/*Constructor*/
	public fd(Relation in_lefthand, Relation in_righthand) {
		super();
		this.l = in_lefthand;
		this.r = in_righthand;
	}
	
	public Relation getLefthand() {
		return l;
	}
	
	public Relation getRighthand() {
		return r;
	}
	
	public String toString() {
		return l.toString()+ "->"+r.toString();
	}
	
	public boolean BCNFviolation(Relation s) {
		Relation u = l.union(r);
		if(s.subset(l) && s.intersect(r) != null && !u.subset(s)) {
			return true;
		}
		return false;
	}
}
