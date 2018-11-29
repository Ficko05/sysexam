package imageHandling;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;


public class DataUriEncoder
{

  
    public String encode(byte[] data, ImageType type)
    {
        return String.format("data:%s;base64,%s", type.getMime(), Base64.getEncoder().encodeToString(data));
    }

 
    public String encode(BufferedImage bufferedImage, ImageType type)
    {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, type.getExtension(), outputStream);
            return encode(outputStream.toByteArray(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
