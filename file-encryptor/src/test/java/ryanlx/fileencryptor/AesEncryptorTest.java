package ryanlx.fileencryptor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AesEncryptorTest {

    @Test
    void encrypt() throws Exception {
        System.out.println(AesEncryptor.encrypt("text", "ps"));
    }

    @Test
    void decrypt() throws Exception {
        System.out.println(AesEncryptor.decrypt("6Oqb4ZKBJQbTmASgo7gd/w==","ps"));
    }
}