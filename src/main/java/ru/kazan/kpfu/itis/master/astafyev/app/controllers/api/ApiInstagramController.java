package ru.kazan.kpfu.itis.master.astafyev.app.controllers.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kazan.kpfu.itis.master.astafyev.app.util.AccessVariablesForAPI;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Getting information from Instagram with API
 *****/

@Controller
public class ApiInstagramController {

    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping(value = "/api/instagram", method = RequestMethod.POST)
    public String getInformationAboutInstagramItem() throws IOException {
        String post_url = request.getParameter("media");
        String content_URL = AccessVariablesForAPI.INSTAGRAM_POST_INFO + post_url;
        JSONObject result = Methods.getRequestResultByURL(content_URL);

        JSONObject media_result = Methods.getRequestResultByURL(AccessVariablesForAPI.INSTAGRAM_API_METHOD +
                result.get("media_id") + "?access_token=" + AccessVariablesForAPI.INSTAGRAM_ACCESS_TOKEN);
        int likes_count = media_result.getJSONObject("data").getJSONObject("likes").getInt("count");

        String location_name = null;
        if (!media_result.getJSONObject("data").get("location").toString().equals("null")) {
            location_name = (String) media_result.getJSONObject("data").getJSONObject("location").get("name");
            Pattern pattern = Pattern.compile("[а-яА-Я]");
            Matcher match = pattern.matcher(location_name.toLowerCase());
            if (match.find()) {
                location_name = null;
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("likes", likes_count);
        jsonObject.put("place", location_name);
        jsonObject.put("media_url", result.get("thumbnail_url"));
        jsonObject.put("author_name", result.get("author_name"));
        jsonObject.put("author_url", result.get("author_url"));
        return jsonObject.toString();
    }
}
