package properties;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import users.Tenant;

import org.junit.jupiter.api.BeforeEach;
import	org.junit.*;	

class RentalPropertyTest {
	String proID;
	RentalProperty rp;
	Tenant tenant;
	Application app;
	
	@BeforeEach
	public void setup() {
		rp= new RentalProperty("S1", "Coburg", "A big house", "Coburg", 2, 1, 1, "house", 200, "12 months");
		proID= rp.getPropertyID();
		tenant = new Tenant();
		app= new Application(proID,tenant , 2000, "student", 180, "12 months");
	}
	@Test
	
	void testHandleApplicationPending() {

		assertFalse(rp.handleApplication(app));// property status is currently pending, not availalbe
		
	}
	
	@Test 
	
	void testHandleApplicationAvailable() {
		rp.setStatusToAvailable();
		assertTrue(rp.handleApplication(app));// property status is currently  availalbe
		
	}

}
