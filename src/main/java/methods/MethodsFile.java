package methods;

import java.util.List;
import org.json.JSONException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;


public class MethodsFile {

	public Response response ;
	
	public void GetMethod(String BaseURL, String EndPoint, int StatusCode) {
		RestAssured.baseURI =  BaseURL;
		System.out.println("API is >> " + BaseURL+EndPoint);
        
		response = RestAssured.given()
                .when()
                .get(EndPoint)  // Hitting the specific endpoint
                .then()
                .assertThat()
                .statusCode(StatusCode)  // Assert status code is as expected
                .extract().response();
        }

//Polymorphism concept of object oriented programming as I need to use same GetMethod with more than argument (token)
	public void GetMethod(String BaseURL, String EndPoint, int StatusCode, String BearerToken) {
		RestAssured.baseURI =  BaseURL;
		System.out.println("API is >> " + BaseURL+EndPoint);
        
		response = RestAssured.given()
	            .header("Authorization", "Bearer " + BearerToken)
//	            .header("Content-Type", "application/json")
                .when()
                .get(EndPoint)  // Hitting the specific endpoint
                .then()
                .assertThat()
                .statusCode(StatusCode)  // Assert status code is as expected
                .extract().response();
        }

	public void PostMethod(String BaseURL, String EndPoint, int StatusCode, String JsonBody, String BearerToken) throws JSONException {
		RestAssured.baseURI =  BaseURL;
		System.out.println("API is >> " + BaseURL+EndPoint);

		response = RestAssured.given()
            .header("Authorization", "Bearer " + BearerToken)
            .header("Content-Type", "application/json")
            .body(JsonBody)
            .when()
            .post(EndPoint)
            .then()
            .statusCode(StatusCode)  // Assert status code is as expected
            .extract().response();
    }

	public void PatchMethod(String BaseURL, String EndPoint, int StatusCode, String JsonBody, String BearerToken) throws JSONException {
    	response = RestAssured.given()
            .header("Authorization", "Bearer " + BearerToken)
            .header("Content-Type", "application/json")  // Setting header for JSON content
            .body(JsonBody)
            .when()
            .patch(EndPoint)
            .then()
            .statusCode(StatusCode)  // Assert status code is as expected
            .extract().response();
    }

	public void DeleteMethod(String BaseURL, String EndPoint, int StatusCode, String BearerToken) throws JSONException {
        	RestAssured.given()
	            .header("Authorization", "Bearer " + BearerToken)
	            .header("Content-Type", "application/json")  // Setting header for JSON content
                .when()
                .delete(EndPoint)  // Hitting the specific endpoint
                .then()
                .assertThat()
                .statusCode(StatusCode);  // Assert status code is as expected
        	}

	public void Assert_value( String Variable , String Expected_Value) {
        // Parsing the response as a String
        String jsonResponse = response.asString();
        System.out.println("Response: " + jsonResponse);

        // Verify that the response contains expected data
        String Acual_Value = response.jsonPath().getString(Variable);
        Assert.assertEquals(Acual_Value, Expected_Value, "Expected Value does not match the Actual one!");
		}
	
//Polymorphism concept of object oriented programming as I need to use same Assert_Value with different arguments
	public void Assert_value( String Variable , int Expected_Value) {
        // Parsing the response as a String
        String jsonResponse = response.asString();
        System.out.println("Response: " + jsonResponse);

        // Verify that the response contains expected data
        int Acual_Value = response.jsonPath().getInt(Variable);
        Assert.assertEquals(Acual_Value, Expected_Value, "Expected Value does not match the Actual one!");
		}
	
//Polymorphism concept of object oriented programming as I need to use same Assert_Value with different arguments
	public void Assert_value( String Variable1 , String Variable2, String Expected_Variable1_Value1 , String Expected_Variable1_Value2 ,
			String Expected_Variable2_Value1 , String Expected_Variable2_Value2 ) {
        // Parsing the response as a String
        String jsonResponse = response.asString();
        System.out.println("Response: " + jsonResponse);

        // Verify that the response contains some data
        List<Integer> Acual_Variable1_Values = response.jsonPath().getList(Variable1);
        List<String>  Acual_Variable2_Values = response.jsonPath().getList(Variable2);
        // Assertions
        assert Acual_Variable1_Values != null && !Acual_Variable1_Values.isEmpty() : "List of IDs should not be null or empty";
        assert Acual_Variable2_Values != null && !Acual_Variable2_Values.isEmpty() : "List of IDs should not be null or empty";

        Assert.assertEquals(Acual_Variable1_Values.get(0).toString(), Expected_Variable1_Value1, "Expected Value does not match the Actual one!");
        Assert.assertEquals(Acual_Variable1_Values.get(1).toString(), Expected_Variable1_Value2, "Expected Value does not match the Actual one!");
        Assert.assertEquals(Acual_Variable2_Values.get(0), Expected_Variable2_Value1, "Expected Value does not match the Actual one!");
        Assert.assertEquals(Acual_Variable2_Values.get(1), Expected_Variable2_Value2, "Expected Value does not match the Actual one!");
        }

//Polymorphism concept of object oriented programming as I need to use same Assert_Value with different arguments
	public void Assert_value( String Variable1 , String Expected_Variable1_Value1 , String Expected_Variable1_Value2) {
        // Parsing the response as a String
        String jsonResponse = response.asString();
        System.out.println("Response: " + jsonResponse);

        // Verify that the response contains some data
        List<Integer> Acual_Variable1_Values = response.jsonPath().getList(Variable1);
        // Assertions
        assert Acual_Variable1_Values != null && !Acual_Variable1_Values.isEmpty() : "List of IDs should not be null or empty";
    	Assert.assertTrue(Acual_Variable1_Values.toString().contains(Expected_Variable1_Value1), "Expected Value does not match the Actual one!");
    	Assert.assertTrue(Acual_Variable1_Values.toString().contains(Expected_Variable1_Value2), "Expected Value does not match the Actual one!");
        }

	public String ReturnFromResponse( String Variable) {
        return response.jsonPath().getString(Variable);
		}

}
