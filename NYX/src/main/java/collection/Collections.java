package collection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Collections {

	List addressList;
	Set addressSet;
	Map addressMap;
	Properties addressProp;
	public List getAddressList() {
		System.out.println("List Elements : " + addressList);
		return addressList;
	}
	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}
	public Set getAddressSet() {
		System.out.println("Set Elements : " + addressSet);
		return addressSet;
	}
	public void setAddressSet(Set addressSet) {
		this.addressSet = addressSet;
	}
	public Map getAddressMap() {
		System.out.println("Map Elements : " + addressMap);
		return addressMap;
	}
	public void setAddressMap(Map addressMap) {
		this.addressMap = addressMap;
	}
	public Properties getAddressProp() {
		return addressProp;
	}
	public void setAddressProp(Properties addressProp) {
		System.out.println("Prop Elements : " + addressProp);
		this.addressProp = addressProp;
	}
	
	
	
	
	
}

