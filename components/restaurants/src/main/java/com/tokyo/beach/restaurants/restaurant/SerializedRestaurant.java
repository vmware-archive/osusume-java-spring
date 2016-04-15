package com.tokyo.beach.restaurants.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tokyo.beach.restaurants.comment.SerializedComment;
import com.tokyo.beach.restaurants.cuisine.Cuisine;
import com.tokyo.beach.restaurants.photos.PhotoUrl;
import com.tokyo.beach.restaurants.user.DatabaseUser;

import java.util.List;
import java.util.Optional;

import static com.tokyo.beach.restaurants.DateFormatter.formatDateForSerialization;

public class SerializedRestaurant {
    private Restaurant restaurant;
    private List<PhotoUrl> photoUrls;
    private Cuisine cuisine;
    private Optional<DatabaseUser> createdByUser;
    private List<SerializedComment> comments;
    private boolean currentUserLikesRestaurant;
    private long numberOfLikes;

    public SerializedRestaurant(
            Restaurant restaurant,
            List<PhotoUrl> photoUrls,
            Cuisine cuisine,
            Optional<DatabaseUser> createdByUser,
            List<SerializedComment> comments,
            boolean currentUserLikesRestaurant,
            long numberOfLikes) {
        this.restaurant = restaurant;
        this.photoUrls = photoUrls;
        this.cuisine = cuisine;
        this.createdByUser = createdByUser;
        this.comments = comments;
        this.currentUserLikesRestaurant = currentUserLikesRestaurant;
        this.numberOfLikes = numberOfLikes;
    }

    public long getId() {
        return restaurant.getId();
    }

    public String getName() {
        return restaurant.getName();
    }

    @JsonProperty("user")
    public DatabaseUser getCreatedByUser() {
        return createdByUser.orElse(null);
    }

    @SuppressWarnings("unused")
    public String getAddress() {
        return restaurant.getAddress();
    }

    @JsonProperty("offers_english_menu")
    public Boolean getOffersEnglishMenu() {
        return restaurant.getOffersEnglishMenu();
    }

    @JsonProperty("walk_ins_ok")
    public Boolean getWalkInsOk() {
        return restaurant.getWalkInsOk();
    }

    @JsonProperty("accepts_credit_cards")
    public Boolean getAcceptsCreditCards() {
        return restaurant.getAcceptsCreditCards();
    }

    @SuppressWarnings("unused")
    public String getNotes() {
        return restaurant.getNotes();
    }

    @JsonProperty("created_by_user_name")
    public String getCreatedByUserName() {
        String username = "";

        if (createdByUser.isPresent()) {
            username = createdByUser.get().getName();
        }
        return username;
    }

    @JsonProperty("created_at")
    public String getCreatedDate() {
        return formatDateForSerialization(restaurant.getCreatedDate());
    }

    @JsonProperty("photo_urls")
    public List<PhotoUrl> getPhotoUrlList() {
        return photoUrls;
    }

    @JsonProperty("comments")
    public List<SerializedComment> getComments() {
        return comments;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    @JsonProperty("liked")
    public boolean isCurrentUserLikesRestaurant() {
        return currentUserLikesRestaurant;
    }

    @JsonProperty("num_likes")
    public long getNumberOfLikes() {
        return numberOfLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializedRestaurant that = (SerializedRestaurant) o;

        if (currentUserLikesRestaurant != that.currentUserLikesRestaurant) return false;
        if (restaurant != null ? !restaurant.equals(that.restaurant) : that.restaurant != null) return false;
        if (photoUrls != null ? !photoUrls.equals(that.photoUrls) : that.photoUrls != null) return false;
        if (cuisine != null ? !cuisine.equals(that.cuisine) : that.cuisine != null) return false;
        if (createdByUser != null ? !createdByUser.equals(that.createdByUser) : that.createdByUser != null)
            return false;
        return comments != null ? comments.equals(that.comments) : that.comments == null;

    }

    @Override
    public int hashCode() {
        int result = restaurant != null ? restaurant.hashCode() : 0;
        result = 31 * result + (photoUrls != null ? photoUrls.hashCode() : 0);
        result = 31 * result + (cuisine != null ? cuisine.hashCode() : 0);
        result = 31 * result + (createdByUser != null ? createdByUser.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (currentUserLikesRestaurant ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SerializedRestaurant{" +
                "restaurant=" + restaurant +
                ", photoUrls=" + photoUrls +
                ", cuisine=" + cuisine +
                ", createdByUser=" + createdByUser +
                ", comments=" + comments +
                ", currentUserLikesRestaurant=" + currentUserLikesRestaurant +
                '}';
    }
}