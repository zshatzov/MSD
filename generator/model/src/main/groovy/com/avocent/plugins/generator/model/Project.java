package com.avocent.plugins.generator.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.avocent.plugins.generator.model.common.CustomCodeInformation;
import com.avocent.plugins.generator.model.common.NMMXMLInformation;
import com.avocent.plugins.generator.model.common.OBWIConfiguration;
import com.avocent.plugins.generator.model.common.SNMPConfiguration;
import com.avocent.plugins.generator.model.common.TrapsInformation;
import com.avocent.plugins.generator.model.view.ContainerViewComponent;

/**
 * <p>
 * A domain object that represents a <em>Plugin Generator Project</em>.
 * The name of the project is constraint to be unique. This class embeds
 * a {@link com.mongodb.DBObject} as property named <em>mibTreeObject</em>
 * that represents a SNMP MIB file as a JSON object tree.
 * </p>
 * 
 * 
 * @author zshatzov
 *
 */

@Document(collection="projects")
public class Project implements Serializable, Comparable<Project>{

	private static final long serialVersionUID = 131593334872025218L;

	private static final SimpleDateFormat SDF = 
			new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSSZ");
	
	@Id
	private String id;
	
	@Indexed(unique=true, name="plugin_project_unique_indx", dropDups=false)
	private String name;
	
	private ProjectStatus status;
	
	private ProjectType type;
	
	private Date lastModified;
	
	private final Date createdDate;
	
	private List<ContainerViewComponent> dataPointMapping = new ArrayList<>();
	
	private TrapsInformation trapsMapping;
	
	private OBWIConfiguration obwiMapping;
	
	private SNMPConfiguration snmpMapping;

	private NMMXMLInformation nmmMapping;
	
	private CustomCodeInformation customCode;
	
	private Map<String, MibTree> mibTrees = new HashMap<>();

	public Project(String name) {
		this.name = name;
		this.createdDate = new Date();
	}

	public void addMibTree(String mibFileName, MibTree mibTree) {
		int index = mibFileName.indexOf(".");
		String key = mibFileName;
		if(index > 0){
			//remove file extension from name
			key = mibFileName.substring(0, index);
		}
		if(!mibTrees.containsKey(key)){
			mibTrees.put(key, mibTree);
		}
	}
	
	@Override
	public int compareTo(Project other) {
		return name.compareTo(other.name);
	}

	public boolean deleteMibTree(String mibFileName){
		if(null != mibTrees.remove(mibFileName)){
			return true;
		}else{
			return false;
		}			
	}

	@Override
	public boolean equals(Object other) {
		return Objects.equals(name, ((Project) other).name);
	}

	public List<String> getAllNonePrimaryMibTreeFileNames(){
		return mibTrees.entrySet()
				.stream()
				.filter(entry-> !entry.getValue().isPrimary())				 
				.map(entry-> entry.getKey())
				.collect(Collectors.toList());
				 
	}	
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public CustomCodeInformation getCustomCode() {
		return customCode;
	}

	public List<ContainerViewComponent> getDataPointMapping() {
		return dataPointMapping;
	}

	public String getId() {
		return id;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public Map<String, MibTree> getMibTrees() {
		return mibTrees;
	}

	public String getName() {
		return name;
	}
	
	public NMMXMLInformation getNmmMapping() {
		return nmmMapping;
	}
	
	public OBWIConfiguration getObwiMapping() {
		return obwiMapping;
	}
	
	
	public MibTree getPrimaryMibTree(){
		return mibTrees.values()
				.stream()
				.filter(MibTree::isPrimary)
				.findFirst()
				.get();
	}
	
	public String getPrimaryMibTreeFileName(){
		return mibTrees.entrySet()
				.stream()
				.filter(entry-> entry.getValue().isPrimary())
				.findFirst()
				.get()
				.getKey();
	}
	
	public SNMPConfiguration getSnmpMapping() {
		return snmpMapping;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public TrapsInformation getTrapsMapping() {
		return trapsMapping;
	}

	public ProjectType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		 return Objects.hashCode(name);
	}

	public boolean isNonePrimeryMibTreeExist(){
		return mibTrees.size() > 1;
	}

	public List<String> retrieveAllMibFileNames(){
		return mibTrees.keySet().stream().collect(Collectors.toList());
	}

	public MibTree retrieveMibTreeByFileName(String mibFileName){
		return mibTrees.get( mibFileName );
	}

	public void setCustomCode(CustomCodeInformation customCode) {
		this.customCode = customCode;
	}
	
	public void setDataPointMapping(List<ContainerViewComponent> dataPointMapping) {
		this.dataPointMapping.clear();
		this.dataPointMapping.addAll(dataPointMapping);
	}

	void setId(String id) {
		this.id = id;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	} 
	
	public void setName(String name) {
		this.name = name;
	}

	public void setNmmMapping(NMMXMLInformation nmmMapping) {
		this.nmmMapping = nmmMapping;	
	}
	
	public void setObwiMapping(OBWIConfiguration obwiMapping) {
		this.obwiMapping = obwiMapping;
	}
	
	public void setSnmpMapping(SNMPConfiguration snmpMapping) {
		this.snmpMapping = snmpMapping;
	}
	
	public void setStatus(ProjectStatus status) {
		this.status = status;
	}	
	
	public void setTrapsMapping(TrapsInformation trapsMapping) {
		this.trapsMapping = trapsMapping;
	}

	public void setType(ProjectType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return String.format("%s [name=%s, status=%s, type=%s, created=%s]", 
				getClass().getSimpleName(), name, status, type,
				SDF.format(createdDate));
	} 
}
