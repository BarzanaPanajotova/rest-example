package com.bdpanajoto.ws.rest.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public abstract class ControllerTest<DomainType, ResourceType> {
	private AbstractController<DomainType, ResourceType> controller;

	protected void setController(AbstractController<DomainType, ResourceType> controller) {
		this.controller = controller;
	}

	@Test
	public void findAllTest() {
		Assert.assertNotNull(controller.findAll());
		Assert.assertNotNull(controller.findAll().getBody());
		Assert.assertTrue(controller.findAll().getBody().isEmpty());
		addMockedElementToRepo();
		Assert.assertFalse(controller.findAll().getBody().isEmpty());
		Assert.assertTrue(controller.findAll().getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void createTest() {
		DomainType element = getMockedDomainType();
		Assert.assertNotNull(controller.create(element));
		addMockedElementToRepo();
		Assert.assertNotNull(controller.create(element).getBody());
	}

	protected abstract DomainType getMockedDomainType();

	protected abstract void addMockedElementToRepo();
}
