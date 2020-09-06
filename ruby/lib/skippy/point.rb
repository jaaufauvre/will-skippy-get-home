# frozen_string_literal: true

# A point on a grid, defined by a pair of coordinates.
class Point
  def initialize(x_coordinate, y_coordinate)
    @x_coordinate = x_coordinate
    @y_coordinate = y_coordinate
  end

  def to_s
    "(#{@x_coordinate}, #{@y_coordinate})"
  end

  def ==(other)
    @x_coordinate == other.x_coordinate && @y_coordinate == other.y_coordinate
  end

  # Get the neighbour point located at the North, East, South, or West of this point.
  # Doesn't check bounds.
  def get_neighbour(direction) # rubocop:disable Metrics/MethodLength
    case direction
    when Direction::EAST
      Point.new(@x_coordinate + 1, @y_coordinate)
    when Direction::WEST
      Point.new(@x_coordinate - 1, @y_coordinate)
    when Direction::NORTH
      Point.new(@x_coordinate, @y_coordinate + 1)
    when Direction::SOUTH
      Point.new(@x_coordinate, @y_coordinate - 1)
    else
      raise ArgumentError, "Unsupported direction: #{direction}"
    end
  end

  attr_reader :x_coordinate, :y_coordinate
end
