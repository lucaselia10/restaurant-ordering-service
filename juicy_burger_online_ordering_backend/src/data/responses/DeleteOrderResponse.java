package data.responses;

import data.requests.DeleteOrderRequest;
import exceptions.OrderDoesNotExistException;

public class DeleteOrderResponse {
    private boolean didItWork;
    private DeleteOrderResponse(Builder builder){
        this.didItWork = builder.didItWork;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private boolean didItWork;


        private Builder() {

        }

        public Builder willItWork(boolean didItWork) {
            this.didItWork = didItWork;
            return this;
        }
        public DeleteOrderResponse build() {
            return new DeleteOrderResponse(this);}
    }
}
