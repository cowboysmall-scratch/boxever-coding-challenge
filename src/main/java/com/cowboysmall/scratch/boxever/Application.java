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

import static com.cowboysmall.scratch.boxever.util.GraphUtils.hasPath;
import static com.cowboysmall.scratch.boxever.util.GraphUtils.shortestPath;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private OptionsReader optionsReader;

    @Autowired
    private GraphRepository<Graph<String, Long>> graphRepository;


    //_________________________________________________________________________

    public static void main(String... args) {

        SpringApplication.run(Application.class, args);
    }


    //_________________________________________________________________________

    @Override
    public void run(String... args) {

        Options options = optionsReader.getOptions(args);

        if (options.isValid()) {

            Graph<String, Long> graph = graphRepository.createGraph(options.isDirected());

            if (!graph.containsNode(options.getSource()) || !graph.containsNode(options.getDestination())) {

                if (!graph.containsNode(options.getSource()))
                    System.out.printf("\nsource %s does not exist", options.getSource());
                if (!graph.containsNode(options.getDestination()))
                    System.out.printf("\ndestination %s does not exist", options.getDestination());
                System.out.println("\n");

            } else if (!hasPath(graph, options.getSource(), options.getDestination())) {

                System.out.printf("\nno route between %s and %s\n\n", options.getSource(), options.getDestination());

            } else {

                System.out.printf("\nshortest route between %s and %s\n\n", options.getSource(), options.getDestination());
                List<Edge<String, Long>> edges = shortestPath(graph, options.getSource(), options.getDestination());
                edges.forEach(System.out::println);
                System.out.printf("\ntime: %d\n\n", edges.stream().mapToLong(Edge::getWeight).sum());
            }
        }
    }
}
