package com.springreact.springBootReact;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MensagemService {

	@Value("${aplication.name}")
	private String appName;
}
