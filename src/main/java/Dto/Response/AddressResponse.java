package Dto.Response;


import com.mysql.cj.log.Log;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private Long id;
    private String province;
    private String district;
    private String town;
    private String detail;
}
