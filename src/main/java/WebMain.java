import view.WebRacingHandler;

import java.util.HashMap;

import static spark.Spark.*;
import static util.RenderUtil.render;

public class WebMain {
    public static final int PORT = 8080;
    private static WebRacingHandler webRacingHandler = new WebRacingHandler();

    public static void main(String[] args) {
        port(PORT);

        get("/", (req, res) -> render(new HashMap<>(), "/index.html"));

        post("/name", webRacingHandler.handleGetName());

        post("/result", webRacingHandler.handleResult());
    }




}