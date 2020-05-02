package startUp;
import properties.*;
import users.*;
import SystemExceptions.*;
import Utilities.*;
import payment.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApplicationTest {
	String proID;
	RentalProperty rp;
	Tenant tenant;
	Application app;
	Application app2;
	RealEstate re;

	@BeforeEach
	public void setup() {
		re= new RealEstate();
		rp= new RentalProperty("S1", "Coburg", "A big house", "Coburg", 2, 1, 1, "house", 200, "12 months");
		proID= rp.getPropertyID();
		tenant = new Tenant();
		app= new Application(proID,tenant , 2000, "student", 180, "12 months");
		app2= new Application(proID,tenant , 8000, "student", 200, "12 months");
	}
	
	//positive 
	@Test 
	void testAcceptedApplicationStatus() {
		
		//after accepted method application status will be Accepted
		rp.getAllApplications().add(app);
		rp.getAllApplications().add(app2);
		re.setCurrenProp(rp); //selected property rp
		re.setCurrentApp(app);//selected application app 
		String ID=app.getApplicationID();
		re.setAppID(ID);//for comparison
		
		re.acceptApplication();
		assertEquals(ApplicationStatus.Accepted, app.getAppStatus());
	}
	
	@Test 
	void testOtherApplicationStatus() {
		//after accepted 1 app, the other apps status will be Rejected
		rp.getAllApplications().add(app);
		rp.getAllApplications().add(app2);
		re.setCurrenProp(rp); //selected property rp
		re.setCurrentApp(app);//selected application app 
		String ID=app.getApplicationID();
		re.setAppID(ID);//for comparison
		
		re.acceptApplication();
		assertEquals(ApplicationStatus.Rejected, app2.getAppStatus());
	}
	
	
	//negative
	@Test 
	void testResponseToRejectedAppException() {
		//respond to a rejected app will through StatusException
		app.rejectApp();
		re.setCurrentApp(app);
		  assertThrows(StatusException.class, () -> {
		      re.respondtoApplication();
		    });
	}
	
	@Test 
	void testRespondToAcceptedAppException() {
		//respond to an accepted app will through StatusException
		app.acceptApp();
		re.setCurrentApp(app);
		  assertThrows(StatusException.class, () -> {
		      re.respondtoApplication();
		    });
	}
}
