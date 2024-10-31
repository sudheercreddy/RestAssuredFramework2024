import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmadeusAPITEStOauth2 extends BaseTest {
	
	
	@Test
	public void AmadeusAPITEST() {
		
		 Map<String, String> queryParms= Map.of("origin","PAR","maxPrice","200");
		Response res = restClient.get(BASE_URL_AMADEUS, "/v1/shopping/flight-destinations", queryParms, null, AuthType.OAUTH2, ContentType.JSON);
		
		Assert.assertEquals(res.statusCode(), 200);
		
	}

}
