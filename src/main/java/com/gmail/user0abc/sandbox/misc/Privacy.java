package com.gmail.user0abc.sandbox.misc;

import static com.gmail.user0abc.sandbox.Util.prn;

/**
 * @author Sergii Ivanov
 */
public class Privacy {
    private String mainSecret = "xyz";

    public static void main(String[] args){
        new Privacy().doPrivacy();
    }

    private void doPrivacy() {
        Child child = new Child();
        child.secret = "abc";
        prn(child.secret);
        prn(child.getMainSecret(this));
    }

    private class Child{
        private String secret;
        private String getMainSecret(Privacy privacy){
            return privacy.mainSecret;
        }
    }

}
