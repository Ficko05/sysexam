package threads;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class JsonProducer implements Runnable {

    BlockingQueue<String> paths;
    BlockingQueue<String> returnedJson;

    public JsonProducer(BlockingQueue<String> urls, BlockingQueue<String> returnedJson) {
        this.paths = urls;
        this.returnedJson = returnedJson;
    }

    //producer/consumer?
    @Override
    public synchronized void run() { //synchronized?
        boolean morePaths = true;
        while (morePaths) {
            try {
                String path = paths.poll();

                if (path == null) {
                    morePaths = false;

                } else {

                    URL url = new URL(path + "/");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Accept", "application/json;charset=UTF-8");
                    con.setRequestProperty("User-Agent", "server");
                    Scanner scan = new Scanner(con.getInputStream());
                    String jsonStr = null;
                    if (scan.hasNext()) {
                        jsonStr = scan.nextLine();
                    }
                    scan.close();
                    returnedJson.put(jsonStr); //why put not add
                    returnedJson.put("\n");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
