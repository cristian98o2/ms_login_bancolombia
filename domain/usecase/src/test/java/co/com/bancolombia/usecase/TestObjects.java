package co.com.bancolombia.usecase;

import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.user.signin.model.Signin;
import co.com.bancolombia.model.user.signin.model.response.Session;
import co.com.bancolombia.model.user.signup.model.Signup;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestObjects {

    public static Signup signup(){
        return new Signup("cracarde@bancolombia.com", "Bancolombia123.","cristian");
    }

    public static Signin signin(){
        return new Signin("cracarde@bancolombia.com", "Bancolombia123.");
    }

    public static Session session(){
        return new Session("123456799123456789");
    }

    public static ContextData contextData() {
        return new ContextData("5fa4e536-c95a-49cc-a1c1-b46a1a33c740", "5fa4e536-c95a-49cc-a1c1-b46a1a33c740");
    }
}
