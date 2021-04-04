package com.cowboysmall.scratch.boxever.graph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Graph<N, V extends Number> {

    private static final BigDecimal MAX_VALUE = new BigDecimal("1000000000000.0");

    private final List<Edge<N, V>> edges = new ArrayList<>();
    private final Set<N> nodes = new HashSet<>();

    private boolean directed;


    //_________________________________________________________________________

    public Graph(boolean directed) {

        this.directed = directed;
    }

    public Graph() {
    }


    //_________________________________________________________________________

    public void addEdge(N source, N destination, V weight) {

        nodes.add(source);
        nodes.add(destination);

        edges.add(Edge.<N, V>builder().source(source).destination(destination).weight(weight).build());
        if (!directed)
            edges.add(Edge.<N, V>builder().source(destination).destination(source).weight(weight).build());
    }


    //_________________________________________________________________________

    public boolean hasPath(N source, N destination) {

        Queue<N> queue = new LinkedList<>();
        Set<N> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {

            N u = queue.poll();

            if (u.equals(destination))
                return true;

            for (Edge<N, V> edge : findEdgeNeighbours(u)) {

                N v = edge.getDestination();
                if (!visited.contains(v)) {

                    queue.offer(v);
                    visited.add(v);
                }
            }
        }

        return false;
    }

    public List<Edge<N, V>> shortestPath(N source, N destination) {

        Map<N, BigDecimal> dist = new HashMap<>();
        Map<N, N> prev = new HashMap<>();

        nodes.forEach(node -> dist.put(node, MAX_VALUE));
        nodes.forEach(node -> prev.put(node, null));

        dist.put(source, BigDecimal.ZERO);

        PriorityQueue<N> priorityQueue =
                new PriorityQueue<>(Comparator.comparing(dist::get));

        priorityQueue.addAll(nodes);

        while (!priorityQueue.isEmpty()) {

            N u = priorityQueue.poll();

            for (Edge<N, V> edge : findEdgeNeighbours(u)) {

                BigDecimal alt = dist.get(u).add(new BigDecimal(edge.getWeight().toString()));
                N v = edge.getDestination();

                if (alt.compareTo(dist.get(v)) < 0) {

                    priorityQueue.remove(v);
                    dist.put(v, alt);
                    prev.put(v, u);
                    priorityQueue.offer(v);
                }
            }
        }

        List<Edge<N, V>> route = new ArrayList<>();

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

    public Edge<N, V> findEdge(N source, N destination) {

        return edges.stream()
                .filter(edge -> edge.hasSource(source))
                .filter(edge -> edge.hasDestination(destination))
                .findFirst()
                .orElse(null);
    }

    public List<Edge<N, V>> findEdgeNeighbours(N node) {

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
