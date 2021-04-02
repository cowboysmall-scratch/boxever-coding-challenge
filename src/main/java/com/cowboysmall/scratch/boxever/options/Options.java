package com.cowboysmall.scratch.boxever.options;

public class Options {

    private String source;

    private String destination;

    private boolean directed;


    //_________________________________________________________________________

    public Options(String source, String destination, boolean directed) {

        this.source = source;
        this.destination = destination;
        this.directed = directed;
    }

    public Options(String source, String destination) {

        this.source = source;
        this.destination = destination;
    }

    public Options() {
    }


    //_________________________________________________________________________

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isDirected() {
        return directed;
    }


    //_________________________________________________________________________

    public boolean isValid() {

        return source != null && !source.equals("") && destination != null && !destination.equals("");
    }
}
