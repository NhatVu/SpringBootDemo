package com.example.demo.pattern.structural.decorator;

import java.util.Base64;

// Concrete decorators must call methods on the wrapped object,
// but may add something of their own to the result. Decorators
// can execute the added behavior either before or after the
// call to a wrapped object.
public class EncryptionDecorator extends DataSourceDecorator{
    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    // 1. Encrypt passed data.
    // 2. Pass encrypted data to the wrappee's writeData
    // method.
    @Override
    public void writeData(String data) {
        super.writeData(encode(data));
    }

    // 1. Get data from the wrappee's readData method.
    // 2. Try to decrypt it if it's encrypted.
    // 3. Return the result.
    @Override
    public String readData() {
        return decode(super.readData());
    }

    private String encode(String data) {
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += (byte) 1;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data) {
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= (byte) 1;
        }
        return new String(result);
    }
}
