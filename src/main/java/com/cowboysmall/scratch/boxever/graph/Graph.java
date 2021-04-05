package com.cowboysmall.scratch.boxever.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Graph<V, W extends Number> {

    private final List<Edge<V, W>> edges = new ArrayList<>();

    private final Set<V> nodes = new HashSet<>();

    private boolean directed;


    //_________________________________________________________________________

    public void addEdges(List<Edge<V, W>> edges) {

        edges.forEach(this::addEdge);
    }

    public void addEdge(Edge<V, W> edge) {

        nodes.add(edge.getSource());
        nodes.add(edge.getDestination());

        edges.add(edge);
        if (!directed)
            edges.add(edge.reverse());
    }


    //_________________________________________________________________________

    public boolean containsNode(V node) {

        return nodes.contains(node);
    }

    public Edge<V, W> findEdge(V source, V destination) {

        return edges.stream()
                .filter(edge -> edge.hasSource(source))
                .filter(edge -> edge.hasDestination(destination))
                .findFirst()
                .orElse(null);
    }

    public List<V> findNeighbours(V node) {

        return edges.stream()
                .filter(edge -> edge.getSource().equals(node))
                .map(Edge::getDestination)
                .collect(Collectors.toList());
    }

    public List<Edge<V, W>> findNeighbourEdges(V node) {

        return edges.stream()
                .filter(edge -> edge.getSource().equals(node))
                .collect(Collectors.toList());
    }


    //_________________________________________________________________________

    @Override
    public String toString() {

        return format(
                "Graph:\n\n\tNodes: \n\t\t%s\n\n\tEdges: \n\t\t%s\n\n",
                nodes.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining("\n\t\t")),
                edges.stream()
                        .map(Edge::toString)
                        .collect(Collectors.joining("\n\t\t"))
        );
    }
}
