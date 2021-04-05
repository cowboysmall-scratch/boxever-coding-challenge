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
public class Edge<V, W extends Number> {

    private V source;

    private V destination;

    private W weight;


    //_________________________________________________________________________

    public boolean hasSource(V source) {

        return this.source.equals(source);
    }

    public boolean hasDestination(V destination) {

        return this.destination.equals(destination);
    }

    public Edge<V, W> reverse() {

        return Edge.<V, W>builder()
                .source(destination)
                .destination(source)
                .weight(weight)
                .build();
    }


    //_________________________________________________________________________

    @Override
    public String toString() {

        return format("%s -- %s (%s)", source, destination, weight);
    }
}
