package ala.ddin.yagu.utils;


import ala.ddin.yagu.payload.SMSTokenTO;
import com.google.gson.Gson;
import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@EnableScheduling
public class SmsServiceUtil {
    @Value("${sms.email}")
    private static String email;
    @Value("${sms.password}")
    private static String password;
    private static String token;

    public static void setToken() {
        try {
            token = getToken();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String getToken() {
        try {
            URL url = new URL("https://notify.eskiz.uz/api/auth/login");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            return generateToken(con);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String trimPhoneNumber(String phone) {
        return phone.substring(phone.indexOf("+") + 1);
    }


    private static String generateToken(HttpURLConnection con) {
        try {
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setConnectTimeout(55000);
            con.setReadTimeout(55000);
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(("email=" + email + "&password=" + password).getBytes());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();
            System.out.println("Send SMS Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                SMSTokenTO to = new Gson().fromJson(response.toString(), SMSTokenTO.class);
                System.out.println(response);
                return to.getData().getToken();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void sendSMS(String phoneNumber, String smsText) {
        boolean active = true;
        if (!active) {
            System.out.println("Sms dont send");
        } else {
            try {
                if (sendReq(phoneNumber, smsText)) {
                    System.out.println("Sent");
                } else {
                    setToken();
                    if (sendReq(phoneNumber, smsText)) {
                        System.out.println("Sent");
                    } else {
                        System.out.println("No Sent");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    @Scheduled(cron = "0 0 0 1/20 * ?")
    public void setTokenEvery20Day() {
        System.out.println("token updated");
        setToken();
    }

    private static boolean sendReq(String phoneNumber, String smsText) {
        RequestBody body = new MultipartBuilder().type(MultipartBuilder.FORM)
                .addFormDataPart("from", "4546")
                .addFormDataPart("mobile_phone", trimPhoneNumber(phoneNumber))
                .addFormDataPart("message", smsText)
                .build();
        Request request = new Request.Builder()
                .url("https://notify.eskiz.uz/api/message/sms/send")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            return response.code() == 200;
        } catch (Exception e) {
            return false;
        }
    }
}
