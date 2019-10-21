package MyHTTPServer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


enum MyHTTPStatusCode {
    OK (200, "OK"),
    NOT_FOUND (404, "Not Found"),
    METHOD_NOT_ALLOWED (405, "Method Not Allowed"),
    CONFLICT (409, "Conflict"),
    INTERNAL_SERVER_ERROR (500, "Internal Server Error");

    private final int code;
    private final String description;

    MyHTTPStatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }
    public String getDescription() { return description; }

    public String getHTMLError() throws IOException {
        return MyHTTPThread.HTMLEntityEncode(new String(Files.readAllBytes(Paths.get(code + ".html"))) + "\n\r");
    }
}
