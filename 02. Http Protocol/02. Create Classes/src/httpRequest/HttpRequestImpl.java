package httpRequest;

import java.util.HashMap;

public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String requestUrl;
    private HashMap<String, String> bodyParameters;
    private HashMap<String, String> headers;

    public HttpRequestImpl(String method, String requestUrl,
                           HashMap<String, String> bodyParameters, HashMap<String, String> headers) {
        this.setMethod(method);
        this.setRequestUrl(requestUrl);
        this.bodyParameters = bodyParameters;
        this.headers = headers;
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public HashMap<String, String> getBodyParameters() {
        return this.bodyParameters;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return false;
    }
}
