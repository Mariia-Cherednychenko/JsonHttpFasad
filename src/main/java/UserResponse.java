import lombok.Data;

@Data
public class UserResponse {
    private String status;
    private String error;
   /* private List<Users> users;

    @Data
    public static class Users {
        private String login;
        private String date_born;

    }*/
}
