package pl.sda.util;

import org.junit.Test;

public class GenerateTokenTest {

    @Test
    public void startGenerateToken() {

        String newToken = new GenerateToken().startGenerateToken();
        System.out.println(newToken);
    }
}