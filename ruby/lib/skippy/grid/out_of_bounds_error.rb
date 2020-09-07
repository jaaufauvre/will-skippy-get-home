# frozen_string_literal: true

module Skippy
  module Grid
    class OutOfBoundsError < StandardError
      def initialize(message, location)
        super message
        @location = location
      end
      attr_reader :location
    end
  end
end
