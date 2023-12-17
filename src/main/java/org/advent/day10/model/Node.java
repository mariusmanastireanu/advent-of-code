package org.advent.day10.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.advent.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Node {

    @EqualsAndHashCode.Include
    private final int rowIndex;
    @EqualsAndHashCode.Include
    private final int colIndex;
    private final char character;

    private final List<Node> neighbors = new ArrayList<>();

    public Node(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.character = ' ';
    }

    public Node(Map<String, Node> knownNodes, List<List<Character>> matrix, int rowIndex, int colIndex) {
        this.character = matrix.get(rowIndex).get(colIndex);
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        knownNodes.put(computeMatrixKey(rowIndex, colIndex), this);
        populateNeighbors(knownNodes, matrix, rowIndex, colIndex, this);
    }

    private void populateNeighbors(Map<String, Node> knownNodes, List<List<Character>> matrix, int rowIndex, int colIndex, Node node) {
        var currentPipe = Pipe.getPipe(character);
        neighbors.addAll(Arrays.stream(Direction.values())
                .filter(direction -> Pipe.canHaveNeighbor(currentPipe, direction))
                .map(direction -> getNode(knownNodes,
                        matrix,
                        rowIndex + direction.getRowOffset(),
                        colIndex + direction.getColOffset(),
                        direction.getOpposite(),
                        node))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private Node getNode(Map<String, Node> knownNodes, List<List<Character>> matrix, int rowIndex, int colIndex, Direction fromDirection, Node fromNode) {
        if (rowIndex < 0 || colIndex < 0 || rowIndex >= matrix.size() || colIndex >= matrix.get(rowIndex).size()) {
            return null;
        }
        if (knownNodes.containsKey(computeMatrixKey(rowIndex, colIndex))) {
            var node = knownNodes.get(computeMatrixKey(rowIndex, colIndex));
            var pipe = Pipe.getPipe(node.getCharacter());
            return Pipe.SOURCE.equals(pipe) || pipe.isConnectedFrom(fromDirection) ? node : null;
        }
        var currentCharacter = matrix.get(rowIndex).get(colIndex);
        if ('.' == currentCharacter) {
            return null;
        }
        var pipe = Pipe.getPipe(currentCharacter);
        if (Pipe.SOURCE.equals(pipe) || pipe.isConnectedFrom(fromDirection)) {
            return new Node(knownNodes, matrix, rowIndex, colIndex);
        }
        return null;
    }

    private String computeMatrixKey(int rowIndex, int colIndex) {
        return rowIndex + "," + colIndex;
    }

}
