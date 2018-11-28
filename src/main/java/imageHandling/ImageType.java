package imageHandling;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public enum ImageType
{

    JPG("jpg", "image/jpg"),
    PNG("png", "image/png"),
    GIF("gif", "image/gif");

  
    private final String extension;

    
    private final String mime;

   
    ImageType(String extension, String mime)
    {
        this.extension = extension;
        this.mime = mime;
    }

    public String getExtension()
    {
        return this.extension;
    }

    public String getMime()
    {
        return this.mime;
    }


    public static ImageType fromMime(String mime) throws NullPointerException
    {
        switch (mime) {
            case "image/jpg":
                return ImageType.JPG;
            case "image/jpeg":
                return ImageType.JPG;
            case "image/gif":
                return ImageType.GIF;
            case "image/png":
                return ImageType.PNG;
            default:
                throw new NullPointerException();
        }
    }

    public static ImageType fromData(byte[] data) throws NullPointerException
    {
        try {
            InputStream is   = new ByteArrayInputStream(data);
            String      mime = URLConnection.guessContentTypeFromStream(is);
            if (mime == null)
                throw new NullPointerException();
            return ImageType.fromMime(mime);
        } catch (IOException e) {
            throw new NullPointerException();
        }
    }
}
