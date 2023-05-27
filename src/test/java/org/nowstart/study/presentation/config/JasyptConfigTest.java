package org.nowstart.study.presentation.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JasyptConfigTest {

    private final String encryptKey = "study.nowstart.org";

    @ParameterizedTest
    @CsvSource({"/v3/api-docs"})
    void jasyptStringEncryptor(String password) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptKey);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        System.out.println("encryptKey = " + encryptKey);
        String encrypt = encryptor.encrypt(password);
        System.out.println("encrypt = " + encrypt);
        String decrypt = encryptor.decrypt(encrypt);
        System.out.println("decrypt = " + decrypt);

        assertThat(password).isEqualTo(decrypt);
    }

}