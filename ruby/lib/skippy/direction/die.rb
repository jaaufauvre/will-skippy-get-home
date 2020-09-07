# frozen_string_literal: true

require 'singleton'

module Skippy
  module Direction
    # A 4-sided die, with each face representing a direction: North, East, South, or West.
    class Die
      include Singleton

      def initialize
        # Faces of the die are directions
        @faces = [Direction::NORTH, Direction::EAST, Direction::SOUTH, Direction::WEST]

        # Keep track of the number of throws in each direction so far
        @history = {}
        @faces.each { |face| @history[face] = 0 }

        # The die uses the Ruby system PRNG
        @random = Random::DEFAULT
      end

      # Roll the die to return a random direction.
      def roll
        direction = @faces[@random.rand(@faces.length)]
        @history[direction] += 1
        direction
      end

      # Print the die statistics.
      #
      # Die statistics:
      # Total throws: 53332
      # North: 25.2% South: 24.8% East: 25.2% West: 24.8%
      def print_stats
        stats = "Die statistics:\n"
        stats += "Total throws: #{compute_total_throw_count}\n"
        stats += "North: #{compute_percentage_of Direction::NORTH}% "
        stats += "South: #{compute_percentage_of Direction::SOUTH}% "
        stats += "East: #{compute_percentage_of Direction::EAST}% "
        stats += "West: #{compute_percentage_of Direction::WEST}%"
        puts stats
      end

      def compute_total_throw_count
        @history.sum { |_, count| count }
      end

      def compute_percentage_of(direction)
        percentage = @history[direction] * 100.0 / compute_total_throw_count
        percentage.round(1).to_s
      end

      private :compute_total_throw_count
      private :compute_percentage_of
    end
  end
end
