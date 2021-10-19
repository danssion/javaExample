package com.danssion.java.baseType.optional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2020/12/2 14:38
 * @desc JavaExample-DdsignPattern
 */
public class OptionalMain {
    private final Logger log = LoggerFactory.getLogger(OptionalMain.class);

    @Test(expected = NoSuchElementException.class)
//    @Test
    public void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        emptyOpt.get();
        //java.util.NoSuchElementException: No value present
    }

    @Test(expected = NullPointerException.class)
//    @Test
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        User user = null;
        Optional<User> opt = Optional.of(user);
        //java.lang.NullPointerException
    }
    @Test
    public void whenCreateOfEmptyOptional_thenNullPointerException1() {
        User user = null;
        Optional<User> optt = Optional.ofNullable(user);
    }

    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);
        assertEquals("John", opt.get());


         name = null;
        Optional<String> opt1 = Optional.ofNullable(name);
        assertEquals("John", opt1.get());
        //java.util.NoSuchElementException: No value present
    }

    //这个方法会在值为 null 的时候抛出异常。要避免异常，你可以选择首先验证是否有值
    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());

        System.out.println(opt.get().getAge());

        //检查是否有值的另一个选择是 ifPresent() 方法。该方法除了执行检查，还接受一个Consumer(消费者) 参数，如果对象不是空的，就对执行传入的 Lambda 表达式
        opt.ifPresent( u -> assertEquals(user.getEmail(), u.getEmail()));

        assertEquals(user.getEmail(), opt.get().getEmail());
    }

    //Optional 类提供了 API 用以返回对象值，或者在对象为空的时候返回默认值
    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);
        assertEquals(user2.getEmail(), result.getEmail());
    }

    @Test
    public void whenValueNotNull_thenIgnoreDefault() {
        User user = new User("john@gmail.com","1234");
        User user2 = new User("anna@gmail.com", "1234");
//        User result = Optional.ofNullable(user).orElse(user2);
        User result = Optional.ofNullable(user).orElseGet( () -> user2);

        assertEquals("john@gmail.com", result.getEmail());
    }

    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        log.debug("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        log.debug("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }
    private User createNewUser() {
        log.debug("Creating New User");
        return new User("extra@gmail.com", "1234");
    }


    //返回异常 Optional 还定义了 orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值
    @Test(expected = IllegalArgumentException.class)
    public void whenThrowException_thenOk() {
        User user = null;
        User result = Optional.ofNullable(user)
                .orElseThrow( () -> new IllegalArgumentException());
    }

    //转换值, 可以转换 Optional  的值。我们从 map() 和 flatMap() 方法开始
    // map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中
    @Test
    public void whenMap_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");
        assertEquals(email, user.getEmail());
    }




















}
