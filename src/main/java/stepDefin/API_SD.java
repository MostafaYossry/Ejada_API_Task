package stepDefin;

import org.json.JSONException;

import config.ConfigurationFile;
import methods.MethodsFile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class API_SD {

// create objects from other used classes
	ConfigurationFile configurationFile = new ConfigurationFile();
	MethodsFile methodsFile = new MethodsFile();

// declare the used variable over this class 
	public String BaseURL = configurationFile.BaseURL;
	public String BearerToken = configurationFile.token;
	public String created_id_1 , created_id_2 ;
	
	@Given("The site is up and running \\(Check status API)")
	public void the_site_is_up_and_running_check_status_api() {
		methodsFile.GetMethod(BaseURL, "/status", 200);
		methodsFile.Assert_value("status","OK" );	    
	}

	@When("I need to check the list of all books")
	public void i_need_to_check_the_list_of_all_books() {
		methodsFile.GetMethod(BaseURL, "/books", 200);
	}

	@Then("I get list of books in response")
	public void i_get_list_of_books_in_response() {
		methodsFile.Assert_value("id" , "name", "1","2", "The Russian","Just as I Am" );
		}

	@When("I need to check single book with ID {string}")
	public void i_need_to_check_single_book_with_id(String id) {
		methodsFile.GetMethod(BaseURL, "/books/"+id , 200);
		}

	@Then("I get one book in response with ID {string}")
	public void i_get_one_book_in_response_with_id(String id) {
		methodsFile.Assert_value("id",id);
		}

	@When("I Submit an order")
	public void i_submit_an_order() throws JSONException {
 // Data to be sent in the POST request body (JSON format)
        String requestBody = "{\n" +
                "  \"bookId\": \"1\",\n" +
                "  \"customerName\": \"Mostafa\"\n" +
                "}";

        methodsFile.PostMethod(BaseURL, "/orders", 201, requestBody,BearerToken);

 // Data to be sent in the POST request body (JSON format)
        requestBody = "{\n" +
                "  \"bookId\": \"3\",\n" +
                "  \"customerName\": \"Alsaddek\"\n" +
                "}";

        methodsFile.PostMethod(BaseURL, "/orders", 201, requestBody,BearerToken);
        }

	@Then("I get the created value is {string}")
	public void i_get_the_created_value_is(String status) {
		methodsFile.Assert_value("created", status);
		}

	@Then("I save the created ID")
	public void i_save_the_created_id() {
		created_id_1 = methodsFile.ReturnFromResponse("orderId");
		created_id_2 = methodsFile.ReturnFromResponse("orderId");
		}

	@When("I need to check all my orders")
	public void i_need_to_check_all_my_orders() {
		methodsFile.GetMethod(BaseURL, "/orders", 200, BearerToken);
		}

	@Then("I found one order with the saved ID")
	public void i_found_one_order_with_the_saved_id() {
		methodsFile.Assert_value("id" , created_id_1, created_id_2 );
		}

	@Then("I need to view this order with the saved ID")
	public void i_need_to_view_this_order_with_the_saved_id() {
		methodsFile.GetMethod(BaseURL, "/orders/" + created_id_1 , 200,BearerToken);
		methodsFile.GetMethod(BaseURL, "/orders/" + created_id_2 , 200,BearerToken);
	}

	@When("I need to update the order with the saved ID")
	public void i_need_to_update_the_order_with_the_saved_id() throws JSONException {
// Data to be sent in the POST request body (JSON format)
        String requestBody = "{\n" +
                "  \"customerName\": \"Mostafa New Name\"\n" +
                "}";
        
        methodsFile.PatchMethod(BaseURL, "/orders/" +created_id_2 , 204, requestBody, BearerToken);
        }

	@Then("I need to check the order details after updating")
	public void i_need_to_check_the_order_details_after_updating() {
		methodsFile.GetMethod(BaseURL, "/orders/" + created_id_1 , 200,BearerToken);
		methodsFile.Assert_value( "customerName", "Mostafa New Name");
		}

	@When("I need to delete this order")
	public void i_need_to_delete_this_order() throws JSONException {
		methodsFile.DeleteMethod(BaseURL, "/orders/" + created_id_1 , 204 ,BearerToken);
		}

	@Then("I can not find this order again")
	public void i_can_not_find_this_order_again() {
		methodsFile.GetMethod(BaseURL, "/orders/" + created_id_1 , 404 ,BearerToken);
		methodsFile.Assert_value("error", "No order with id " + created_id_1 + "." );
		}
	}
