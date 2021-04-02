package com.cowboysmall.scratch.boxever.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.lang.String.format;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge<N> {

    private N source;

    private N destination;

    private Integer weight;


    //_________________________________________________________________________

    public boolean hasSource(N source) {

        return this.source.equals(source);
    }

    public boolean hasDestination(N destination) {

        return this.destination.equals(destination);
    }

    @Override
    public String toString() {

        return format("%s -- %s (%s)", source, destination, weight);
    }
}
