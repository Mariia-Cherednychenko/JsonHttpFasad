public class Main {

    public static void main(String[] args) {


        JsonHttpFacade jsonHttp = new JsonHttpFacade();

        UserResponse resp = jsonHttp.get("http://example.com/users", UserResponse.class);

        CreateUserRequest req = new CreateUserRequest(
                "examplelogin",
                "examplepass"
        );

        StatusResponse resp2 = jsonHttp.post(
                "http://example.com/register",
                req,
                StatusResponse.class
        );
    }
}
