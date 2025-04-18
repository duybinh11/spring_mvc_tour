package Controller10;

import Dto.Request.TourRequest;
import Dto.Response.TourResponse;
import Entity.Tour;
import Service.TourService;
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
import java.util.*;

@RestController
@RequestMapping("/tour")
public class TourController {

    @Autowired
    private TourService tourService;
    @PostMapping()
    public TourResponse addTour(@RequestBody TourRequest tourRequest) {
        return tourService.addTour(tourRequest);
    }

    @GetMapping()
    public List<TourResponse> getHotTour() {
        return tourService.getAllTours();
    }

    @PostMapping(path = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, Object>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        Map<String, Object> result = new HashMap<>();
        List<String> imageUrls = new ArrayList<>();
        String apiKey = "4ddede4ada64aae1e0793f5c61eba4b4";

        for (MultipartFile file : files) {
            try {
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
                imageUrls.add(imageUrl);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                result.put("error", "Failed to upload one or more images: " + e.getMessage());
                return ResponseEntity.ok(result);
            }
        }

        result.put("images", imageUrls);
        return ResponseEntity.ok(result);
    }


}
