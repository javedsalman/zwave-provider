package eu.arrowhead.client.skeleton.database;

public class Thermo {

	//=================================================================================================
	// members

	private final double value;
	private String serviceType;
	private String deviceType;


	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public Thermo(final double valueCounter, final String serviceType, final String deviceType) {
		this.value = valueCounter;
		this.serviceType = serviceType;
		this.deviceType = deviceType;
	}

	//-------------------------------------------------------------------------------------------------
	public double getValue() { return value; }

	public String getServiceType() { return serviceType; }

	public String getDeviceType() { return deviceType; }

	//-------------------------------------------------------------------------------------------------
	public void setServiceType(final String serviceType) { this.serviceType = serviceType; }
	public void setDeviceType(final String deviceType) { this.deviceType = deviceType; }
}
