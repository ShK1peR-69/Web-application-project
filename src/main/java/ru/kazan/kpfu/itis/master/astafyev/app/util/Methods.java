package ru.kazan.kpfu.itis.master.astafyev.app.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kazan.kpfu.itis.master.astafyev.app.api.ApiVKController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.http.protocol.HTTP.USER_AGENT;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Additional methods for application
 *****/

@Component
public class Methods {

    public static void addImageToLocalFolder(MultipartFile file, HttpServletRequest request) throws IOException {
        String PATH = "src/main/webapp/resources/images/tmp/";
        String PATH_FOR_COPY = "\\resources\\images\\tmp\\";

        BufferedOutputStream bos;
        BufferedOutputStream bos_copy;
        byte[] fileBytes = file.getBytes();
        String fileName = getStartPathForSaveImage(request) + PATH + file.getOriginalFilename();
        String copy_file = request.getServletContext().getRealPath("/") + PATH_FOR_COPY + file.getOriginalFilename();

        bos = new BufferedOutputStream(new FileOutputStream(
                new File(fileName)));
        bos.write(fileBytes);
        bos_copy = new BufferedOutputStream(new FileOutputStream(
                new File(copy_file)));
        bos_copy.write(fileBytes);
        bos.close();
    }

    public static String hashPass(String password) {
        password = "first_sold" + password;
        password = Arrays.toString(DigestUtils.sha256(password));
        password += "second_sold";
        password = DigestUtils.md5Hex(password);
        return password;
    }

    public static String convertDateToStringFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy" + " HH:mm");
        return simpleDateFormat.format(date);
    }

    public static JSONObject getRequestResultByURL(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert response != null;
        return new JSONObject(EntityUtils.toString(response.getEntity()));
    }

    public static String chooseArticleSource(String source, String content) {
        Pattern pattern_vk = Pattern.compile("\\.userapi\\.com/");
        Matcher matcher_vk = pattern_vk.matcher(content);
        if (matcher_vk.find()) {
            source = "Change";
        }
        Pattern pattern_vk_url = Pattern.compile("vk\\.com");
        Matcher matcher_vk_url = pattern_vk_url.matcher(content);
        if (matcher_vk_url.find()) {
            source = "ВКонтакте";
        }
        Pattern pattern_instagram = Pattern.compile("instagram\\.");
        Matcher matcher_instagram = pattern_instagram.matcher(content);
        if (matcher_instagram.find()) {
            source = "Instagram";
        }
        Pattern pattern_youtube = Pattern.compile("www\\.youtube\\.com/watch");
        Matcher matcher_youtube = pattern_youtube.matcher(content);
        if (matcher_youtube.find()) {
            source = "YouTube";
        }
        return source;
    }

    public static String getVKImageURL(JSONObject url){
        String imageURL = "";
        JSONArray jsonArray = url.getJSONArray("response").
                getJSONObject(0).getJSONArray("sizes");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject j = (JSONObject) jsonArray.get(i);
            if (j.get("type").equals("x")) {
                imageURL = (String) j.get("url");
            }
        }
        return imageURL;
    }

    public static String returnNameOfArticleSource(String source, String content) throws IOException {
        if (source.equals("Instagram")) {
            content = (String) Methods.getRequestResultByURL(
                    "https://api.instagram.com/oembed/?callback=&url=" + content)
                    .get("thumbnail_url");
        }
        if (source.equals("ВКонтакте")) {
            content = Methods.getVKSourceForNewArticle(content);
        }
        if (source.equals("YouTube")) {
            content = content.replace("watch?v=", "embed/");
        }
        return content;
    }

    private static String getStartPathForSaveImage(HttpServletRequest request) {
        String path = "";
        String[] path_parts = request.getServletContext().getRealPath("/").split("\\\\");
        for (int i = 0; i < path_parts.length - 2; i++) {
            path += path_parts[i] + "/";
        }
        return path;
    }

    private static String getVKSourceForNewArticle(String content) throws IOException {

        String photo = "photos=" + ApiVKController.cutPhotoIdFromURL(content);
        content = getVKImageURL(getRequestResultByURL(
                AccessVariablesForAPI.VK_MAIN_URL_ADDRESS +
                        AccessVariablesForAPI.VK_API_METHOD + photo +
                        AccessVariablesForAPI.VK_ACCESS_TOKEN_HEADER +
                        AccessVariablesForAPI.VK_ACCESS_TOKEN));
        return content;
    }
}
