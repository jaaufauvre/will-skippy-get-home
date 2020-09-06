# frozen_string_literal: true

require 'skippy/point'
require 'skippy/grid_item'

# A square grid with items placed on it.
class Grid
  class ItemExistsError < StandardError; end
  class ItemNotFoundError < StandardError; end
  class OutOfBoundsError < StandardError
    def initialize(message, location)
      super message
      @location = location
    end
    attr_reader :location
  end

  def initialize(dimension)
    raise ArgumentError, 'Dimension of the grid must be a positive integer!' if dimension < 1

    @dimension = dimension
    # Items placed on the grid so far
    @items = {}
  end

  def get_item_location(grid_item)
    raise ItemNotFoundError, 'The item is not on the grid!' unless @items.key?(grid_item)

    @items[grid_item]
  end

  def place_item(grid_item, location)
    raise ItemExistsError, 'The item is already on the grid!' if @items.key?(grid_item)

    check_bounds(location)
    @items[grid_item] = location
  end

  def move_item(grid_item, direction)
    raise ItemNotFoundError, 'The item is not on the grid!' unless @items.key?(grid_item)

    location = @items[grid_item]
    new_location = location.get_neighbour(direction)
    check_bounds(new_location)
    @items[grid_item] = new_location
  end

  def check_bounds(location)
    x = location.x_coordinate
    y = location.y_coordinate
    out_of_bounds = x.negative? || y.negative? || x > @dimension - 1 || y > @dimension - 1
    message = "This location is outside the grid: #{location}!"
    raise OutOfBoundsError.new(message, location) if out_of_bounds
  end

  private :check_bounds
end
