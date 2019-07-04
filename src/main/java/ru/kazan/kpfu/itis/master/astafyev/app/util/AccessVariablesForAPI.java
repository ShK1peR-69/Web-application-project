package ru.kazan.kpfu.itis.master.astafyev.app.util;

import org.springframework.stereotype.Component;

@Component
public class AccessVariablesForAPI {

    public String INSTAGRAM_ACCESS_TOKEN = "4023814518.246b894.5d152b00bd5044f296dd41a70d3f0c49";
    public String INSTAGRAM_API_METHOD = "https://api.instagram.com/v1/media/";
    public String INSTAGRAM_POST_INFO = "https://api.instagram.com/oembed/?callback=&url=";

    public String VK_ACCESS_TOKEN_HEADER = "&extended=1&v=5.95&access_token=";
    public String VK_ACCESS_TOKEN = "b834158cc516d16bf5824fe1614460266a7c75546" +
            "636948e0ccefb336176a760b5722387248d3b9cacb32";
    public String VK_API_METHOD = "photos.getById?";
    public String VK_MAIN_URL_ADDRESS = "https://api.vk.com/method/";

    public String YOUTUBE_PARAMETER = "&key=";
    public String YOUTUBE_ACCESS_KEY = "AIzaSyCo-HI608ZQXcqjql4JjZHZKq8IB6HYtVg";
    public String YOUTUBE_MAIN_URL = "https://www.googleapis.com/youtube/v3/" +
            "videos?part=statistics%2C+snippet&id=";
}
