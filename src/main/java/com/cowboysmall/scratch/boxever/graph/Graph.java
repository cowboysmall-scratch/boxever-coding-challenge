package com.cowboysmall.scratch.boxever.graph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Graph<V, W extends Number> {

    private static final BigDecimal MAX_VALUE = BigDecimal.valueOf(Long.MAX_VALUE);

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

    public boolean hasPath(V source, V destination) {

        Queue<V> queue = new LinkedList<>();
        Set<V> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {

            V u = queue.poll();

            if (u.equals(destination))
                return true;

            for (Edge<V, W> edge : findEdgeNeighbours(u)) {

                V v = edge.getDestination();
                if (!visited.contains(v)) {

                    queue.offer(v);
                    visited.add(v);
                }
            }
        }

        return false;
    }

    public List<Edge<V, W>> shortestPath(V source, V destination) {

        Map<V, BigDecimal> dist = new HashMap<>();
        Map<V, V> prev = new HashMap<>();

        nodes.forEach(node -> dist.put(node, MAX_VALUE));
        nodes.forEach(node -> prev.put(node, null));

        dist.put(source, BigDecimal.ZERO);

        PriorityQueue<V> priorityQueue =
                new PriorityQueue<>(Comparator.comparing(dist::get));
        priorityQueue.addAll(nodes);

        while (!priorityQueue.isEmpty()) {

            V u = priorityQueue.poll();

            for (Edge<V, W> edge : findEdgeNeighbours(u)) {

                BigDecimal alt = dist.get(u).add(new BigDecimal(edge.getWeight().toString()));
                V v = edge.getDestination();

                if (alt.compareTo(dist.get(v)) < 0) {

                    priorityQueue.remove(v);
                    dist.put(v, alt);
                    prev.put(v, u);
                    priorityQueue.offer(v);
                }
            }
        }

        List<Edge<V, W>> route = new ArrayList<>();

        V current = destination;
        V parent = prev.get(current);

        while (parent != null) {

            route.add(0, findEdge(parent, current));
            current = parent;
            parent = prev.get(current);
        }

        return route;
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

    public List<Edge<V, W>> findEdgeNeighbours(V node) {

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
