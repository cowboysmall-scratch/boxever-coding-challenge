package com.cowboysmall.scratch.boxever.util;

import com.cowboysmall.scratch.boxever.graph.Edge;
import com.cowboysmall.scratch.boxever.graph.Graph;

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

public class GraphAlgorithms {

    private static final BigDecimal MAX_VALUE = BigDecimal.valueOf(Long.MAX_VALUE);


    //_________________________________________________________________________

    private GraphAlgorithms() {
    }


    //_________________________________________________________________________

    public static <V, W extends Number> boolean hasPath(Graph<V, W> graph, V source, V destination) {

        Queue<V> queue = new LinkedList<>();
        Set<V> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        while (!queue.isEmpty()) {

            V u = queue.poll();

            if (u.equals(destination))
                return true;

            for (Edge<V, W> edge : graph.findNeighbourEdges(u)) {

                V v = edge.getDestination();
                if (!visited.contains(v)) {

                    queue.offer(v);
                    visited.add(v);
                }
            }
        }

        return false;
    }

    public static <V, W extends Number> List<Edge<V, W>> shortestPath(Graph<V, W> graph, V source, V destination) {

        Map<V, BigDecimal> dist = new HashMap<>();
        Map<V, V> prev = new HashMap<>();

        Set<V> nodes = graph.getNodes();

        nodes.forEach(node -> dist.put(node, MAX_VALUE));
        nodes.forEach(node -> prev.put(node, null));

        dist.put(source, BigDecimal.ZERO);

        PriorityQueue<V> priorityQueue =
                new PriorityQueue<>(Comparator.comparing(dist::get));
        priorityQueue.addAll(nodes);

        while (!priorityQueue.isEmpty()) {

            V u = priorityQueue.poll();

            for (Edge<V, W> edge : graph.findNeighbourEdges(u)) {

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

            route.add(0, graph.findEdge(parent, current));
            current = parent;
            parent = prev.get(current);
        }

        return route;
    }
}
