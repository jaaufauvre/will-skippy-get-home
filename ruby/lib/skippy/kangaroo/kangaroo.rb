# frozen_string_literal: true

require 'skippy/direction'
require 'skippy/grid'

module Skippy
  module Kangaroo
    class Kangaroo
      def initialize
        @die = Direction::Die.instance
      end

      # Make the Kangaroo say something.
      def chortle(something)
        puts something
      end

      # Ask the kangaroo to find its home on the grid.
      # The kangaroo's strategy to find his way home is to take random hops either North, South, East or West.
      def find_home(grid, home)
        hop_count = 0
        while grid.get_item_location(self) != grid.get_item_location(home)
          take_random_hop(grid)
          hop_count += 1
        end
        chortle("Finished in #{hop_count} hops!")
      end

      def take_random_hop(grid)
        random_direction = @die.roll
        location = grid.move_item(self, random_direction)
        chortle("Hopped to: #{location}")
      rescue Grid::OutOfBoundsError => e
        chortle("Oops, hit the boundary: #{e.location}")
      end

      private :take_random_hop
    end
  end
end
