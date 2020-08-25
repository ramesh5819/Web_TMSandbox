package TradeMeUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfile {
    private final String profile_name;
    private final String user_name;
    private final String password;

    @JsonCreator
    public UserProfile(
            @JsonProperty("profile_name") String profile_name,
            @JsonProperty("user_name") String user_name,
            @JsonProperty("password") String password)
    {
        this.profile_name = profile_name;
        this.user_name = user_name;
        this.password = password;
    }


}
