package eu.arrowhead.client.skeleton.database;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


import eu.arrowhead.client.skeleton.provider.PlugSwitcher;
import org.springframework.stereotype.Component;

import eu.arrowhead.client.skeleton.database.Thermo;
import eu.arrowhead.common.exception.InvalidParameterException;

@Component
public class InMemoryPlugDB extends ConcurrentHashMap<Double, Thermo> {

	//=================================================================================================
	// members
	
	//private static final long serialVersionUID = -2462387539362748691L;
	
	private double valueCounter =0.00;
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public Thermo create(final String switchPlugState, final String deviceType) {
		if (switchPlugState == null || switchPlugState.isBlank()) {
			throw new InvalidParameterException("serviceType is null or empty");
		}		
		if (deviceType == null || deviceType.isBlank()) {
			throw new InvalidParameterException("deviceType is null or empty");
		}
		
		valueCounter= Double.parseDouble(switchPlugState);
		System.out.println("!!!!!valuecounter" + valueCounter);
		//System.out.println("(PlugEnergyReader.readEnergyKWH() : " + (PlugEnergyReader.readEnergyKWH()));
		System.out.println("!!!!!switchPlugState" + switchPlugState);
		PlugSwitcher.switchPlug (Double.parseDouble(switchPlugState));
		String switchPlugState1 = "switch-plug-state";

		this.put(valueCounter, new Thermo(valueCounter, deviceType.trim(), switchPlugState1.trim()));
		return this.get(valueCounter);
	}
	
	//-------------------------------------------------------------------------------------------------
	public List<Thermo> getAll() {
		return List.copyOf(this.values());
	}
	
	//-------------------------------------------------------------------------------------------------
	public Thermo getById(final int id) {
		if (this.containsKey(id)) {
			return this.get(id);
		} else {
			throw new InvalidParameterException("id '" + id + "' not exists");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	public Thermo updateById(final double id, final String brand, final String color) {
		if (this.containsKey(id)) {
			final Thermo car = this.get(id);
			if (brand!= null && !brand.isBlank()) {
				car.setDeviceType(brand);
			}
			if (color != null && !color.isBlank()) {
				car.setServiceType(color);
			}
			this.put(id, car);
			return car;
		} else {
			throw new InvalidParameterException("id '" + id + "' not exists");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	public void removeById(final int id) {
		if (this.containsKey(id)) {
			this.remove(id);
		}
	}
}
