package ru.kazan.kpfu.itis.master.astafyev.app.api;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kazan.kpfu.itis.master.astafyev.app.util.AccessVariablesForAPI;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Getting information from VK with API
 *****/

@Controller
public class ApiVKController {
    private final Methods methods;
    private final AccessVariablesForAPI vars;

    public ApiVKController(Methods methods, AccessVariablesForAPI vars) {
        this.methods = methods;
        this.vars = vars;
    }

    @ResponseBody
    @RequestMapping(value = "/api/vk", method = RequestMethod.POST)
    public String getInformationAboutVKSource(
            @RequestParam("photo") String photo_url) throws IOException {
        String photo = cutPhotoIdFromURL(photo_url);
        String PHOTO_ID = "photos=" + photo;
        String url = (vars.VK_MAIN_URL_ADDRESS + vars.VK_API_METHOD +
                PHOTO_ID + vars.VK_ACCESS_TOKEN_HEADER + vars.VK_ACCESS_TOKEN);

        JSONObject result = methods.getRequestResultByURL(url);
        int likes_count = result.getJSONArray("response").
                getJSONObject(0).getJSONObject("likes").getInt("count");
        int reposts_count = result.getJSONArray("response").
                getJSONObject(0).getJSONObject("reposts").getInt("count");

        String image_url = methods.getVKImageURL(result);
        JSONObject json = new JSONObject();
        json.put("likes", likes_count);
        json.put("reposts", reposts_count);
        json.put("image_url", image_url);
        return json.toString();
    }

    public static String cutPhotoIdFromURL(String url) {
        Pattern pattern = Pattern.compile("\\?z=photo");
        Matcher match = pattern.matcher(url);
        if (match.find()) {
            String[] parts = url.split("\\?z=photo");
            String[] params = parts[1].split("%");
            url = params[0];
        }
        return url;
    }
}