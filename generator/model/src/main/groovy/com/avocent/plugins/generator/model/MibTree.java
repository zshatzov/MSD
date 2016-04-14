package com.avocent.plugins.generator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.DBObject;

/**
 * <p>
 * A model class that captures a MIB tree as a {@link com.mongodb.DBObject}.
 * This class also captures MIB node details for the associated MIB tree. The MIB node
 * details are stored as a {@link java.util.List} of {@link MibDetail} objects. The separation of
 * the MIB tree from the MIB details is done to minimize the MIB tree payload size delivered
 * to the client (Web browser).
 * </p>
 * 
 * @author zshatzov
 *
 */

public class MibTree implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1893483405161193437L;
	
	private boolean primary;
	private DBObject mibTreeObject;
	private List<MibDetail> mibDetails = new ArrayList<>();
	
	MibTree() {
		this(false);
	}
	
	public MibTree(boolean primary) {
		this.primary = primary;
	}
	 
	public MibTree(DBObject mibTreeObject, boolean primary) {
		this(primary);
		this.mibTreeObject = mibTreeObject;
	}
	
	public void addMibDetail(MibDetail detail){
		mibDetails.add(detail);
	}
	
	public MibDetail findMibDetail(String oid){		
		Optional<MibDetail> result = 
				getMibDetails().stream()
							   .filter(detail-> detail.getOid().equals(oid))
							   .findFirst();		
		
		return (result.isPresent())? result.get(): null;
	}
	
	public List<MibDetail> getMibDetails() {
		return mibDetails;
	}
	
	public DBObject getMibTreeObject() {
		return mibTreeObject;
	}
	
	public void setMibTreeObject(DBObject mibTreeObject) {
		this.mibTreeObject = mibTreeObject;
	}

	public boolean isPrimary() {
		return primary;
	}
}
