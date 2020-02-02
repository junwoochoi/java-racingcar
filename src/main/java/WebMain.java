import view.WebRacingHandler;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;
import static util.RenderUtil.render;

public class WebMain {
    private static WebRacingHandler webRacingHandler = new WebRacingHandler();

    public static void main(String[] args) {

        get("/", (req, res) -> render(new HashMap<>(), "/index.html"));
        post("/name", webRacingHandler.handleGetName());

        get("/result", webRacingHandler.handleResult());
    }




}