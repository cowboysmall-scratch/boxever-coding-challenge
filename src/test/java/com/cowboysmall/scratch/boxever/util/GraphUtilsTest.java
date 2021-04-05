package com.cowboysmall.scratch.boxever.util;

import com.cowboysmall.scratch.boxever.graph.Edge;
import com.cowboysmall.scratch.boxever.graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GraphUtilsTest {

    @Test
    public void hasPath() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("A", "Z", 3L));


        assertThat(GraphUtils.hasPath(graph, "A", "B"), is(true));
        assertThat(GraphUtils.hasPath(graph, "A", "Z"), is(true));

        assertThat(GraphUtils.hasPath(graph, "Z", "A"), is(true));
        assertThat(GraphUtils.hasPath(graph, "Z", "B"), is(true));
    }


    @Test
    public void hasPath_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("A", "Z", 3L));


        assertThat(GraphUtils.hasPath(graph, "A", "B"), is(true));
        assertThat(GraphUtils.hasPath(graph, "A", "Z"), is(true));

        assertThat(GraphUtils.hasPath(graph, "Z", "A"), is(false));
        assertThat(GraphUtils.hasPath(graph, "Z", "B"), is(false));
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


        List<Edge<String, Long>> edges1 = GraphUtils.shortestPath(graph, "A", "Z");

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


        List<Edge<String, Long>> edges2 = GraphUtils.shortestPath(graph, "Z", "A");

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


        List<Edge<String, Long>> edges1 = GraphUtils.shortestPath(graph, "A", "Z");

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


        List<Edge<String, Long>> edges2 = GraphUtils.shortestPath(graph, "Z", "A");

        assertThat(edges2.size(), is(0));
    }


    @Test
    public void shortestPath_NoPath() {

        Graph<String, Long> graph = new Graph<>();

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("G", "H", 5L));


        assertThat(GraphUtils.shortestPath(graph, "C", "G").size(), is(0));
        assertThat(GraphUtils.shortestPath(graph, "C", "A").size(), is(2));
    }


    @Test
    public void shortestPath_NoPath_Directed() {

        Graph<String, Long> graph = new Graph<>(true);

        graph.addEdge(new Edge<>("A", "B", 5L));
        graph.addEdge(new Edge<>("B", "C", 6L));
        graph.addEdge(new Edge<>("G", "H", 5L));


        assertThat(GraphUtils.shortestPath(graph, "C", "G").size(), is(0));
        assertThat(GraphUtils.shortestPath(graph, "C", "A").size(), is(0));
    }
}
