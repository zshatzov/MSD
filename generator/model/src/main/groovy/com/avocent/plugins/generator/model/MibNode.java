package com.avocent.plugins.generator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 	Represents a <em>SNMP MIB</em> file as a tree of object nodes
 *  of type <code>MibNode</code>.
 *  Besides a <em>name</em> and a <em>MIB OID</em> this class also 
 *  carries extra info contained in the class {@link MibDetail}.
 * </p> 
 * 
 * @author zshatzov
 *
 */
public class MibNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4059393851875356837L;
	
	private String oid;
	private final String name;
	private MibDetail mibDetail;	
	
	private MibNode parent;
	private List<MibNode> children = new ArrayList<>();
	
	public MibNode(String name) {
		this.name = name;
		mibDetail = new MibDetail();
	}
	
	public MibNode(String name, MibNode parent) {
		this(name);
		this.parent = parent;	
	} 

	public void addChild(MibNode child){
		children.add(child);
	}
	
	 
	public List<MibNode> getChildren() {
		return children;
	}
	
	public int childrenCount(){
		return children.size();
	}	
	 
	public boolean hasChildren(){
		return childrenCount() > 0;
	}
	
	public MibDetail getMibDetail() {
		return mibDetail;
	}
	
	public boolean mibDetailExists(){
		return mibDetail.detailExists();
	}
	
	public String getName() {
		return name;
	}
	
	public String getOid() {
		return oid;
	}
	 
	public MibNode getParent() {
		return parent;
	}
	
	public boolean hasParent(){
		return  null != parent;
	}

	public void setMibDetail(MibDetail mibDetail) {
		this.mibDetail = mibDetail;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	
	public void setParent(MibNode parent) {
		this.parent = parent;
	}
	 
	
	public MibNode findChildByName(String name){		
		Optional<MibNode> candidate = children.stream()
				.filter(node-> node.name.equals(name))
				.findFirst();
		 		
		return (candidate.isPresent())? candidate.get(): null;
	}

	@Override
	public String toString() {
		return String.format("%s [oid= %s, name= %s]",
			getClass().getSimpleName(), oid, name);
	} 
}
