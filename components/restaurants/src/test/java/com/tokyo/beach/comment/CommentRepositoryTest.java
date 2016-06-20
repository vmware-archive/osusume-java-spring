package com.tokyo.beach.comment;

import com.tokyo.beach.restaurant.RestaurantFixture;
import com.tokyo.beach.restaurants.comment.*;
import com.tokyo.beach.restaurants.restaurant.Restaurant;
import com.tokyo.beach.restaurants.user.User;
import com.tokyo.beach.user.UserFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.tokyo.beach.TestDatabaseUtils.*;
import static org.junit.Assert.assertEquals;

public class CommentRepositoryTest {

    JdbcTemplate jdbcTemplate;
    CommentRepository commentRepository;
    CommentDataMapper commentDataMapper;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = new JdbcTemplate(buildDataSource());
        commentRepository = new CommentRepository(jdbcTemplate);
        commentDataMapper = new CommentDataMapper(jdbcTemplate);
        createDefaultCuisine(jdbcTemplate);
        createDefaultPriceRange(jdbcTemplate);
    }

    @After
    public void tearDown() throws Exception {
        truncateAllTables(jdbcTemplate);
    }

    @Test
    public void test_findForRestaurant_returnsCommentsOnRestaurant() throws Exception {
        User user = new UserFixture().persist(jdbcTemplate);
        Restaurant restaurant = new RestaurantFixture().withUser(user).persist(jdbcTemplate);

        Comment createdComment = commentDataMapper.create(
                new NewComment("New Comment Content"),
                user.getId(),
                restaurant.getId()
        );

        List<SerializedComment> actualComments = commentRepository.findForRestaurant(restaurant.getId());
        assertEquals(actualComments.size(), 1);
        assertEquals(actualComments.get(0).getComment(), createdComment.getComment());
        assertEquals(actualComments.get(0).getUser().getId(), createdComment.getCreatedByUserId());
        assertEquals(actualComments.get(0).getRestaurantId(), createdComment.getRestaurantId());
    }
}
