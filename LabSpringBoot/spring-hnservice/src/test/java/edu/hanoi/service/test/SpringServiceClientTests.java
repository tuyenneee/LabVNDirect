package edu.hanoi.service.test;

import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(JUnit4.class)
public class SpringServiceClientTests {
    private RestTemplate restTemplate;

    @Before
    public void setUp(){
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @Test
    public void test1(){
        String address = "http://localhost:8080/list/users/";
        ResponseEntity<List> entity = restTemplate.getForEntity(address, List.class);
        List<User> users = (List<User>) entity.getBody();
    }

    @Test
    public void test2(){
        String address = "http://localhost:8080/list/groups/";
        ResponseEntity<Group[]> groupEntity = restTemplate.getForEntity(address, Group[].class);
        Group[] groups = groupEntity.getBody();
        Assert.assertTrue(groups.length > 0);

        for (int i = 0; i < groups.length; i++) {
            System.out.println(groups[i].getId());
        }
    }

    @Test
    public void test3(){
        User user = new User();
        user.setUsername("khaingu20");
        user.setPassword("123456");
        user.setAge(30);
        user.setGroupId(501);
        user.setEmail("khaingu@gmail.com");

        String address = "http://localhost:8080/add/user/";
        ResponseEntity<String> insertEntity = restTemplate.postForEntity(address, user, String.class);
        Assert.assertEquals(user.getUsername(), insertEntity.getBody());
    }

    @Test
    public void test4(){
        String address = "http://localhost:8080/get/user/sa";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(address, User.class);
        Assert.assertEquals("1", getEntity.getBody().getPassword());
    }

    @Test
    public void test5(){
        String address = "http://localhost:8080/delete/user/khaingu20";
        ResponseEntity<Void> delEntity = restTemplate.getForEntity(address, Void.class);

        String address1 = "http://localhost:8080/get/user/khaingu20";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(address1, User.class);
        Assert.assertEquals(null, getEntity.getBody());
    }

    @Test
    public void test6(){
        String address = "http://localhost:8080/get/user/sa";
        ResponseEntity<User> getEntity = restTemplate.getForEntity(address, User.class);
        User user = getEntity.getBody();
        Assert.assertNotNull(user);

        user.setPassword("2001");
        String address1 = "http://localhost:8080/update/user";
        restTemplate.postForEntity(address1, user, Void.class);

        String address2 = "http://localhost:8080/get/user/sa";
        ResponseEntity<User> getEntity2 = restTemplate.getForEntity(address2, User.class);
        Assert.assertEquals(user.getPassword(), getEntity2.getBody().getPassword());
    }
}
