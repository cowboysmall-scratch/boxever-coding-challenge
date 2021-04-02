package com.cowboysmall.scratch.boxever.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Comparator.comparingInt;

public class Graph<N> {

    private static final Integer MAX_VALUE = Integer.valueOf("100000000");

    private final List<Edge<N>> edges = new ArrayList<>();
    private final Set<N> nodes = new HashSet<>();

    private boolean directed;


    //_________________________________________________________________________

    public Graph(boolean directed) {

        this.directed = directed;
    }

    public Graph() {
    }


    //_________________________________________________________________________

    public void addEdge(N source, N destination, Integer weight) {

        nodes.add(source);
        nodes.add(destination);

        edges.add(Edge.<N>builder().source(source).destination(destination).weight(weight).build());
        if (!directed)
            edges.add(Edge.<N>builder().source(destination).destination(source).weight(weight).build());
    }


    //_________________________________________________________________________

    public List<Edge<N>> shortestPath(N source, N destination) {

        Map<N, Integer> dist = new HashMap<>();
        Map<N, N> prev = new HashMap<>();

        nodes.forEach(node -> dist.put(node, MAX_VALUE));
        nodes.forEach(node -> prev.put(node, null));

        dist.put(source, 0);

        PriorityQueue<N> priorityQueue =
                new PriorityQueue<>(comparingInt(dist::get));
        priorityQueue.addAll(nodes);

        while (!priorityQueue.isEmpty()) {

            N u = priorityQueue.poll();

            for (Edge<N> edge : findEdgeNeighbours(u)) {

                int alt = dist.get(u) + edge.getWeight();
                N v = edge.getDestination();

                if (alt < dist.get(v)) {

                    priorityQueue.remove(v);
                    dist.put(v, alt);
                    prev.put(v, u);
                    priorityQueue.offer(v);
                }
            }
        }

        List<Edge<N>> route = new ArrayList<>();

        N current = destination;
        N parent = prev.get(current);

        while (parent != null) {

            route.add(0, findEdge(parent, current));
            current = parent;
            parent = prev.get(current);
        }

        return route;
    }


    //_________________________________________________________________________

    public Edge<N> findEdge(N source, N destination) {

        return edges.stream()
                .filter(edge -> edge.hasSource(source))
                .filter(edge -> edge.hasDestination(destination))
                .findFirst()
                .orElse(null);
    }

    public List<Edge<N>> findEdgeNeighbours(N node) {

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
