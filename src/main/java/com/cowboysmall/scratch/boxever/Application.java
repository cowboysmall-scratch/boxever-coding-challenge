package com.cowboysmall.scratch.boxever;

import com.cowboysmall.scratch.boxever.graph.Edge;
import com.cowboysmall.scratch.boxever.graph.Graph;
import com.cowboysmall.scratch.boxever.graph.GraphRepository;
import com.cowboysmall.scratch.boxever.options.Options;
import com.cowboysmall.scratch.boxever.options.OptionsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private OptionsReader optionsReader;

    @Autowired
    private GraphRepository<Graph<String>> graphRepository;


    //_________________________________________________________________________

    public static void main(String... args) {

        SpringApplication.run(Application.class, args);
    }


    //_________________________________________________________________________

    @Override
    public void run(String... args) {

        Options options = optionsReader.getOptions(args);

        if (options.isValid()) {

            Graph<String> graph = graphRepository.createGraph(options.isDirected());

            List<Edge<String>> edges = graph.shortestPath(options.getSource(), options.getDestination());
            edges.forEach(System.out::println);
            System.out.printf("time: %d%n\n", edges.stream().mapToInt(Edge::getWeight).sum());
        }
    }
}
