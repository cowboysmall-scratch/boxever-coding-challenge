package com.cowboysmall.scratch.boxever.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class GraphTest {

    @Test
    public void hasPath() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));
        graph.addEdge(new Edge<>("A", "Z", 3L));

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
    public void hasPath_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));
        graph.addEdge(new Edge<>("A", "Z", 3L));

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
    public void shortestPath() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));
        graph.addEdge(new Edge<>("E", "Z", 3L));

        graph.addEdge(new Edge<>("A", "F", 7L));
        graph.addEdge(new Edge<>("F", "G", 6L));
        graph.addEdge(new Edge<>("G", "H", 5L));
        graph.addEdge(new Edge<>("H", "I", 8L));
        graph.addEdge(new Edge<>("I", "Z", 3L));

        List<Edge<String, Long>> edges1 = graph.shortestPath("A", "Z");

        assertThat(edges1.size(), is(5));

        assertThat(edges1.get(0).getSource(), is("A"));
        assertThat(edges1.get(0).getDestination(), is("B"));
        assertThat(edges1.get(1).getSource(), is("B"));
        assertThat(edges1.get(1).getDestination(), is("C"));
        assertThat(edges1.get(2).getSource(), is("C"));
        assertThat(edges1.get(2).getDestination(), is("D"));
        assertThat(edges1.get(3).getSource(), is("D"));
        assertThat(edges1.get(3).getDestination(), is("E"));
        assertThat(edges1.get(4).getSource(), is("E"));
        assertThat(edges1.get(4).getDestination(), is("Z"));


        List<Edge<String, Long>> edges2 = graph.shortestPath("Z", "A");

        assertThat(edges2.size(), is(5));

        assertThat(edges2.get(0).getSource(), is("Z"));
        assertThat(edges2.get(0).getDestination(), is("E"));
        assertThat(edges2.get(1).getSource(), is("E"));
        assertThat(edges2.get(1).getDestination(), is("D"));
        assertThat(edges2.get(2).getSource(), is("D"));
        assertThat(edges2.get(2).getDestination(), is("C"));
        assertThat(edges2.get(3).getSource(), is("C"));
        assertThat(edges2.get(3).getDestination(), is("B"));
        assertThat(edges2.get(4).getSource(), is("B"));
        assertThat(edges2.get(4).getDestination(), is("A"));
    }


    @Test
    public void shortestPath_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));
        graph.addEdge(new Edge<>("E", "Z", 3L));

        graph.addEdge(new Edge<>("A", "F", 7L));
        graph.addEdge(new Edge<>("F", "G", 6L));
        graph.addEdge(new Edge<>("G", "H", 5L));
        graph.addEdge(new Edge<>("H", "I", 8L));
        graph.addEdge(new Edge<>("I", "Z", 3L));

        List<Edge<String, Long>> edges1 = graph.shortestPath("A", "Z");

        assertThat(edges1.size(), is(5));

        assertThat(edges1.get(0).getSource(), is("A"));
        assertThat(edges1.get(0).getDestination(), is("B"));
        assertThat(edges1.get(1).getSource(), is("B"));
        assertThat(edges1.get(1).getDestination(), is("C"));
        assertThat(edges1.get(2).getSource(), is("C"));
        assertThat(edges1.get(2).getDestination(), is("D"));
        assertThat(edges1.get(3).getSource(), is("D"));
        assertThat(edges1.get(3).getDestination(), is("E"));
        assertThat(edges1.get(4).getSource(), is("E"));
        assertThat(edges1.get(4).getDestination(), is("Z"));


        List<Edge<String, Long>> edges2 = graph.shortestPath("Z", "A");

        assertThat(edges2.size(), is(0));
    }


    @Test
    public void shortestPath_NoPath() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));

        graph.addEdge(new Edge<>("F", "G", 6L));
        graph.addEdge(new Edge<>("G", "H", 5L));
        graph.addEdge(new Edge<>("H", "I", 8L));
        graph.addEdge(new Edge<>("I", "Z", 3L));

        assertThat(graph.shortestPath("C", "G").size(), is(0));
        assertThat(graph.shortestPath("E", "A").size(), is(4));
    }


    @Test
    public void shortestPath_NoPath_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));

        graph.addEdge(new Edge<>("F", "G", 6L));
        graph.addEdge(new Edge<>("G", "H", 5L));
        graph.addEdge(new Edge<>("H", "I", 8L));
        graph.addEdge(new Edge<>("I", "Z", 3L));

        assertThat(graph.shortestPath("C", "G").size(), is(0));
        assertThat(graph.shortestPath("E", "A").size(), is(0));
    }


    @Test
    public void containsNode() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));

        assertThat(graph.containsNode("A"), is(true));
        assertThat(graph.containsNode("B"), is(true));
        assertThat(graph.containsNode("C"), is(true));
        assertThat(graph.containsNode("D"), is(true));
        assertThat(graph.containsNode("E"), is(true));
        assertThat(graph.containsNode("Z"), is(false));
    }


    @Test
    public void findEdge() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));

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
    public void findEdge_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("C", "D", 4L));
        graph.addEdge(new Edge<>("D", "E", 3L));

        assertThat(graph.findEdge("A", "B"), is(notNullValue()));
        assertThat(graph.findEdge("B", "C"), is(notNullValue()));
        assertThat(graph.findEdge("C", "D"), is(notNullValue()));
        assertThat(graph.findEdge("D", "E"), is(notNullValue()));

        assertThat(graph.findEdge("B", "D"), is(nullValue()));
    }


    @Test
    public void findEdgeNeighbours() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("A", "C", 6L));
        graph.addEdge(new Edge<>("A", "D", 4L));
        graph.addEdge(new Edge<>("A", "E", 3L));

        assertThat(graph.findEdgeNeighbours("A").size(), is(4));
        assertThat(graph.findEdgeNeighbours("E").size(), is(1));
    }


    @Test
    public void findEdgeNeighbours_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("A", "C", 6L));
        graph.addEdge(new Edge<>("A", "D", 4L));
        graph.addEdge(new Edge<>("A", "E", 3L));

        assertThat(graph.findEdgeNeighbours("A").size(), is(4));
        assertThat(graph.findEdgeNeighbours("E").size(), is(0));
    }
}
