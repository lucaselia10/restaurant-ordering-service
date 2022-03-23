package data.types;

import java.util.Objects;

/**
 * Defines the characteristics of a MenuItem object. A MenuItem consists
<<<<<<< HEAD
 * of String name, Long price in cents, and String description of the
=======
 * of String name, Integer price in cents, and String description of the
>>>>>>> origin/PlaceOrderActivityFeature
 * MenuItem (such as ingredients)
 * @author willi
 */
public class MenuItem {
    private String name;
<<<<<<< HEAD
    private Long price;
=======
    private Integer price;
>>>>>>> origin/PlaceOrderActivityFeature
    private String description;

    private MenuItem(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
    }

    public String getName() {
        return name;
    }

<<<<<<< HEAD
    public Long getPrice() {
=======
    public Integer getPrice() {
>>>>>>> origin/PlaceOrderActivityFeature
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return name.equals(menuItem.name) &&
                price.equals(menuItem.price) &&
                description.equals(menuItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }

    public static class Builder {
        private String name;
<<<<<<< HEAD
        private Long price;
=======
        private Integer price;
>>>>>>> origin/PlaceOrderActivityFeature
        private String description;

        public Builder withName(String builderName) {
            this.name = builderName;
            return this;
        }

<<<<<<< HEAD
        public Builder withPrice(Long builderPrice) {
=======
        public Builder withPrice(Integer builderPrice) {
>>>>>>> origin/PlaceOrderActivityFeature
            this.price = builderPrice;
            return this;
        }

        public Builder withDescription(String builderDescription) {
            this.description = builderDescription;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }
    }
}
