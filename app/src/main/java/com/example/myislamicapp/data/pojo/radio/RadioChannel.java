package com.example.myislamicapp.data.pojo.radio;

public class RadioChannel {

    String name;
    String radio_url;

    public RadioChannel(String name, String radio_url) {
        this.name = name;
        this.radio_url = radio_url;
    }

    public RadioChannel() {
    }

    public String getName() {
        return name;
    }

    public String getRadio_url() {
        return radio_url;
    }

}
