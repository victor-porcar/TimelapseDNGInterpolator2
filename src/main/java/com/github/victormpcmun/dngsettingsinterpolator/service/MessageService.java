package com.github.victormpcmun.dngsettingsinterpolator.service;

public class MessageService {
    public static final MessageService INSTANCE = new MessageService();

    public void emptyLine() {
        System.out.println("");

    }


    public void message(String s) {
        System.out.println(s);

    }
    public void messageError(String s) {
        System.err.println(s);
    }
}
