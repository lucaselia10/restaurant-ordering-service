package data.requests;

import java.util.Objects;

public class GetOrderRequest {
    private String id;

    public GetOrderRequest() {

    }

    public GetOrderRequest(Builder builder) {
        this.id = builder.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetOrderRequest that = (GetOrderRequest) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "GetOrderRequest{" +
                "id='" + id + '\'' +
                '}';
    }

    public Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private String id;

        private Builder() {

        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }
        public GetOrderRequest build() {
            return new GetOrderRequest(this);
        }
    }
}
