package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.geom.Line2D;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Square {
    private List<Line2D> lines;
}