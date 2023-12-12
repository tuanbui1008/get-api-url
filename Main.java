package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {
        try {
            // Tạo URL từ đường dẫn cần truy cập
            URL url = new URL("https://example.com/api/endpoint");

            // Mở kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Thiết lập phương thức yêu cầu là GET
            connection.setRequestMethod("GET");

            // Thiết lập xác thực Basic Auth
            String username = "admin";
            String password = "password";
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            connection.setRequestProperty("Authorization", authHeaderValue);

            // Đọc phản hồi từ URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // In phản hồi
            System.out.println(response.toString());

            // Đóng kết nối
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}