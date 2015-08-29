import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;

/**
 * Created by vitaly on 15.08.15.
 */
public class RxJavaEventBus {
    private static final String ADDRESS = "ping-address";

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();

        eb.consumer(ADDRESS)
                .toObservable()
                .subscribe(message -> {
                    System.out.println("Received " + message.body());
                    message.reply("PONG");
                });

        // Send a message every second
        vertx.setPeriodic(1000, v -> {
            eb.sendObservable(ADDRESS, "PING")
                    .subscribe(reply -> {
                        System.out.println("Received reply " + reply.body());
                    });
        });
    }
}
