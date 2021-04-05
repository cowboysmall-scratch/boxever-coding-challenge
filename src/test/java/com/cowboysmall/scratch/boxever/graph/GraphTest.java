package com.cowboysmall.scratch.boxever.graph;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class GraphTest {

    @Test
    public void containsNode() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));


        assertThat(graph.containsNode("A"), is(true));
        assertThat(graph.containsNode("B"), is(true));
        assertThat(graph.containsNode("C"), is(true));
        assertThat(graph.containsNode("Z"), is(false));
    }


    @Test
    public void findEdge() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));


        assertThat(graph.findEdge("A", "B"), is(notNullValue()));
        assertThat(graph.findEdge("B", "A"), is(notNullValue()));
        assertThat(graph.findEdge("B", "C"), is(notNullValue()));
        assertThat(graph.findEdge("C", "B"), is(notNullValue()));

        assertThat(graph.findEdge("A", "C"), is(nullValue()));
        assertThat(graph.findEdge("C", "A"), is(nullValue()));
    }


    @Test
    public void findEdge_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));


        assertThat(graph.findEdge("A", "B"), is(notNullValue()));
        assertThat(graph.findEdge("B", "C"), is(notNullValue()));

        assertThat(graph.findEdge("A", "C"), is(nullValue()));
    }


    @Test
    public void findEdgeNeighbours() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("A", "C", 6L));
        graph.addEdge(new Edge<>("A", "D", 4L));


        assertThat(graph.findEdgeNeighbours("A").size(), is(3));

        assertThat(graph.findEdgeNeighbours("B").size(), is(1));
        assertThat(graph.findEdgeNeighbours("C").size(), is(1));
        assertThat(graph.findEdgeNeighbours("D").size(), is(1));
    }


    @Test
    public void findEdgeNeighbours_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("A", "C", 6L));
        graph.addEdge(new Edge<>("A", "D", 4L));


        assertThat(graph.findEdgeNeighbours("A").size(), is(3));

        assertThat(graph.findEdgeNeighbours("B").size(), is(0));
        assertThat(graph.findEdgeNeighbours("C").size(), is(0));
        assertThat(graph.findEdgeNeighbours("D").size(), is(0));
    }
}
