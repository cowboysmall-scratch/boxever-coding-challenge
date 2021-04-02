package com.cowboysmall.scratch.boxever.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class GraphTest {

    @Test
    public void testGraph_ShortestPath() {

        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 6);
        graph.addEdge("C", "D", 4);
        graph.addEdge("D", "E", 3);
        graph.addEdge("E", "Z", 3);

        graph.addEdge("A", "F", 7);
        graph.addEdge("F", "G", 6);
        graph.addEdge("G", "H", 5);
        graph.addEdge("H", "I", 8);
        graph.addEdge("I", "Z", 3);

        List<Edge<String>> edges = graph.shortestPath("A", "Z");

        assertThat(edges.size(), is(5));

        assertThat(edges.get(0).getSource(), is("A"));
        assertThat(edges.get(0).getDestination(), is("B"));
        assertThat(edges.get(1).getSource(), is("B"));
        assertThat(edges.get(1).getDestination(), is("C"));
        assertThat(edges.get(2).getSource(), is("C"));
        assertThat(edges.get(2).getDestination(), is("D"));
        assertThat(edges.get(3).getSource(), is("D"));
        assertThat(edges.get(3).getDestination(), is("E"));
        assertThat(edges.get(4).getSource(), is("E"));
        assertThat(edges.get(4).getDestination(), is("Z"));
    }

    @Test
    public void testGraph_ShortestPath_SubPath() {

        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 6);
        graph.addEdge("C", "D", 4);
        graph.addEdge("D", "E", 3);
        graph.addEdge("E", "Z", 3);

        graph.addEdge("A", "F", 7);
        graph.addEdge("F", "G", 6);
        graph.addEdge("G", "H", 5);
        graph.addEdge("H", "I", 8);
        graph.addEdge("I", "Z", 3);

        List<Edge<String>> edges1 = graph.shortestPath("A", "D");
        List<Edge<String>> edges2 = graph.shortestPath("D", "Z");

        assertThat(edges1.size(), is(3));
        assertThat(edges2.size(), is(2));

        assertThat(edges1.get(0).getSource(), is("A"));
        assertThat(edges1.get(0).getDestination(), is("B"));
        assertThat(edges1.get(1).getSource(), is("B"));
        assertThat(edges1.get(1).getDestination(), is("C"));
        assertThat(edges1.get(2).getSource(), is("C"));
        assertThat(edges1.get(2).getDestination(), is("D"));

        assertThat(edges2.get(0).getSource(), is("D"));
        assertThat(edges2.get(0).getDestination(), is("E"));
        assertThat(edges2.get(1).getSource(), is("E"));
        assertThat(edges2.get(1).getDestination(), is("Z"));
    }

    @Test
    public void testGraph_ShortestPath_NoPath_Directed() {

        Graph<String> graph = new Graph<>(true);

        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 6);
        graph.addEdge("C", "D", 4);
        graph.addEdge("D", "E", 3);
        graph.addEdge("E", "Z", 3);

        graph.addEdge("A", "F", 7);
        graph.addEdge("F", "G", 6);
        graph.addEdge("G", "H", 5);
        graph.addEdge("H", "I", 8);
        graph.addEdge("I", "Z", 3);

        List<Edge<String>> edges = graph.shortestPath("C", "G");

        assertThat(edges.size(), is(0));
    }


    @Test
    public void testGraph_FindEdge() {

        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B", 5);
        graph.addEdge("B", "C", 6);
        graph.addEdge("C", "D", 4);
        graph.addEdge("D", "E", 3);

        assertThat(graph.findEdge("A", "B"), is(notNullValue()));
        assertThat(graph.findEdge("B", "C"), is(notNullValue()));
        assertThat(graph.findEdge("C", "D"), is(notNullValue()));
        assertThat(graph.findEdge("D", "E"), is(notNullValue()));

        assertThat(graph.findEdge("B", "D"), is(nullValue()));
    }


    @Test
    public void testGraph_FindEdgeNeighbours() {

        Graph<String> graph = new Graph<>();

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 6);
        graph.addEdge("A", "D", 4);
        graph.addEdge("A", "E", 3);

        List<Edge<String>> neighbours = graph.findEdgeNeighbours("A");

        assertThat(neighbours.size(), is(4));
    }
}
