package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main {

    private static final String SKATURL = "https://vent.skat.dk/inqueue.aspx?c=skat&e=prod160304nemid&q=cb2c7f1a-8092-4b73-be90-299de8610f34&i=635933917086290542&cid=da-DK";

    public static void main(String[] args) {

        Thread queue = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    Document doc = null;
                    try {
                        doc = Jsoup.connect(SKATURL).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String divContents = doc.select("#MainPart_lbUsersInLineAheadOfYou").first().text();
                System.out.println("Antal brugere i kø foran dig: "+Integer.parseInt(divContents));

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        queue.start();
    }
}
