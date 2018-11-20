package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class JsonConsumer implements Runnable {

    BlockingQueue<String> jsonReceived;
    StringBuffer jsonResult;

    public JsonConsumer(BlockingQueue<String> jsonReceived, StringBuffer jsonResult) {
        this.jsonReceived = jsonReceived;
        this.jsonResult = jsonResult;
    }

    @Override
    public void run() {//why not syncronized

        boolean moreJson = true;
        while (moreJson) {
            try {
                String json = jsonReceived.poll(2, TimeUnit.SECONDS);
                
                if (json != null) {
                    jsonResult.append(json);
                } else {
                    moreJson = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
