package com.bdpanajoto.ws.rest.resource;

import java.util.Map;

import org.springframework.hateoas.ResourceSupport;

public class ReportResource extends ResourceSupport {
	private final Map<Integer, Integer> report;

	public ReportResource(Map<Integer, Integer> report) {
		this.report = report;
	}

	public Map<Integer, Integer> getReport() {
		return report;
	}
}
