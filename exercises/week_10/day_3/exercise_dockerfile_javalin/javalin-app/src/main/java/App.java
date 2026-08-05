import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import java.time.Instant;
import java.util.Map;
import java.net.InetAddress;

public class App {
    public static void Main(String[] args) {
        String version = System.getenv().getOrDefault("APP_VERSION", "1.0.0");
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));

        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson());
        }).start(port);

        // home endpoint
        app.get("/", ctx -> { // ctx = context
            String hostname = InetAddress.getLocalHost().getHostName();
            ctx.json(Map.of(
                    "message", "Hello from Docker and Javalin!",
                    "hostname", hostname,
                    "timestamp", Instant.now().toString(),
                    "version", version));
        });

        // health endpoint
        app.get("/health", ctx -> {
            ctx.json(Map.of(
                    "status", "healthy",
                    "service", "javalin-demo"));
        });

        // environment info endpoint
        app.get("/env", ctx -> {
            ctx.json(Map.of(
                    "javaVersion", System.getProperty("java.version"),
                    "appVersion", version,
                    "port", port));
        });

        System.out.println("Javalin app started on port " + port);
    }
}