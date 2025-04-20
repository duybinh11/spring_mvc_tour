package Controller10;


import Dto.Request.CustomerRequest;
import Dto.Request.CustomerUpdateRequest;
import Dto.Response.CustomerResponse;
import Service.CustomerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public CustomerResponse add(@RequestBody CustomerRequest customerRequest) {
        return customerService.add(customerRequest);
    }

    @PutMapping
    public CustomerResponse update(@RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return customerService.updateCustomer(customerUpdateRequest);
    }

    @PostMapping(path = "/avt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, String> result = new HashMap<>();
        try {
            String apiKey = "4ddede4ada64aae1e0793f5c61eba4b4";
            String base64 = Base64.getEncoder().encodeToString(file.getBytes());
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.imgbb.com/1/upload?key=" + apiKey))
                    .POST(HttpRequest.BodyPublishers.ofString("image=" + URLEncoder.encode(base64, StandardCharsets.UTF_8)))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());
            String imageUrl = json.getJSONObject("data").getString("url");
            result.put("img", imageUrl);
            return ResponseEntity.ok(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            result.put("error",e.getMessage());
            return ResponseEntity.ok(result);
        }
    }

}
