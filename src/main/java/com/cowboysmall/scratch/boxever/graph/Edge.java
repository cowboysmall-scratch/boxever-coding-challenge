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
public class Edge<N, V extends Number> {

    private N source;

    private N destination;

    private V weight;


    //_________________________________________________________________________

    public boolean hasSource(N source) {

        return this.source.equals(source);
    }

    public boolean hasDestination(N destination) {

        return this.destination.equals(destination);
    }

    public Edge<N, V> reverse() {

        return Edge.<N, V>builder()
                .source(destination)
                .destination(source)
                .weight(weight)
                .build();
    }

    @Override
    public String toString() {

        return format("%s -- %s (%s)", source, destination, weight);
    }
}
