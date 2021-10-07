package eu.arrowhead.client.skeleton.provider.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.arrowhead.client.skeleton.database.DTOConverter;
import eu.arrowhead.client.skeleton.database.InMemoryPlugDB;
import eu.arrowhead.client.skeleton.database.PlugRequestDTO;
import eu.arrowhead.client.skeleton.database.PlugResponseDTO;
import eu.arrowhead.client.skeleton.database.Thermo;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.exception.BadPayloadException;
import eu.arrowhead.client.skeleton.provider.configuration.PlugProviderConstants;
@RestController
@RequestMapping(PlugProviderConstants.PLUG_URI)
public class ProviderController {
	
	//=================================================================================================
	// members
	@Autowired
	private InMemoryPlugDB carDB;

	//TODO: add your variables here

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echoService() {
		return "Got it!";
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public List<PlugResponseDTO> getCars(@RequestParam(name = PlugProviderConstants.REQUEST_PARAM_serviceType, required = false) final String serviceType,
													   @RequestParam(name = PlugProviderConstants.REQUEST_PARAM_deviceType, required = false) final String deviceType) {
		final List<PlugResponseDTO> response = new ArrayList<>();
		for (final Thermo car : carDB.getAll()) {
			boolean toAdd = true;
			if (serviceType != null && !serviceType.isBlank() && !car.getDeviceType().equalsIgnoreCase(serviceType)) {
				toAdd = false;
			}
			if (deviceType != null && !deviceType.isBlank() && !car.getServiceType().equalsIgnoreCase(deviceType)) {
				toAdd = false;
			}
			if (toAdd) {
				response.add(DTOConverter.convertCarToCarResponseDTO(car));
			}
		}
		return response;
	}
	
	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = PlugProviderConstants.BY_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public PlugResponseDTO getCarById(@PathVariable(value = PlugProviderConstants.PATH_VARIABLE_ID) final int id) {
		return DTOConverter.convertCarToCarResponseDTO(carDB.getById(id));
	}
	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public PlugResponseDTO createCar(@RequestBody final PlugRequestDTO dto) {
		if (dto.getServiceType() == null || dto.getServiceType().isBlank()) {
			throw new BadPayloadException("serviceType is null or blank");
		}
		if (dto.getDeviceType() == null || dto.getDeviceType().isBlank()) {
			throw new BadPayloadException("deviceType is null or blank");
		}
		final Thermo car = carDB.create(dto.getServiceType(), dto.getDeviceType());
		return DTOConverter.convertCarToCarResponseDTO(car);
	}
	
	//-------------------------------------------------------------------------------------------------
	@PutMapping(path = PlugProviderConstants.BY_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public PlugResponseDTO updateCar(@PathVariable(name = PlugProviderConstants.PATH_VARIABLE_ID) final int id, @RequestBody final PlugRequestDTO dto) {
		if (dto.getServiceType() == null || dto.getServiceType().isBlank()) {
			throw new BadPayloadException("brand is null or blank");
		}
		if (dto.getDeviceType() == null || dto.getDeviceType().isBlank()) {
			throw new BadPayloadException("color is null or blank");
		}
		final Thermo car = carDB.updateById(id, dto.getServiceType(), dto.getDeviceType());
		return DTOConverter.convertCarToCarResponseDTO(car);
	}
	
	
	//-------------------------------------------------------------------------------------------------
	@DeleteMapping(path = PlugProviderConstants.BY_ID_PATH)
	public void removeCarById(@PathVariable(value = PlugProviderConstants.PATH_VARIABLE_ID) final int id) {
		carDB.removeById(id);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TODO: implement here your provider related REST end points
}
