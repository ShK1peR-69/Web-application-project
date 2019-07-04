package ru.kazan.kpfu.itis.master.astafyev.app.api;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kazan.kpfu.itis.master.astafyev.app.util.AccessVariablesForAPI;
import ru.kazan.kpfu.itis.master.astafyev.app.util.Methods;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*****
 * @author Igor Astafyev
 * May, 2019
 * Getting information from YouTube with API
 *****/

@Controller
public class ApiYouTubeController {

    private final HttpServletRequest request;
    private final Methods methods;
    private final AccessVariablesForAPI vars;

    public ApiYouTubeController(HttpServletRequest request,
                                Methods methods,
                                AccessVariablesForAPI vars) {
        this.request = request;
        this.methods = methods;
        this.vars = vars;
    }

    @ResponseBody
    @RequestMapping(value = "/api/youtube", method = RequestMethod.POST)
    public String getVideoInformationFromYouTube() throws IOException {
        String source_url = request.getParameter("video");
        String VIDEO_ID = cutVideoIdFromURL(source_url);
        String api_url = (vars.YOUTUBE_MAIN_URL + VIDEO_ID +
                vars.YOUTUBE_PARAMETER + vars.YOUTUBE_ACCESS_KEY);
        JSONObject result = methods.getRequestResultByURL(api_url);
        JSONObject video_statistics = result.getJSONArray("items").
                getJSONObject(0).getJSONObject("statistics");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("views", video_statistics.getInt("viewCount"));
        jsonObject.put("likes", video_statistics.getInt("likeCount"));
        jsonObject.put("dislikes", video_statistics.getInt("dislikeCount"));
        jsonObject.put("video_url", source_url.replace("watch?v=", "embed/"));
        return jsonObject.toString();
    }

    private String cutVideoIdFromURL(String url) {
        String[] url_parts = url.split("\\?v=");
        return url_parts[1];
    }

}