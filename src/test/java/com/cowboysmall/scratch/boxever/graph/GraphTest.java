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

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);
        graph.addEdge("E", "Z", 3L);

        graph.addEdge("A", "F", 7L);
        graph.addEdge("F", "G", 6L);
        graph.addEdge("G", "H", 5L);
        graph.addEdge("H", "I", 8L);
        graph.addEdge("I", "Z", 3L);

        List<Edge<String, Long>> edges = graph.shortestPath("A", "Z");

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

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);
        graph.addEdge("E", "Z", 3L);

        graph.addEdge("A", "F", 7L);
        graph.addEdge("F", "G", 6L);
        graph.addEdge("G", "H", 5L);
        graph.addEdge("H", "I", 8L);
        graph.addEdge("I", "Z", 3L);

        List<Edge<String, Long>> edges1 = graph.shortestPath("A", "D");
        List<Edge<String, Long>> edges2 = graph.shortestPath("D", "Z");

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
    public void testGraph_ShortestPath_NoPath() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);

        graph.addEdge("F", "G", 6L);
        graph.addEdge("G", "H", 5L);
        graph.addEdge("H", "I", 8L);
        graph.addEdge("I", "Z", 3L);

        assertThat(graph.shortestPath("C", "G").size(), is(0));
        assertThat(graph.shortestPath("E", "A").size(), is(4));
    }

    @Test
    public void testGraph_ShortestPath_NoPath_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);

        graph.addEdge("F", "G", 6L);
        graph.addEdge("G", "H", 5L);
        graph.addEdge("H", "I", 8L);
        graph.addEdge("I", "Z", 3L);

        assertThat(graph.shortestPath("C", "G").size(), is(0));
        assertThat(graph.shortestPath("E", "A").size(), is(0));
    }


    @Test
    public void testGraph_HasPath() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);
        graph.addEdge("A", "Z", 3L);

        assertThat(graph.hasPath("A", "B"), is(true));
        assertThat(graph.hasPath("A", "C"), is(true));
        assertThat(graph.hasPath("A", "D"), is(true));
        assertThat(graph.hasPath("A", "E"), is(true));
        assertThat(graph.hasPath("A", "Z"), is(true));

        assertThat(graph.hasPath("Z", "A"), is(true));
        assertThat(graph.hasPath("Z", "B"), is(true));
        assertThat(graph.hasPath("Z", "C"), is(true));
        assertThat(graph.hasPath("Z", "D"), is(true));
        assertThat(graph.hasPath("Z", "E"), is(true));
    }


    @Test
    public void testGraph_HasPath_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);
        graph.addEdge("A", "Z", 3L);

        assertThat(graph.hasPath("A", "B"), is(true));
        assertThat(graph.hasPath("A", "C"), is(true));
        assertThat(graph.hasPath("A", "D"), is(true));
        assertThat(graph.hasPath("A", "E"), is(true));
        assertThat(graph.hasPath("A", "Z"), is(true));

        assertThat(graph.hasPath("Z", "A"), is(false));
        assertThat(graph.hasPath("Z", "B"), is(false));
        assertThat(graph.hasPath("Z", "C"), is(false));
        assertThat(graph.hasPath("Z", "D"), is(false));
        assertThat(graph.hasPath("Z", "E"), is(false));
    }


    @Test
    public void testGraph_FindEdge() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);

        assertThat(graph.findEdge("A", "B"), is(notNullValue()));
        assertThat(graph.findEdge("B", "A"), is(notNullValue()));
        assertThat(graph.findEdge("B", "C"), is(notNullValue()));
        assertThat(graph.findEdge("C", "B"), is(notNullValue()));
        assertThat(graph.findEdge("C", "D"), is(notNullValue()));
        assertThat(graph.findEdge("D", "C"), is(notNullValue()));
        assertThat(graph.findEdge("D", "E"), is(notNullValue()));
        assertThat(graph.findEdge("E", "D"), is(notNullValue()));

        assertThat(graph.findEdge("B", "D"), is(nullValue()));
        assertThat(graph.findEdge("D", "B"), is(nullValue()));
    }


    @Test
    public void testGraph_FindEdge_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge("A", "B", 5L);
        graph.addEdge("B", "C", 6L);
        graph.addEdge("C", "D", 4L);
        graph.addEdge("D", "E", 3L);

        assertThat(graph.findEdge("A", "B"), is(notNullValue()));
        assertThat(graph.findEdge("B", "C"), is(notNullValue()));
        assertThat(graph.findEdge("C", "D"), is(notNullValue()));
        assertThat(graph.findEdge("D", "E"), is(notNullValue()));

        assertThat(graph.findEdge("B", "D"), is(nullValue()));
    }


    @Test
    public void testGraph_FindEdgeNeighbours() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge("A", "B", 5L);
        graph.addEdge("A", "C", 6L);
        graph.addEdge("A", "D", 4L);
        graph.addEdge("A", "E", 3L);

        assertThat(graph.findEdgeNeighbours("A").size(), is(4));
        assertThat(graph.findEdgeNeighbours("E").size(), is(1));
    }


    @Test
    public void testGraph_FindEdgeNeighbours_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge("A", "B", 5L);
        graph.addEdge("A", "C", 6L);
        graph.addEdge("A", "D", 4L);
        graph.addEdge("A", "E", 3L);

        assertThat(graph.findEdgeNeighbours("A").size(), is(4));
        assertThat(graph.findEdgeNeighbours("E").size(), is(0));
    }
}
