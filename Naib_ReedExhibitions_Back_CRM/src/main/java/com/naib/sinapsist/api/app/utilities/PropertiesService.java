package com.naib.sinapsist.api.app.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naib.sinapsist.api.app.models.entity.ConfigMail;

@Service
public class PropertiesService {

	@Autowired
	private ConfigProperties propertiesService;

	public void setPropertiesMail(ConfigMail cm) {
		if (cm != null) {
			propertiesService.setMail(cm.getUsername());
			propertiesService.setPassword(cm.getPassword());
			propertiesService.setHost(cm.getHost());
			propertiesService.setPort(cm.getPort());
		}
	}

	public void resetPropertiesMail() {
		propertiesService.setMail("info.ticmap@sinapsist.net");
		propertiesService.setPassword(".1Na1B#g5oUp&?S1nA9s1sT");
		propertiesService.setHost("smtp.ionos.mx");
		propertiesService.setPort("587");
	}

}
