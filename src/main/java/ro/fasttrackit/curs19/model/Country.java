package ro.fasttrackit.curs19.model;

import java.util.List;

public record Country (int id,
                       String name,
                       String capital,
                       long population,
                       double area,
                       String continent,
                       List<String> neighbours){
}
