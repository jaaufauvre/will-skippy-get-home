# frozen_string_literal: true

require 'skippy/grid'
require 'skippy/direction'
require 'skippy/kangaroo'

module Skippy
  class Main
    def self.run
      # Create a grid
      puts('Enter dimension of the grid (>=1):')
      dimension = gets.to_i
      grid = Grid::Grid.new(dimension)

      # Place Skippy on the grid
      skippy = Kangaroo::Kangaroo.new
      grid.place_item(skippy, Grid::Point.new(0, 0))

      # Place Skippy's home on the grid
      home = Grid::GridItem.new
      grid.place_item(home, Grid::Point.new(dimension - 1, dimension - 1))

      # Ask skippy to find his home
      skippy.find_home(grid, home)

      # Print die statistics
      puts ''
      Direction::Die.instance.print_stats
    end
  end
end
