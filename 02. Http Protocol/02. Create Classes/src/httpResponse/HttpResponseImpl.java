package httpResponse;

import com.sun.deploy.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HttpResponseImpl implements HttpResponse {

    private int statusCode;
    private byte[] content;
    private HashMap<String, String> headers;

    public HttpResponseImpl(int statusCode, byte[] content, HashMap<String, String> headers) {
        this.statusCode = statusCode;
        this.content = content;
        this.headers = headers;
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        byte[] headersBytes = Base64.getDecoder().decode(this.headers.values().stream()
                .collect(Collectors.joining("")));
        byte[] array = Arrays.copyOf(headersBytes, headersBytes.length + this.content.length);
            System.arraycopy(this.content,0, array, headersBytes.length, this.content.length);

        return array;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }
}
