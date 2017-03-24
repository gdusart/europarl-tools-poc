package be.gdusart.europarltools.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.gdusart.europarltools.model.Environment;
import be.gdusart.europarltools.services.EnvironmentService;

@RestController
@RequestMapping("/environments")
public class EnvironmentsRestController {

	@Autowired
	private EnvironmentService environmentService;

	public EnvironmentsRestController(EnvironmentService environmentService) {
		super();
		this.environmentService = environmentService;
	}

	@RequestMapping("list")
	public Collection<Environment> list() {
		return environmentService.getEnvironments();
	}

}
