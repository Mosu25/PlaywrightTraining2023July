package userdata;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDataUsingLombok {

    private int id;
    private String first_name;
    private String last_name;
    private String email;

}
