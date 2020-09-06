# frozen_string_literal: true

require 'skippy/version'
require 'skippy/grid'
require 'skippy/kangaroo'

module Skippy
  # Create a grid
  puts('Enter dimension of the grid (>=1):')
  dimension = gets.to_i
  grid = Grid.new(dimension)

  # Place Skippy on the grid
  skippy = Kangaroo.new
  grid.place_item(skippy, Point.new(0, 0))

  # Place Skippy's home on the grid
  home = GridItem.new
  grid.place_item(home, Point.new(dimension - 1, dimension - 1))

  # Ask skippy to find his home
  skippy.find_home(grid, home)

  # Print die statistics
  puts ''
  Die.instance.print_stats
end
