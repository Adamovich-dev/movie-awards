package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
@RunWith(SpringRunner.class)
@Sql(scripts = "/init_db.sql")
@Sql(scripts = "/insert_data.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testFindByUserName() {
        UserEntity user1 = repository.findByUserName("user1");

        assertNotNull(user1);
        assertNotNull(user1.getPassword());
        assertEquals(user1.getUserName(), "user1");
    }
}
