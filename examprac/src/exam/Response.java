public class Response{
    private List<Head> header;
    private List<Body> body;

    public List<Head> getHeader() {
        return header;
    }

    public void setHeader(List<Head> header) {
        this.header = header;
    }

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }
}