package eu.arrowhead.client.skeleton.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class DTOConverter {

	
	//=================================================================================================
	// methods
	
	//-------------------------------------------------------------------------------------------------
	public static PlugResponseDTO convertCarToCarResponseDTO(final Thermo car) {
		Assert.notNull(car, "car is null");
		return new PlugResponseDTO(car.getValue(), car.getDeviceType(), car.getServiceType());
	}
	
	//-------------------------------------------------------------------------------------------------
	public static List<PlugResponseDTO> convertCarListToCarResponseDTOList(final List<Thermo> cars) {
		Assert.notNull(cars, "car list is null");
		final List<PlugResponseDTO> carResponse = new ArrayList<>(cars.size());
		for (final Thermo car : cars) {
			carResponse.add(convertCarToCarResponseDTO(car));
		}
		return carResponse;
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	public DTOConverter() {
		throw new UnsupportedOperationException(); 
	}
}
