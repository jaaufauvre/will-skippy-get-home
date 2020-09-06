# frozen_string_literal: true

require 'skippy/version'
require 'skippy/die'

# Top-level module for solving "Will Skippy Get Home?"
module Skippy
  class Error < StandardError; end

  # Create a grid
  # TODO

  # Place Skippy on the grid
  # TODO

  # Place Skippy's home on the grid
  # TODO

  # Ask skippy to find his home
  # TODO

  # Print die statistics
  # TODO

  # Temp!
  1_000_000.times do
    Die.instance.roll
  end
  puts Die.instance.print_stats
end
